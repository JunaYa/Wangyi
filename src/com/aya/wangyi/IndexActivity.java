package com.aya.wangyi;

import java.util.ArrayList; 
import java.util.List;

import com.aya.adapter.IndexViewPagerAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class IndexActivity extends Activity 
	implements OnPageChangeListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		initView();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.index, menu);
		return true;
	}

	private ViewPager viewPager;
	private IndexViewPagerAdapter adapter;
	private List<ImageView> list = new ArrayList<ImageView>();
	private void initView(){
		viewPager = (ViewPager)super.findViewById(R.id.viewpager_idex);
		adapter = new IndexViewPagerAdapter(this, list);
		viewPager.setOnPageChangeListener(this);
		viewPager.setAdapter(adapter);
		load();
	}
	
	private void load(){
		ImageView img1=new ImageView(this);
		img1.setImageResource(R.drawable.sence01);
		img1.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		list.add(img1);
		
		ImageView img2=new ImageView(this);
		img2.setImageResource(R.drawable.sence02);
		img2.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		list.add(img2);
		
		ImageView img3=new ImageView(this);
		img3.setImageResource(R.drawable.sence02);
		img3.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		list.add(img3);
		
		ImageView img4=new ImageView(this);
		img4.setImageResource(R.drawable.sence02);
		img4.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		list.add(img4);
		
		adapter.notifyDataSetChanged();
		}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	private SharedPreferences sp;
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		if(position==3){
			goHome();
			sp=super.getSharedPreferences("app_param",Context.MODE_PRIVATE);
			sp.edit().putInt("use",1).commit();
		}
	}
	private void goHome(){
		Intent intent = new Intent(this,WangYiActivity.class);
		startActivity(intent);
		finish();
	}
}
