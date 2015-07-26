package com.aya.adapter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.aya.entity.NewsPhoto;
import com.aya.util.NewsApplication;
import com.aya.wangyi.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsPhotoAdapter extends BaseAdapter {

	private Context mcontext;
	private List<NewsPhoto> list;
	private FinalBitmap bitmap;
	
	public NewsPhotoAdapter(Context context, List<NewsPhoto> list) {
		super();
		this.mcontext = context;
		this.list = list;
		NewsApplication app=(NewsApplication)context.getApplicationContext();
		bitmap=app.getFinalBitmap();
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
	public View getView(int position, View convertview, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertview==null){
			holder =new ViewHolder();
			convertview=LayoutInflater.from(mcontext).inflate(R.layout.news_picture_item1_layour, null);
			holder.img=(ImageView)convertview.findViewById(R.id.iv_news_picture);
			holder.desc=(TextView)convertview.findViewById(R.id.tv_news_picture_desc);
			holder.imgcount=(TextView)convertview.findViewById(R.id.tv_news_picture_count);
			holder.replyCount=(TextView)convertview.findViewById(R.id.tv_news_picture_tie);
			convertview.setTag(holder);
		}else{
			holder=(ViewHolder)convertview.getTag();
		}
		NewsPhoto news=list.get(position);
		bitmap.display(holder.img, news.getClientcover1());
		holder.desc.setText(news.getSetname());
		holder.imgcount.setText(news.getImgsum()+"pics");
		holder.replyCount.setText(news.getReplynum()+"¸úÌû");
		return convertview;
	}

	private class ViewHolder{
		private ImageView img;
		private TextView desc;
		private TextView replyCount;
		private TextView imgcount;
	};
}
