package com.aya.fragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import com.aya.adapter.VedioNewsAdapter;
import com.aya.entity.News;
import com.aya.wangyi.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import android.app.Activity;
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

public class VaVedioFragment extends Fragment 
implements OnRefreshListener2<ListView>, OnItemClickListener {

	private FinalHttp afinal;
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		afinal=new FinalHttp();
	}

	//获得要输的数据的url
	private String weburl;
	@Override
	public void setArguments(Bundle bundle) {
		// TODO Auto-generated method stub
		weburl=bundle.getString("vediourl");
	}
	
	private PullToRefreshListView pview;
	private VedioNewsAdapter vedioAdapter;
	private View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if(view==null){
		view=inflater.inflate(R.layout.news_fragment_layout, null);
		pview=(PullToRefreshListView)view.findViewById(R.id.newsList);
		vedioAdapter=new VedioNewsAdapter(getActivity(), list);
		pview.setAdapter(vedioAdapter);
		pview.setMode(Mode.BOTH);
		pview.setOnRefreshListener(this);
		pview.setOnItemClickListener(this);
		load(1,pageSize);
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);
		}
		return view;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	private Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1:
				vedioAdapter.notifyDataSetChanged();
				refreceComplate();
				break;
			}
		}
		
	};
	/**
	 * 加载数据
	 * @param pageNo
	 * @param pageSize
	 */
	private List<News> list=new ArrayList<News>();
	private void load(int pageNo,int pageSize){
		if(pageNo==1)list.clear();
		String url=getUrl(pageNo, pageSize);
		Log.d("aya", url);
		afinal.get(url, new AjaxCallBack<String>(){

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				Log.d("aya", "数据加载失败");
				Toast.makeText(getActivity(), "请检查网络是否连接", Toast.LENGTH_LONG).show();
				refreceComplate();
			}

			@Override
			public void onSuccess(String t) {
				// TODO Auto-generated method stub
				Log.d("aya", t);
				Gson gson=new Gson();
				Map<String, List<News>> map=gson.fromJson(t,
						new TypeToken<HashMap<String, List<News>>>(){}.getType());
				Collection<List<News>> c=map.values();
				if(c==null || c.size()==0){
					Log.d("aya", "数据加载完了");
					Toast.makeText(getActivity(), "数据加载完成", Toast.LENGTH_LONG).show();
					refreceComplate();
					return;
				}
				Iterator<List<News>> i=c.iterator();
				List<News> data=null;
				if(i.hasNext()){
					data=i.next();
				}
				list.addAll(data);
				handler.sendEmptyMessage(1);
			}
			
		});
	}
	//获得要获取数据的url
	private String getUrl(int pageNo,int pageSize){
		String path=weburl.substring(0,weburl.lastIndexOf("/")+1);
		String webName=(pageNo-1)*pageSize+"-"+pageSize+".html";
		return path+webName;
	}
	private void refreceComplate(){
		pview.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				pview.onRefreshComplete(); //取消缓存
			}
		}, 500);
	}
	private int pageNo=1;
	private int pageSize=10;
	@Override
	public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		if(pageNo==1)
			load(pageNo,pageSize);
		
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
		// TODO Auto-generated method stub
		pageNo++;
		load(pageNo,pageSize);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View convertview, int position, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
