package com.aya.wangyi;
 

import com.aya.entity.TabDb; 

import android.content.pm.ActivityInfo;
import android.os.Bundle; 
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost;
import com.aya.wangyi.R;

public class WangYiActivity extends FragmentActivity implements OnTabChangeListener {

	private FragmentTabHost tabHost;
	private FrameLayout content;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wang_yi);
		tabHost=(FragmentTabHost)super.findViewById(android.R.id.tabhost);
		content=(FrameLayout)super.findViewById(R.id.contentLayout1);
		
		tabHost.setup(this,super.getSupportFragmentManager(),R.id.contentLayout1);
		tabHost.getTabWidget().setDividerDrawable(null);
		tabHost.setOnTabChangedListener(this);
		initTab();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		//设置仅是竖屏
		//		if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
//			  setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//			 }

		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wang_yi, menu);
		return true;
	}

	@Override
	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub
		updateTab();
	}
	private void initTab(){
		String tabs[]=TabDb.getTabsTxt();
		for(int i=0;i<tabs.length;i++){
			TabSpec tabSpec=tabHost.newTabSpec(tabs[i]).setIndicator(getTabView(i));
			tabHost.addTab(tabSpec,TabDb.getFragments()[i],null);
			tabHost.setTag(i);
		}
		}
		
	private View getTabView(int idx){
		View view=LayoutInflater.from(this).inflate(R.layout.footer_tabs,null);
		((TextView)view.findViewById(R.id.tvTab)).setText(TabDb.getTabsTxt()[idx]);
		if(idx==0){
			((TextView)view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.red));
			((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImgLight()[idx]);
		}else{
			((ImageView)view.findViewById(R.id.ivImg)).setImageResource(TabDb.getTabsImg()[idx]);
		}
		return view;
			
	}
	private void updateTab(){
		TabWidget tabw=tabHost.getTabWidget();
		for(int i=0;i<tabw.getChildCount();i++){
			View view=tabw.getChildAt(i);
			ImageView iv=(ImageView)view.findViewById(R.id.ivImg);
			
			if(i==tabHost.getCurrentTab()){
				((TextView)view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.red));
				iv.setImageResource(TabDb.getTabsImgLight()[i]);
			}else{
				((TextView)view.findViewById(R.id.tvTab)).setTextColor(getResources().getColor(R.color.textclor));
				iv.setImageResource(TabDb.getTabsImg()[i]);
			}
			
		}
	}

	private long endTime=0;
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(System.currentTimeMillis()-endTime>2000){
			Toast.makeText(this, "再按一次退出网易新闻",Toast.LENGTH_LONG).show();
			endTime=System.currentTimeMillis();
		}else{
			System.exit(0);
		}
	}
	
}
