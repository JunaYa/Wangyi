package com.aya.adapter;

import java.util.List; 

import org.w3c.dom.Text;

import com.aya.entity.Channel;
import com.aya.wangyi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

public class ChannelListAdapter extends BaseAdapter {

	private Context mcontext;
	private List<Channel> list;
	public ChannelListAdapter(Context context,List<Channel> list){
		this.mcontext=context;
		this.list=list;
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
	public View getView(int position, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		String item=list.get(position).getName();
		ViewHolder holder=null;
		if(convertView==null){
			holder=new ViewHolder();
			convertView=LayoutInflater.from(mcontext).inflate(R.layout.channel_edit_gv_layout,null);
			holder.tvChannel=(TextView)convertView.findViewById(R.id.edit_item);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		
		holder.tvChannel.setText(item);
		
		return convertView;
	}

	private class ViewHolder{
		public TextView tvChannel;
	}
}
