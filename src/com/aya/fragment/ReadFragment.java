package com.aya.fragment;

import java.util.ArrayList;
import java.util.List;

import com.aya.adapter.ViewPagerAdapter;
import com.aya.wangyi.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ReadFragment extends Fragment 
	implements OnClickListener, OnCheckedChangeListener, OnPageChangeListener {

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	
	
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.read_main_layout, null);
			initView();
			initViewPager();
		}
		
		ViewGroup parent=(ViewGroup)view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}

	/*
	 * 初始化view
	 */
	private ImageView ivAddMenu;
	private RadioGroup group;
	private void initView(){
		ivAddMenu=(ImageView)view.findViewById(R.id.iv_read_menu_add);
		ivAddMenu.setOnClickListener(this);
		group=(RadioGroup)view.findViewById(R.id.group_read_tab);
		group.check(1);
		group.setOnCheckedChangeListener(this);
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	/**
	 * 初始化viewpager
	 */
	private ViewPager viewPager;
	private List<Fragment> frag = new ArrayList<Fragment>();
	private ViewPagerAdapter adapter;
	private void initViewPager(){
		ReadCommendFragment fgCommend=new ReadCommendFragment();
		ReadOrderFragment fgOrder=new ReadOrderFragment();
		frag.add(fgCommend);
		frag.add(fgOrder);
		
		viewPager=(ViewPager)view.findViewById(R.id.viewpager_read);
		adapter=new ViewPagerAdapter(super.getActivity().getSupportFragmentManager(), frag);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		viewPager.setCurrentItem(0);
		viewPager.setOffscreenPageLimit(2);
		
	}
	/**
	 * 处理RadioGroup切换
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int position) {
		// TODO Auto-generated method stub
		
	}

	/**
	 *处理点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.iv_read_main_test_head:
			
			break;
		case R.id.iv_read_main_test_head_female:
			
			break;
		case R.id.iv_read_menu_add:
			
			break;
		case R.id.tv_read_main_pass_opt:
			
			break;
		}
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
		setTag(position);
	}

	private void setTag(int idx){
		RadioButton rb=(RadioButton)group.findViewById(idx+1);
		rb.setChecked(true);
	}
}
