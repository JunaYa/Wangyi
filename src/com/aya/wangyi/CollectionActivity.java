package com.aya.wangyi;


import java.util.ArrayList;
import java.util.List;

import com.aya.adapter.ViewPagerAdapter;
import com.aya.fragment.CollectionNewsFragment;
import com.aya.fragment.CollectionPictureFragmment;
import com.aya.fragment.CollectionTieFragmment;

import android.os.Bundle;
import android.app.ActionBar;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CollectionActivity extends FragmentActivity 
implements OnPageChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection);
		initActionBar();
		initView();
	}

	private ViewPager viewPager ;
	private List<Fragment> ftList = new ArrayList<Fragment>();
	private ViewPagerAdapter adapter;
	private RadioGroup group;
	private RadioButton rb;
	private void initView(){
		group=(RadioGroup)super.findViewById(R.id.group_collection);
		group.check(1);
		viewPager=(ViewPager)super.findViewById(R.id.viewpager_collection);
		viewPager.setOnPageChangeListener(this);
		load();
		
	}
	
	/**
	 * 加载数据
	 */
	private void load(){
		CollectionNewsFragment ftNews=new CollectionNewsFragment();
		CollectionPictureFragmment ftPictrue = new CollectionPictureFragmment();
		CollectionTieFragmment ftTie = new CollectionTieFragmment();
		ftList.add(ftNews);
		ftList.add(ftTie);
		ftList.add(ftPictrue);
		adapter = new ViewPagerAdapter(super.getSupportFragmentManager(), ftList);
		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setCurrentItem(0);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.collection, menu);
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
	
	/**
	 * 设置头样式
	 */
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
		tvTitle.setText("我的收藏");
		tvTitle.setTextSize(18);
		int colorVal=getResources().getColor(R.color.white);
		tvTitle.setTextColor(colorVal);
		tvTitle.setGravity(Gravity.CENTER);
		actionBar.setCustomView(tvTitle);
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
		initTab(position);
	}
	private void initTab(int idx){
		rb=(RadioButton)group.findViewById(idx+1);
		rb.setChecked(true);
	}
}
