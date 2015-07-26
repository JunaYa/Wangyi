package com.aya.adapter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.aya.entity.News;
import com.aya.util.NewsApplication;
import com.aya.wangyi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class VedioNewsAdapter extends BaseAdapter {

	private Context context;
	private List<News> list;
	private FinalBitmap fbmap;
	
	public VedioNewsAdapter(Context context, List<News> list) {
		super();
		this.context = context;
		this.list = list;
		NewsApplication app=(NewsApplication)context.getApplicationContext();
		fbmap=app.getFinalBitmap();
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
	public View getView(int position, View converview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		News news=list.get(position);
		ViewHolder holder=null;
		if(converview==null){
			holder=new ViewHolder();
			converview=LayoutInflater.from(context).inflate(R.layout.va_vedio_item_layout, null);
			holder.img=(ImageView)converview.findViewById(R.id.iv_va_vedio);
			holder.title=(TextView)converview.findViewById(R.id.tv_va_vedio_title);
			holder.playcount=(TextView)converview.findViewById(R.id.tv_va_vedio_playcount);
			holder.length=(TextView)converview.findViewById(R.id.tv_va_vedio_length);
			holder.replycount=(TextView)converview.findViewById(R.id.tv_va_vedio_replaycount);
			converview.setTag(holder);
		}else{
			holder=(ViewHolder)converview.getTag();
		}
		
		//¼ÆËãlength
		int minute=news.getLength()/60;
		int second=news.getLength()%60;
		String len="0" + minute + ":" + second;
		fbmap.display(holder.img,news.getCover());
		holder.length.setText(len);
		holder.title.setText(news.getTitle());
		holder.replycount.setText(news.getReplyCount()+"¸úÌû");
		holder.playcount.setText(news.getPlayCount()+"");
		
		return converview;
	}
	
	private class ViewHolder{
		private ImageView img;
		private TextView title;
		private TextView replycount;
		private TextView playcount;
		private TextView length;
	}
}
