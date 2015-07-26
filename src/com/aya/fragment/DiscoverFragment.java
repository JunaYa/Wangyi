package com.aya.fragment;

import java.util.ArrayList;
import java.util.List;

import com.aya.adapter.IndexViewPagerAdapter;
import com.aya.wangyi.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

public class DiscoverFragment extends Fragment implements OnPageChangeListener {

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	private ViewPager viewPager;
	private List<ImageView> list=new ArrayList<ImageView>();
	private IndexViewPagerAdapter adapter;
	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stubs
		super.onDestroyView();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View view = inflater.inflate(R.layout.discover_main_layout, null);
		viewPager=(ViewPager)view.findViewById(R.id.viewpager_discover_topimg);
		adapter=new IndexViewPagerAdapter(super.getActivity(), list);
		viewPager.setAdapter(adapter);
		viewPager.setOnPageChangeListener(this);
		load();
		return view;
	}
	
	private void load(){
		ImageView img1=new ImageView(super.getActivity());
		img1.setImageResource(R.drawable.sence02);
		img1.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		list.add(img1);
		
		ImageView img2=new ImageView(super.getActivity());
		img2.setImageResource(R.drawable.sence01);
		img2.setLayoutParams(new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		list.add(img2);
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
	@Override
	public void onPageSelected(int position) {
		// TODO Auto-generated method stub
		
	}
	
		
		
		
}
