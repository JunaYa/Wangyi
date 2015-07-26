package com.aya.fragment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.w3c.dom.Text;

import tool.BitmapCut;

import com.aya.entity.ChannelDb;
import com.aya.util.CollectionDao;
import com.aya.wangyi.CollectionActivity;
import com.aya.wangyi.R;
import com.aya.wangyi.SelfInfoActivity;
import com.aya.wangyi.SettingActivity;
import com.aya.wangyi.UserLoginActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PcFragment extends Fragment implements OnClickListener {

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		collection=new CollectionDao(super.getActivity());
	}

	
	private RelativeLayout rCollection,rRead,rTie,rGold,rSetting;
	private ImageView ivUserHead;
	private TextView tvLogin,tvUser,selfinfo; 
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.pc_main_layout, null);
		 rCollection=(RelativeLayout)view.findViewById(R.id.r_pc_main_collection);
		 rRead=(RelativeLayout)view.findViewById(R.id.r_pc_main_read);
		 rTie=(RelativeLayout)view.findViewById(R.id.R_pc_main_tie);
		 rGold=(RelativeLayout)view.findViewById(R.id.r_pc_main_gold);
		 rCollection.setOnClickListener(this);
		 rRead.setOnClickListener(this);
		 rTie.setOnClickListener(this);
		 rGold.setOnClickListener(this);
		 ivUserHead=(ImageView)view.findViewById(R.id.iv_img_pc_head);
		 ivUserHead.setOnClickListener(this);
		 tvLogin=(TextView)view.findViewById(R.id.tv_user_login);
		 tvLogin.setOnClickListener(this);
		 tvUser=(TextView)view.findViewById(R.id.tv_user_login);
		 initUser();
		 selfinfo=(TextView)view.findViewById(R.id.tv_pc_selfinfo);
		 selfinfo.setOnClickListener(this);
		 rSetting=(RelativeLayout)view.findViewById(R.id.r_pc_main_setting);
		 rSetting.setOnClickListener(this);
		return view;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	
	private CollectionDao collection;
	private SharedPreferences sp;
	private String state;
	private void initUser(){
		sp=super.getActivity().getSharedPreferences("app_pramas",Context.MODE_PRIVATE);
		state = sp.getString("user_login","");
		if(state!=null ){
			tvUser.setText("立即登陆");
		}else{
			
			tvUser.setText(state);
		}
		
	}
	
	/////////////////////////////////////////////////////////
	
	/**
	 * 选择头像时弹出的对话框
	 */
	private void showDialog(){
		final AlertDialog potraidDlg = new AlertDialog.Builder(super.getActivity()).create();
		potraidDlg.show();
		Window win = potraidDlg.getWindow();
		win.setContentView(R.layout.dialog_show_photo);
		
		View viewTop=win.findViewById(R.id.view_top_dialog_sethead);
		View viewBottom=win.findViewById(R.id.view_bottom_dialog_sethead);
		RadioButton photo=(RadioButton)win.findViewById(R.id.rb_photo_dialog_sethead);
		RadioButton pic=(RadioButton)win.findViewById(R.id.rb_pic_dialog_sethead);
		RadioButton bind=(RadioButton)win.findViewById(R.id.rb_bind_dialog_sethead);
		//点击dialog外部，关闭dialog
		viewTop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				potraidDlg.dismiss();
			}
		});
		viewBottom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				potraidDlg.dismiss();
			}
		});
		//调用相机
		photo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			Intent intentPhoto=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);	
			intentPhoto.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(
					new File(Environment.getExternalStorageDirectory(),"/user_head.png")));
			startActivityForResult(intentPhoto, 22);
			potraidDlg.dismiss();
			}
		});
		//调用相片
		pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intentPic=new Intent(Intent.ACTION_PICK,null);
				intentPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
				startActivityForResult(intentPic, 11);
				potraidDlg.dismiss();
			}
		});
		
	}
		
	
	private Bitmap headerPortait;
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		switch (requestCode) {
        case 11:
            if (resultCode == super.getActivity().RESULT_OK) {
                cropPhoto(data.getData());//裁剪图片
            }
            break;
        case 22:
            if (resultCode == super.getActivity().RESULT_OK) {
                File temp = new File(Environment.getExternalStorageDirectory()
                        + "/user_header.png");
                cropPhoto(Uri.fromFile(temp));//裁剪图片
            } 
            break;
        case 33:
            if (data != null) {
                Bundle extras = data.getExtras();
                headerPortait = extras.getParcelable("data");
                headerPortait=BitmapCut.toRoundBitmap(headerPortait);
                if(headerPortait!=null){
                	saveHeadImg(headerPortait);
                	ivUserHead.setImageBitmap(headerPortait);//用ImageView显示出来
                }
            }
            break;
        default:
            break;
 
        }
		super.onActivityResult(requestCode, resultCode, data);
	}


	private Intent intent=null;
	//处理点击事件
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.r_pc_main_collection:
			toCollection();
			break;
		case R.id.r_pc_main_read:
			toLogin();
			break;
		case R.id.R_pc_main_tie:
			toLogin();
			break;
		case R.id.r_pc_main_gold:
			toLogin();
			break;
		case R.id.iv_img_pc_head:
//			toLogin();
			showDialog();
			break;
		case R.id.tv_user_login:
			toLogin();
			break;
		case R.id.tv_pc_selfinfo:
		    intent = new Intent(super.getActivity(),SelfInfoActivity.class);
		    startActivity(intent);
		    super.getActivity().overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out);
			break;
		case R.id.r_pc_main_setting:
			intent = new Intent(super.getActivity(), SettingActivity.class);
			startActivity(intent);
			super.getActivity().overridePendingTransition(R.anim.zoom_in ,R.anim.zoom_out);
			break;
		
		}
	}
	private void toLogin(){
		intent=new Intent(super.getActivity(),UserLoginActivity.class);
		startActivity(intent);
		super.getActivity().overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
	}
	private void toCollection(){
		intent = new Intent(super.getActivity(),CollectionActivity.class);
		startActivity(intent);
		
	}
	
	/**
     * 调用拍照的裁剪功能
     * @param uri
     */
    public void cropPhoto(Uri uri) {
    	//调用拍照的裁剪功能
        Intent intent = new Intent("com.android.camera.action.CROP"); //匹配隐士action
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
         // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 33);
    }
    public Bitmap readHead(){
		String file=Environment.getExternalStorageDirectory()+"/user_header.png";
		return BitmapFactory.decodeFile(file);
	}
	public void saveHeadImg(Bitmap head){
		FileOutputStream fos=null;
		 try {
			 fos=new FileOutputStream(new File(Environment.getExternalStorageDirectory()+"/user_header.png"));
			 head.compress(CompressFormat.PNG, 100, fos);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally{
			try {
				if(fos!=null)fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
	}
}
