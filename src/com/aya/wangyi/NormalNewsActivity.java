package com.aya.wangyi;

import java.lang.reflect.Method;
import java.util.List;  
import java.util.Map; 

import com.aya.entity.News;
import com.aya.entity.NewsDetail;
import com.aya.entity.Photo;
import com.aya.util.CollectionDao;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class NormalNewsActivity extends Activity implements OnClickListener

	 {

	private String url;
	private String docId;
	private String replyCount;
	private String source;
	private String title;
	private News news;
	private FinalHttp afinal;
	private ProgressBar probar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal_news);
		url=super.getIntent().getStringExtra("url");
		docId=super.getIntent().getStringExtra("docId");
		replyCount=super.getIntent().getStringExtra("replyCount");
		source=super.getIntent().getStringExtra("source");
		title=super.getIntent().getStringExtra("title");
		afinal=new FinalHttp();
		initView();
		load();
//		iniActionBar();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.normal_news, menu);
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
//	/**
//	 * 显示overflower菜单图标
//	 */
//	@Override
//	public boolean onMenuOpened(int featureId, Menu menu) {  
//	    if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {  
//	        if (menu.getClass().getSimpleName().equals("MenuBuilder")) {  
//	            try {  
//	                Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);  
//	                m.setAccessible(true);  
//	                m.invoke(menu, true);  
//	            } catch (Exception e) {  
//	            }  
//	        }  
//	    }  
//	    return super.onMenuOpened(featureId, menu);  
//	} 
//	private ActionBar actionBar;
//	private void iniActionBar(){
//		actionBar=super.getActionBar();
//		actionBar.show();
//		//设置回退
//		actionBar.setDisplayHomeAsUpEnabled(true);
//		actionBar.setHomeAsUpIndicator(R.drawable.base_action_bar_back_black);
//		//不显示home区域标题
//		actionBar.setDisplayShowTitleEnabled(false); 
//	}


	
	private TextView tvTitle,tvSource,tvPTime,tvCommentCount;
	
	private WebView webView;
	
	private ImageView topback,topflow;
	private RelativeLayout nomalnews;
	
	private void initView(){
		tvTitle=(TextView)super.findViewById(R.id.tv_title);
		tvSource=(TextView)super.findViewById(R.id.tv_source);
		tvPTime=(TextView)super.findViewById(R.id.tv_pTime);
		webView=(WebView)super.findViewById(R.id.webContent);
		initWebView();
		probar=(ProgressBar)super.findViewById(R.id.progressBar1);
		
		topback=(ImageView)super.findViewById(R.id.newsdetailback);
		topflow=(ImageView)super.findViewById(R.id.iv_base_news_overflow);
		topback.setOnClickListener(this);
		topflow.setOnClickListener(this);
		nomalnews=(RelativeLayout)super.findViewById(R.id.nomalnews_layout);
		
		tvCommentCount=(TextView)findViewById(R.id.tv_base_comment);
		tvCommentCount.setText(replyCount+"跟帖");
	}
	
	private void initWebView(){ 
		webView.setBackgroundColor(0);
	//	webView.getBackground().setAlpha(0);
		
		WebSettings setting=webView.getSettings();
		//设置加载进来的页面自适应手机屏幕 
//		setting.setUseWideViewPort(true);
//		setting.setLoadWithOverviewMode(true);
		setting.setDefaultFontSize(16);
		setting.setDisplayZoomControls(false);
		setting.setDefaultTextEncodingName("utf-8");
		
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public void onPageFinished(WebView view, String url) {
				// TODO Auto-generated method stub
				probar.setVisibility(View.GONE);
			}

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// TODO Auto-generated method stub
				view.loadUrl(url);
				return true;
			}
			
		});
	}
	
	private void load(){
		afinal.get(url, new AjaxCallBack<String>(){

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
			}

			@Override
			public void onSuccess(String t) {
				// TODO Auto-generated method stub
				Gson gson=new Gson();
				Map<String,NewsDetail> map=
						gson.fromJson(t,new TypeToken<Map<String,NewsDetail>>(){}.getType());
				NewsDetail newsDetail=map.get(docId);
				Message msg=new Message();
				Bundle bundle=new Bundle();
				bundle.putSerializable("details", newsDetail);
				bundle.putString("html",getHtml(newsDetail));
				msg.arg1=1;
				msg.setData(bundle);
				handler.sendMessage(msg);
				//super.onSuccess(t);
			}
		});
	}
	
	private String getHtml(NewsDetail details){
		List<Photo> photoList=details.getImg();
		String html=details.getBody();
		
		if(photoList!=null&&photoList.size()>0){
			for(int i=0;i<photoList.size();i++){
				Photo photo=photoList.get(i);
				String imgTag="<img src='"+photo.getSrc()+"' ";
				String pixel=photo.getPixel();
				if(pixel!=null&&!pixel.equals("")){
					String[] size=pixel.split("\\*");
					if(size!=null&&size.length==2){
						imgTag+="width='"+size[0]+"px' ";
						imgTag+="height='"+size[1]+"px' ";
					}
				}
				
				imgTag+="/>";
				String alt=photo.getAlt();
				if(alt!=null&&!alt.equals("")) imgTag+="<p>"+alt+"</p>";
				String pos=photo.getref();
				html=html.replace(pos, imgTag);
				
			}
			
		}
		return html;
	}
	private NewsDetail details;
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.arg1){
			case 1:
				String html=msg.getData().getString("html");
				details=(NewsDetail)msg.getData().getSerializable("details");
				setViewData(details);
				webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
				break;
			}
		}
		
	};
	private void setViewData(NewsDetail desc){
		tvTitle.setText(desc.getTitle());
		tvSource.setText(desc.getSource());
		tvPTime.setText(desc.getpTime());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.newsdetailback:
			this.finish();
			super.overridePendingTransition(R.anim.back_zoom_in, R.anim.back_zoom_out);
			break;
		case R.id.iv_base_news_overflow:
			    initPopuwindow();
				popuwindow.showAtLocation(nomalnews,0,0,Gravity.CENTER_HORIZONTAL);
			break;
		
		}
	}

	private PopupWindow popuwindow;
	private LinearLayout rCollection;
	private ImageView ivNewsCollection;
	private CheckBox tvNewsCollection;
	private int ret;
	private SharedPreferences sp;
	private CollectionDao collection = new CollectionDao(this);
	private void initPopuwindow(){
		news = new News();
		news.setTitle(title);
		news.setUrl(url);
		news.setSource(source);
		news.setReplyCount(Integer.valueOf(replyCount));
		news.setDocid(docId);
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
					Toast.makeText(NormalNewsActivity.this, "已取消收藏！",Toast.LENGTH_LONG).show();
				}else{
					collection.insertNews(news);
					sp.edit().putInt("ret", 0).commit();
					Toast.makeText(NormalNewsActivity.this, "收藏成功！",Toast.LENGTH_LONG).show();
				}
				popuwindow.dismiss();
			}
		});
	}
}
