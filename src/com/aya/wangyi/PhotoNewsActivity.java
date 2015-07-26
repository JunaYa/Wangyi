package com.aya.wangyi;

import java.util.ArrayList; 
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.os.Bundle;  
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aya.adapter.PhotoPagerAdapter;
import com.aya.entity.News;
import com.aya.entity.NewsDetail;
import com.aya.entity.Photo;
import com.aya.util.CollectionDao;
import com.aya.wangyi.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class PhotoNewsActivity extends Activity 
implements OnPageChangeListener, OnClickListener {

	private String url;
	private String playsum;
	private FinalHttp afinal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_news);
		url=super.getIntent().getStringExtra("url");
		Log.d("test", url);
		playsum=super.getIntent().getStringExtra("replyCount");
		
		afinal=new FinalHttp();
		initView();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.photo_news, menu);
		return true;
	}
	private ViewPager viewPager;
	private PhotoPagerAdapter adapter;
	private TextView tvTitle,picCount,plyCount;
	private List<Photo> photolist=new ArrayList<Photo>();
	private ImageView ivback,ivOverflow;
	private RelativeLayout photonews;
	private void initView(){
		viewPager=(ViewPager)super.findViewById(R.id.viewpager_photo);
		adapter=new PhotoPagerAdapter(this, photolist);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		load();
		tvTitle=(TextView)super.findViewById(R.id.tv_title_photo_item);
		picCount=(TextView)super.findViewById(R.id.tv_title_photo_item_count);
		plyCount=(TextView)super.findViewById(R.id.tv_base_photonews_comment);
		plyCount.setText(playsum);
		
		ivback=(ImageView)super.findViewById(R.id.photonewsdetailback);
		ivOverflow=(ImageView)super.findViewById(R.id.iv_base_photonews_overflow);	
		ivback.setOnClickListener(this);
		ivOverflow.setOnClickListener(this);
		photonews=(RelativeLayout)super.findViewById(R.id.r_photonews);
	}

	private void load(){
		afinal.get(url, new AjaxCallBack<String>(){
		
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				Log.d("aya", "请求失败,请检测网络连接!");
				Toast.makeText(PhotoNewsActivity.this, "请求失败,请检测网络连接!", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(String t) {
				// TODO Auto-generated method stub
				Gson gson=new Gson();
				Log.d("android", t);
				NewsDetail	news = gson.fromJson(t, new TypeToken<NewsDetail>(){}.getType());
				photolist.addAll(news.getPhotos());
				Log.d("aya", photolist.size()+"size");
				adapter.notifyDataSetChanged();
				Message msg=new Message();
				Bundle bundle=new Bundle();
				bundle.putString("title", news.getSetname());
				bundle.putString("picCount", photolist.size()+"");
				msg.setData(bundle);
				handler.sendMessage(msg);
			}
			
		});
	}
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
				String title=(String) msg.getData().get("title");
				String imgcount=msg.getData().getString("picCount");
				tvTitle.setText(title);
				setTextView("1/"+imgcount);
		}
		
	};

	//设置图片页数
	private void setTextView(String imgcount){
		picCount.setText(imgcount);
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		setTextView((position+1)+"/"+photolist.size());
		if(position==adapter.getCount()){
			Toast.makeText(this, "已经是最后一页，相关内容还没有关联上", Toast.LENGTH_LONG).show();
		}
	}
	/**
	 * 处理点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.photonewsdetailback:
			finish();
			super.overridePendingTransition(R.anim.back_zoom_in, R.anim.back_zoom_out);
			break;
		case R.id.iv_base_photonews_overflow:
			initPopuwindow();
			popuwindow.showAtLocation(photonews,0,0,Gravity.CENTER_HORIZONTAL);
			
			
		}
	}
	
	/**
	 *弹出popuwindow 
	 */
	private PopupWindow popuwindow;
	private LinearLayout rCollection;
	private ImageView ivNewsCollection;
	private CheckBox tvNewsCollection;
	private int ret;
	private SharedPreferences sp;
	private CollectionDao collection = new CollectionDao(this);
	private void initPopuwindow(){
		
		View viewpopu=LayoutInflater.from(this).inflate(R.layout.popuwindow_newsdetail_layout, null);
		popuwindow=new PopupWindow(viewpopu,ViewGroup.LayoutParams.MATCH_PARENT,
										ViewGroup.LayoutParams.MATCH_PARENT);
		popuwindow.setFocusable(true);
		popuwindow.setOutsideTouchable(true);
		popuwindow.setAnimationStyle(R.anim.share_out_upshow);
		
		ivNewsCollection=(ImageView)viewpopu.findViewById(R.id.iv_news_collection);
		tvNewsCollection=(CheckBox)viewpopu.findViewById(R.id.tv_flow_collect);
		sp= super.getSharedPreferences("app_collect",Context.MODE_PRIVATE);
		ret = sp.getInt("ret",1);
		if(ret!=1){
			
			tvNewsCollection.setText("取消收藏");
			tvNewsCollection.setChecked(true);
			ivNewsCollection.setImageResource(R.drawable.biz_news_newspage_collect_icon);
		}else{
			
			tvNewsCollection.setChecked(false);
			tvNewsCollection.setText("收藏");
			ivNewsCollection.setImageResource(R.drawable.biz_news_newspage_un_collect_icon);
		}
		rCollection=(LinearLayout)viewpopu.findViewById(R.id.r_news_collect);
		rCollection.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(ret==0){
					sp.edit().putInt("ret", 1).commit();
					Toast.makeText(PhotoNewsActivity.this, "已取消收藏！",Toast.LENGTH_LONG).show();
				}else{
					
					sp.edit().putInt("ret", 0).commit();
					Toast.makeText(PhotoNewsActivity.this, "收藏成功！",Toast.LENGTH_LONG).show();
				}
				popuwindow.dismiss();
			}
		});
	}

	
}
