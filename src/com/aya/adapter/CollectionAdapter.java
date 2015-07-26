package com.aya.adapter;

import java.util.List;

import com.aya.entity.News;
import com.aya.wangyi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CollectionAdapter extends BaseAdapter {

	private List<News> list;
	private Context mContext;
	
	
	public CollectionAdapter(List<News> list, Context context) {
		super();
		this.list = list;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(view==null){
			holder=new ViewHolder();
			view=LayoutInflater.from(mContext).inflate(R.layout.collection_news_item_layout,null);
			holder.title=(TextView)view.findViewById(R.id.tv_collection_title);
			holder.desc=(TextView)view.findViewById(R.id.tv_collection_desc);
			holder.tie=(TextView)view.findViewById(R.id.tv_collection_tie);
			view.setTag(holder);
		}else{
			holder=(ViewHolder)view.getTag();
		}
		News news=list.get(position);
		holder.title.setText(news.getTitle());
		holder.desc.setText(news.getDigest());
		holder.tie.setText(news.getReplyCount()+"¸úÌû");
		return view;
	}
	
	private class ViewHolder{
		private TextView title;
		private TextView desc;
		private TextView tie;
	}

}
