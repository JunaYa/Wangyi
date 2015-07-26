package com.aya.fragment;

import java.util.ArrayList; 
import java.util.List;

import com.aya.adapter.ViewPagerAdapter;
import com.aya.wangyi.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class VaFragment extends Fragment 
implements OnCheckedChangeListener, OnPageChangeListener {

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	
	private RadioGroup group;
	private ViewPager viewPager;
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.va_main_layout, null);
			group=(RadioGroup)view.findViewById(R.id.group_va_tab);
			group.check(1);
			group.setOnCheckedChangeListener(this);
			viewPager=(ViewPager)view.findViewById(R.id.viewpager_va);
			viewPager.setOnPageChangeListener(this);
			initPageView();
		}
		ViewGroup parent=(ViewGroup)view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}

	private List<Fragment> list = new ArrayList<Fragment>();
	private ViewPagerAdapter adapter=null;
	//初始化pageview
	private void initPageView(){
		//视频fragment 并且把url传过去
		VaVedioFragment vedioFrag=new VaVedioFragment();
		Bundle bundle1=new Bundle();
		bundle1.putString("vediourl", "http://c.m.163.com/nc/video/list/V9LG4B3A0/n/10-10.html");
		vedioFrag.setArguments(bundle1);
		
		//电台fragment 并且把url传过去
		VaDianTaiFragment diantaiFrag=new VaDianTaiFragment();
		Bundle bundle2=new Bundle();
		bundle2.putString("diantaiurl", "");
		diantaiFrag.setArguments(bundle2);
		
		list.add(vedioFrag);
		list.add(diantaiFrag);
		
		adapter = new ViewPagerAdapter(super.getActivity().getSupportFragmentManager(), list);
		viewPager.setAdapter(adapter);
		viewPager.setOffscreenPageLimit(1);
		viewPager.setCurrentItem(0);
	}
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	private Intent intent=null;
	@Override
	public void onCheckedChanged(RadioGroup group, int position) {
		// TODO Auto-generated method stub
		
		//Toast.makeText(getActivity(), ""+position, Toast.LENGTH_LONG).show();
		if(position==1){
			intent=new Intent();
		}else if(position==2){
		}
		viewPager.setCurrentItem(position-1);
	}
	private void setTag(int idx){
		RadioButton rb=(RadioButton)group.findViewById(idx+1);
		rb.setChecked(true);
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

	
}
