package com.aya.fragment;

import java.util.ArrayList;
import java.util.List;

import com.aya.adapter.CollectionAdapter;
import com.aya.entity.News;
import com.aya.util.CollectionDao;
import com.aya.wangyi.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class CollectionNewsFragment extends Fragment {

	private ListView collectionList;
	private CollectionAdapter adapter;
	private List<News> list=new ArrayList<News>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view =inflater.inflate(R.layout.collection_tie_layout, null);
//		collectionList=(ListView)view.findViewById(R.id.channel_list);
//		
//		adapter=new CollectionAdapter(list, super.getActivity());
//		collectionList.setAdapter(adapter);
//		load();
		return view;
	}

	private CollectionDao collectionDao;
	private void load(){
		List<News> data=collectionDao.findNews();
		list.addAll(data);
		adapter.notifyDataSetChanged();
	}
	
	
	
		@Override
		public void onDestroyView() {
			// TODO Auto-generated method stub
			super.onDestroyView();
		}
		
	}
