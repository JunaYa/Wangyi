package com.aya.adapter;

import java.util.List;

import net.tsz.afinal.FinalBitmap;

import com.aya.entity.Photo;
import com.aya.util.NewsApplication;
import com.aya.wangyi.PhotoNewsActivity;
import com.aya.wangyi.R;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotoPagerAdapter extends PagerAdapter {

	private Context  mContext;
	FinalBitmap finalBitmap;
	private List<Photo> Newslist;
	
	public PhotoPagerAdapter(Context context, List<Photo> list) {
		super();
		this.mContext = context;
		this.Newslist = list;
		
		PhotoNewsActivity activity=(PhotoNewsActivity)context;
		NewsApplication app=(NewsApplication)activity.getApplicationContext();
		finalBitmap=app.getFinalBitmap();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Newslist.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		
		return view==object;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		container.removeView((View)object);
	}
	
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		Photo news=Newslist.get(position);
		View view = LayoutInflater.from(mContext).inflate(R.layout.photo_item_layout,null);
		ImageView img=(ImageView)view.findViewById(R.id.iv_photo_item);
		finalBitmap.display(img,news.getImgurl());
		
		TextView desc=(TextView)view.findViewById(R.id.tv_content_photo_item_desc);
		desc.setText(news.getNote());
		container.addView(view);
		return view;
	}


}
