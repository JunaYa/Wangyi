package com.aya.wangyi;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SettingActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initActionBar();
		initView();
	}

	private TextView	tvSelfSetting;
	private void initView(){
		tvSelfSetting=(TextView)super.findViewById(R.id.tv_setting_self);
		tvSelfSetting.setOnClickListener(this);
		
	}

	/**
	 * 处理点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.tv_setting_self:
			Intent intentToselfSetting=new Intent(this,SelfInfoActivity.class);
			startActivity(intentToselfSetting);
			super.overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
			break;
		}
	}
	/**
	 * 处理头部显示样式
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
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
		tvTitle.setText("设置");
		tvTitle.setTextSize(18);
		int colorVal=getResources().getColor(R.color.white);
		//tvTitle.setTextColor(Color.WHITE);
		tvTitle.setTextColor(colorVal);
		tvTitle.setGravity(Gravity.CENTER);
		actionBar.setCustomView(tvTitle);
	}

	
}
