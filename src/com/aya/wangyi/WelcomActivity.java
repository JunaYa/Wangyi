package com.aya.wangyi;

import com.google.gson.Gson;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.os.Bundle; 
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class WelcomActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcom);
		afinal=new FinalHttp();
		load();
	}
	
	String url="http://c.m.163.com/nc/weather/5YyX5LqsfOWMl%2BS6rA%3D%3D.html";
	private FinalHttp afinal;
	private void load(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				handler.sendEmptyMessage(1);
			} 
		}).start();
		
		afinal.get(url, new AjaxCallBack<String>(){

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO Auto-generated method stub
				Log.d("aya", "º”‘ÿ ˝æ› ß∞‹£°");
			}

			@Override
			public void onSuccess(String t) {
				// TODO Auto-generated method stub
				Log.d("aya", t);
				Gson gson=new Gson();
			}});
	}

	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			switch(msg.what){
			case 1:
				next();
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	private SharedPreferences sp;
	private void next(){
		sp=super.getSharedPreferences("app_param",Context.MODE_PRIVATE );
		int user = sp.getInt("use", 0);
		if(user==0){
			goIndex();
		}else{
			goHome();
		}
	}
	
	private void goIndex(){
		Intent intent=new Intent(WelcomActivity.this,IndexActivity.class);
		startActivity(intent);
		finish();
	}
	private void goHome(){
		Intent intent=new Intent(WelcomActivity.this,WangYiActivity.class);
		startActivity(intent);
		finish();
	}
}
