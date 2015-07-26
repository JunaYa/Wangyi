package com.aya.entity;

import com.aya.fragment.DiscoverFragment;
import com.aya.fragment.NewsFragment;
import com.aya.fragment.PcFragment;
import com.aya.fragment.ReadFragment;
import com.aya.fragment.VaFragment;
import com.aya.wangyi.R;






public class TabDb {
	public static String[] getTabsTxt(){
		String[] tabs={"新闻","阅读","视听","发现"," 我"};
		return tabs;
	}
	public static int[] getTabsImg(){
		int[] ids={
				R.drawable.biz_navigation_tab_news,
				R.drawable.biz_navigation_tab_read,
				R.drawable.biz_navigation_tab_va,
				R.drawable.biz_navigation_tab_discovery,
				R.drawable.biz_navigation_tab_pc};
		return ids;
	}
	public static int[] getTabsImgLight(){
		int[] ids={R.drawable.biz_navigation_tab_news_selected,
				R.drawable.biz_navigation_tab_read_selected,
				R.drawable.biz_navigation_tab_va_selected,
				R.drawable.biz_navigation_tab_discovery_selected,
				R.drawable.biz_navigation_tab_pc_selected};
				return ids;
	}
	public static Class[] getFragments(){
		Class[] clz={
				NewsFragment.class,
				ReadFragment.class,
				VaFragment.class,
				DiscoverFragment.class,
				PcFragment.class};
		return clz;
	}
}
