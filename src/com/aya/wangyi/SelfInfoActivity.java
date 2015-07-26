package com.aya.wangyi;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.AlreadyConnectedException;

import tool.BitmapCut;

import com.aya.fragment.PcFragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SelfInfoActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_self_info);
		initActionBar();
		initView();
	}

	private Button exist;
	private RelativeLayout rHead;
	private ImageView ivHead;
	private void initView(){
		exist=(Button)super.findViewById(R.id.exsit);
		exist.setOnClickListener(this);
		rHead=(RelativeLayout)super.findViewById(R.id.r_pc_self_headimg);
		rHead.setOnClickListener(this);
		ivHead=(ImageView)super.findViewById(R.id.iv_pc_self_headimg);
	}
	
	/**
	 * 处理点击事件
	 */
	private Intent intent;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.exsit:
			finish();
			super.overridePendingTransition(R.anim.back_zoom_in, R.anim.back_zoom_out);
			break;
		case R.id.r_pc_self_headimg:
			showDialog();
			break;
		
			
		}
	}
	/**
	 * 处理头部显示样式
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.self_info, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			super.onBackPressed();// finish 当前activity
        	super.overridePendingTransition(R.anim.back_zoom_in, R.anim.back_zoom_out);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	private ActionBar actionBar;
	private void initActionBar(){
		actionBar=super.getActionBar();
		actionBar.show();
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeAsUpIndicator(R.drawable.ic_up);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		//设置logo
		actionBar.setLogo(R.drawable.logo);
		Drawable colorDrawable=new ColorDrawable(android.R.color.transparent);
		actionBar.setIcon(colorDrawable);
		//自定义区域
		actionBar.setDisplayShowCustomEnabled(true);
		TextView tvTitle=new TextView(this);
		tvTitle.setText("个人设置");
		tvTitle.setTextSize(18);
		int colorVal=getResources().getColor(R.color.white);
		//tvTitle.setTextColor(Color.WHITE);
		tvTitle.setTextColor(colorVal);
		tvTitle.setGravity(Gravity.CENTER);
		actionBar.setCustomView(tvTitle);
	}

	/**
	 * 选择头像时弹出的对话框
	 */
	private void showDialog(){
		final AlertDialog potraidDlg = new AlertDialog.Builder(this).create();
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        switch (requestCode) {
	        case 11:
	            if (resultCode == RESULT_OK) {
	                cropPhoto(data.getData());//裁剪图片
	            }
	            break;
	        case 22:
	            if (resultCode == RESULT_OK) {
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
	                    ivHead.setImageBitmap(headerPortait);//用ImageView显示出来
	                }
	            }
	            break;
	        default:
	            break;
	 
	        }
	        super.onActivityResult(requestCode, resultCode, data);
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
	 
	/**
     * 调用拍照的裁剪功能
     * @param uri
     */
    public void cropPhoto(Uri uri) {
    	//调用拍照的裁剪功能
        Intent intent = new Intent("com.android.camera.action.CROP");
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

}
