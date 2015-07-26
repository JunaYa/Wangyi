package com.aya.adapter;

import java.util.List;
import java.util.Map;

import com.aya.entity.News;
import com.aya.util.NewsApplication;
import com.aya.wangyi.R;

import net.tsz.afinal.FinalBitmap;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;


@SuppressLint("NewApi")
public class NewsListViewAdapter  extends BaseAdapter implements OnClickListener {

	private Context mContext;
	private List<News> newsList;
	
	private final int TYPE_COUNT=4;
	private FinalBitmap fianlBitmap;
	
	
	public NewsListViewAdapter(Context context,List<News> newsList){
		this.mContext=context;
		this.newsList=newsList;
		initPopupWindow();
	
		NewsApplication app=(NewsApplication)context.getApplicationContext();
		fianlBitmap=app.getFinalBitmap();
	}
	/**
	 * 返回数据项的显示类型数据
	 * 0 1 2
	 */
	@Override
	public int getItemViewType(int position) {
		
		// TODO Auto-generated method stub
		return newsList!=null?newsList.get(position).getStyle():-1;
	}
	/**
	 * 返回类型个数
	 */
	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return TYPE_COUNT;
	}



	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsList!=null?newsList.size():0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return newsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		News item=newsList.get(position);
		if(convertView==null){
			holder=new ViewHolder();
			//将layout.xml转换为View
			switch(item.getStyle()){
			case 0:
				convertView=LayoutInflater.from(mContext).inflate(R.layout.news_item, null);
				holder.tvDigest=(TextView)convertView.findViewById(R.id.tvDigest);
				holder.tvReply=(TextView)convertView.findViewById(R.id.tvReply);
				break;
			case 1:
				convertView=LayoutInflater.from(mContext).inflate(R.layout.news_item2, null);
				holder.ivExtImg1=(ImageView)convertView.findViewById(R.id.ivExtImg1);
				holder.ivExtImg2=(ImageView)convertView.findViewById(R.id.ivExtImg2);
				holder.tvReply=(TextView)convertView.findViewById(R.id.tvReply);
				break;
			case 2:
				convertView=LayoutInflater.from(mContext).inflate(R.layout.news_item3, null);
				holder.tvDigest=(TextView)convertView.findViewById(R.id.tvDigest);
				break;
			case 3:
				convertView=LayoutInflater.from(mContext).inflate(R.layout.news_item1,null);
				break;
			}
			holder.tvTilte=(TextView)convertView.findViewById(R.id.tvTitle);
			holder.ivImgUrl=(ImageView)convertView.findViewById(R.id.ivImgUrl);
			
			convertView.setTag(holder);//记录个标识
		}else{
			holder=(ViewHolder)convertView.getTag();
		}
		holder.tvTilte.setText(item.getTitle());
		fianlBitmap.display(holder.ivImgUrl, item.getImgsrc());
		if(item.getDigest()!=null&&holder.tvDigest!=null){
			holder.tvDigest.setText(item.getDigest());
		}
		List<Map<String,String>> list=item.getImgextra();
		if(list!=null&&list.size()!=0&&holder.ivExtImg1!=null){
			fianlBitmap.display(holder.ivExtImg1, 
					list.get(0).get("imgsrc"));
			fianlBitmap.display(holder.ivExtImg2, list.get(1).get("imgsrc"));
		}
		if(holder.tvReply!=null){
			holder.tvReply.setText(item.getReplyCount()+"跟贴");
		}
		
		return convertView;
	}
	
	
	private class ViewHolder{
		private TextView tvTilte;
		private TextView tvDigest;
		private TextView tvReply;
		private ImageView ivImgUrl;
		private ImageView ivExtImg1;
		private ImageView ivExtImg2;
		
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	private void initPopupWindow(){
		
	}




}
