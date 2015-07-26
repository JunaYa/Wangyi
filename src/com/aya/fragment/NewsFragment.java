package com.aya.fragment;


import java.util.ArrayList;   
import java.util.List;

import com.aya.adapter.ChannelListAdapter;
import com.aya.adapter.ViewPagerAdapter;
import com.aya.entity.Channel;
import com.aya.entity.ChannelDb;
import com.aya.entity.News;
import com.aya.wangyi.R;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class NewsFragment extends Fragment 
implements OnCheckedChangeListener, OnPageChangeListener, OnClickListener{

	private ViewPager pageView;
	private List<News> list=new ArrayList<News>();
	private List<Fragment> fragment=new ArrayList<Fragment>();
	private ViewPagerAdapter adapter;
	private RadioGroup rgChannel;
	private HorizontalScrollView hsView;
	private View view;
	private View channel;
	private ImageView ivChannelEdit,ivAlert,ivOverFlow;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
			view=inflater.inflate(R.layout.news_main_layout,null);
			rgChannel=(RadioGroup)view.findViewById(R.id.rg_channel_news);
			pageView=(ViewPager)view.findViewById(R.id.view_pager_main_news);
			hsView=(HorizontalScrollView)view.findViewById(R.id.hsView_news);
			rgChannel.setOnCheckedChangeListener(this);
			pageView.setOnPageChangeListener(this);
			initTab(inflater);
			initPageView();
			rgChannel.check(0);
			channel=(View)view.findViewById(R.id.edit_top);
			ivChannelEdit=(ImageView)view.findViewById(R.id.iv_channel_edit);
			ivChannelEdit.setOnClickListener(this);
			ivAlert=(ImageView)view.findViewById(R.id.alert);
			ivOverFlow=(ImageView)view.findViewById(R.id.overflow);
			ivOverFlow.setOnClickListener(this);
		}
		
		ViewGroup parent=(ViewGroup)view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
	
		return view;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}
	
	//初始化pageView
	private void initPageView(){
		List<Channel> channelList=ChannelDb.getSelectedChannel();
		for(int i=0;i<channelList.size();i++){
			Log.d("aya", channelList.get(i).getOrder()+"");
			if(channelList.get(i).getOrder()==1){
				NewsChannelPhotoFragment fgphoto=new NewsChannelPhotoFragment();
				fragment.add(fgphoto);
			}else{
				NewsChannelFragment frag=new NewsChannelFragment();
				Bundle bundle=new Bundle();
				bundle.putString("weburl", channelList.get(i).getWeburl());
				bundle.putString("id", channelList.get(i).getId());
				bundle.putString("name", channelList.get(i).getName());
				bundle.putString("hweburl", channelList.get(i).getHweburl());
				bundle.putString("order",channelList.get(i).getOrder()+"");
				frag.setArguments(bundle);	
				fragment.add(frag);
			}
		}
		
		adapter=new ViewPagerAdapter(super.getActivity().getSupportFragmentManager(), fragment);
		pageView.setAdapter(adapter);
		pageView.setOffscreenPageLimit(2);
		pageView.setCurrentItem(0);
	}

	
	private void setTab(int idx){
		RadioButton rb=(RadioButton)rgChannel.getChildAt(idx);
		rb.setChecked(true);
		int left=rb.getLeft();
		int width=rb.getMeasuredWidth();
		DisplayMetrics metrics=new DisplayMetrics();
		super.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
		int screenWidth=metrics.widthPixels;
		int len=left+width/2-screenWidth/2;
		hsView.smoothScrollTo(len, 0);
	}
	
	private void initTab(LayoutInflater inflater){
		List<Channel> channelList=ChannelDb.getSelectedChannel();
		for(int i=0;i<channelList.size();i++){
			RadioButton rb=(RadioButton)inflater.
					inflate(R.layout.channel_button, null);
			rb.setId(i);
			rb.setText(channelList.get(i).getName());
			RadioGroup.LayoutParams params=new 
					RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,
							RadioGroup.LayoutParams.WRAP_CONTENT);
			rgChannel.addView(rb,params);
		}
		}
	//点击频道转换viewPager内容
	@Override
	public void onCheckedChanged(RadioGroup arg0, int position) {
		// TODO Auto-generated method stub
		pageView.setCurrentItem(position);
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
		setTab(position);
	}
	/**
	 * popuWindow
	 */
	private PopupWindow popuWindow,morePopuWindow;
	private ImageView ivPopuClose;
	private GridView gvselected,gvunselected;
	private ChannelListAdapter adapterSelected,adapterUnSelected;
	private List<Channel> selectedList=null,unselectedList=null;
	private void setMOrePopuWindow(){
		View view=LayoutInflater.from(super.getActivity()).inflate(R.layout.action_list_layout, null);
		morePopuWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,
											ViewGroup.LayoutParams.MATCH_PARENT);
		morePopuWindow.setFocusable(false);
		morePopuWindow.setOutsideTouchable(true);
		morePopuWindow.setAnimationStyle(R.anim.share_out_upshow);
	}
	private void setPopuWindow(){
//		Toast.makeText(getActivity(), "fdsa", Toast.LENGTH_SHORT).show();
		View view=LayoutInflater.from(super.getActivity()).inflate(R.layout.channel_edit_layout, null);
		popuWindow=new PopupWindow(view,ViewGroup.LayoutParams.MATCH_PARENT,
										ViewGroup.LayoutParams.MATCH_PARENT);
		popuWindow.setFocusable(true);
		popuWindow.setOutsideTouchable(true);
		popuWindow.setAnimationStyle(R.anim.share_out_upshow);
		
		ivPopuClose=(ImageView)view.findViewById(R.id.iv_popu_edit_close);
		ivPopuClose.setOnClickListener(this);
		
		gvselected=(GridView)view.findViewById(R.id.gvSelected);
		selectedList=new ArrayList<Channel>();
		adapterSelected=new ChannelListAdapter(super.getActivity(), selectedList);
		gvselected.setAdapter(adapterSelected);
		
		
		gvunselected=(GridView)view.findViewById(R.id.gvunSelected);
		unselectedList=new ArrayList<Channel>();
		adapterUnSelected=new ChannelListAdapter(super.getActivity(), unselectedList);
		gvunselected.setAdapter(adapterUnSelected);
		
		gvunselected.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "fdsa", Toast.LENGTH_SHORT).show();
				selectedList.add(unselectedList.get(position));
				adapterSelected.notifyDataSetChanged();
				
				unselectedList.remove(unselectedList.get(position));
				adapterUnSelected.notifyDataSetChanged();
				
			}
		});
		gvselected.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(),"ds",Toast.LENGTH_LONG).show();
				rgChannel.check(position);
				popuWindow.dismiss();
			}
		});
		gvselected.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				return false;
			}
		});
	
	}
	//加载频道数据
	private void loadChannel(){
		selectedList.addAll(ChannelDb.getSelectedChannel());
		adapterSelected.notifyDataSetChanged();
		
		unselectedList.addAll(ChannelDb.getUnselectedChannel());
		adapterUnSelected.notifyDataSetChanged();
	}

	private  int ret=0;
	@TargetApi(19)
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.iv_channel_edit:
			setPopuWindow();
			loadChannel();
			popuWindow.showAsDropDown(channel, 0,0,Gravity.TOP);
			break;
		case R.id.iv_popu_edit_close:
			popuWindow.dismiss();
			break;
		case R.id.overflow:
		   
			if(ret==0){
				setMOrePopuWindow();
				morePopuWindow.showAsDropDown(channel, 0,0,Gravity.TOP);
				ret=1;
			}else if(ret==1){
				morePopuWindow.dismiss();
				ret=0;
			}
			break;
		}
	}


	

}
