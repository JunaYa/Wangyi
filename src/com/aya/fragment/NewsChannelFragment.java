package com.aya.fragment;

import java.util.ArrayList;   
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.aya.adapter.NewsListViewAdapter;
import com.aya.entity.News;
import com.aya.entity.News;
import com.aya.wangyi.NormalNewsActivity;
import com.aya.wangyi.PhotoNewsActivity;
import com.aya.wangyi.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

public class NewsChannelFragment extends Fragment  
	implements OnRefreshListener2<ListView>, OnItemClickListener {


	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		outState.putString("weburl", weburl);
	}

	private PullToRefreshListView lvNewsList;
	//private ListView lvNewsList;
	private NewsListViewAdapter adapter;
	private String weburl;
	private String channelId;
	private String channelName;
	private String hweburl;
	private int order;
	private FinalHttp afinal=null;
	private List<News> data=null;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		afinal=new FinalHttp();
	}
	
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		data=new ArrayList<News>();
		if(view==null){
			if(savedInstanceState!=null){
				weburl=savedInstanceState.getString("weburl");
			}
			 view=inflater.inflate(R.layout.news_fragment_layout, null);
			 lvNewsList=(PullToRefreshListView)view.findViewById(R.id.newsList);
			 lvNewsList.setOnItemClickListener(this);
			 adapter=new NewsListViewAdapter(super.getActivity(),data);
			 lvNewsList.setAdapter(adapter);
			 lvNewsList.setMode(Mode.BOTH);
			 lvNewsList.setRefreshing(true);	
			 lvNewsList.setOnRefreshListener(this);
			 loadData(1,pageSize);
			
		}
		ViewGroup parent=(ViewGroup)view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}
	
	private String getWebUrl(int pageNo,int pageSize){
		String strurl=null;
		if(order==0){
			String path=weburl.substring(0,weburl.lastIndexOf("/")+1);
			String webName=(pageNo-1)*pageSize+"-"+pageSize+".html";
			strurl=path+webName;
		}else if(order==3){
			strurl="http://c.m.163.com/recommend/getSubDocPic?passport=&devId=000000000000000&size="+pageSize;
		}
		return strurl;
	}
	
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1:
				adapter.notifyDataSetChanged();
				refreshComplete();
				break;
			}
		}

	
		
	};
	private void loadData(int pageNo,int pageSize){
		if(pageNo==1)data.clear();
		String url=url=getWebUrl(pageNo, pageSize);
			
		afinal.get(url, new AjaxCallBack<String>() {
			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				Log.d("jereh", "请求失败,请检测网络连接!");
				Toast.makeText(getActivity(), "请求失败,请检测网络连接!", Toast.LENGTH_LONG).show();
				refreshComplete();
			}

			
			@Override
			public void onSuccess(String t) {
				// TODO Auto-generated method stub
				Log.d("jereh",t);
				Gson gson=new Gson();	
				Map<String,List<News>> map=gson.fromJson(t, 
								new TypeToken<HashMap<String,List<News>>>(){}.getType());
				//List<News> list=map.get(channelId);
			    Collection<List<News>> c=map.values();
			    if(c==null||c.size()==0){//数据加载完成
			    	Toast.makeText(getActivity(), "数据加载完成", Toast.LENGTH_LONG).show();
			    	refreshComplete();
			    	return ;
			    }
			    
			    Iterator<List<News>> i=c.iterator();
			    List<News> newsList=null;
			    if(i.hasNext()){
			    	newsList=i.next();
			    }
			    //if(newsList.get(0).getOrder()==1)newsList.remove(0); //移除第一项
			    data.addAll(newsList);
			    handler.sendEmptyMessage(1);
			}
		} );
//	    	refreshComplete();

	}
	@Override
	public void setArguments(Bundle bundle) {
		// TODO Auto-generated method stub
		weburl=bundle.getString("weburl");
		channelId=bundle.getString("id");
		channelName=bundle.getString("name");
		hweburl=bundle.getString("hweburl");
		order=Integer.valueOf(bundle.getString("order"));
		
	}
	private void refreshComplete(){
		lvNewsList.postDelayed(new Runnable() {
	
	            @Override
	            public void run() {
	           	 lvNewsList.onRefreshComplete();
	            }
	    }, 500);
	}
	private int pageNo=1;
	private int pageSize=20;
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		pageNo=1;
		loadData(1, pageSize);
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		pageNo++;
		loadData(pageNo,pageSize);
	}

	/**
	 * 点击显示详细内容
	 */
	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		News news=data.get(position-1);
		String url=news.getDetailUrl();
		String docId=news.getDocid();
		String source=news.getSource();
		String title=news.getTitle();
		String photoId=news.getPhotosetID();
		int replyCount=news.getReplyCount();
		switch(news.getDetailType()){
		case News.NORMAL_TYPE:
			Intent intent=new Intent(super.getActivity(),NormalNewsActivity.class);
			intent.putExtra("url",url);
			intent.putExtra("docId",docId);
			intent.putExtra("source", source);
			intent.putExtra("title", title);
			intent.putExtra("replyCount",""+replyCount);
			super.startActivity(intent);
			super.getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
			break;
		case News.PHOTO_TYPE:
			Intent intentPhoto=new Intent(super.getActivity(),PhotoNewsActivity.class);
			intentPhoto.putExtra("url",url);
			Log.d("test","图片"+ url);
			intentPhoto.putExtra("replyCount",""+replyCount);
			intentPhoto.putExtra("photoId",photoId);
			super.startActivity(intentPhoto);
			super.getActivity().overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
			break;
		}
	}
	
	
}
