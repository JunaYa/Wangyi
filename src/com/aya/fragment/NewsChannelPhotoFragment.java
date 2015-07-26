package com.aya.fragment;
 
import java.util.ArrayList;  
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import com.aya.adapter.NewsPhotoAdapter;
import com.aya.entity.NewsPhoto;
import com.aya.wangyi.PhotoNewsActivity;
import com.aya.wangyi.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

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
import android.widget.Toast;

public class NewsChannelPhotoFragment extends Fragment implements OnItemClickListener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	private FinalHttp afinal;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		afinal=new FinalHttp();
		
	}

	private PullToRefreshListView pview;
	private NewsPhotoAdapter adapter;
	private List<NewsPhoto> list=new ArrayList<NewsPhoto>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.news_fragment_layout,null);
		pview=(PullToRefreshListView)view.findViewById(R.id.newsList);
		adapter=new NewsPhotoAdapter(super.getActivity(), list);
		pview.setOnItemClickListener(this);
		pview.setAdapter(adapter);
		load();
		return view;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	private String weburl;
	@Override
	public void setArguments(Bundle bundle) {
		// TODO Auto-generated method stub
		weburl=bundle.getString("weburl");
		Log.d("aya", weburl);
	}

	private void refreshComplete(){
		pview.postDelayed(new Runnable() {
	
	            @Override
	            public void run() {
	           	 pview.onRefreshComplete();
	            }
	    }, 500);
	}
	
	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1:
				adapter.notifyDataSetChanged();
				refreshComplete();
				break;
			}
		}};
	/*
	 * º”‘ÿ ˝æ›
	 */
	private void load(){
		String url="http://c.m.163.com/photo/api/list/0096/4GJ60096.json";
		afinal.get(url, new AjaxCallBack<String>(){

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				Log.d("aya", "º”‘ÿ ß∞‹");
				Toast.makeText(getActivity(), "«ÎºÏ≤‚Õ¯¬Á£¨", Toast.LENGTH_LONG).show();
				refreshComplete();
			}

			@Override
			public void onSuccess(String t) {
				// TODO Auto-generated method stub
				Gson gson=new Gson();
				List<NewsPhoto> data=gson.fromJson(t,
						new TypeToken<List<NewsPhoto>>(){}.getType() );
				list.addAll(data);
				Log.d("test", data.get(0).getCover());
				handler.sendEmptyMessage(1);
				
			}});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
		// TODO Auto-generated method stub
		NewsPhoto photoitem=list.get(position+1);
		String url="http://c.m.163.com/photo/api/set/0096/"+photoitem.getSetid()+".json";
		Intent intentPhoto=new Intent(super.getActivity(),PhotoNewsActivity.class);
		intentPhoto.putExtra("url",url);
		intentPhoto.putExtra("replyCount",""+photoitem.getReplynum());
		super.startActivity(intentPhoto);
		super.getActivity().overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
	}
}
