package com.aya.wangyi;



import com.aya.util.CollectionDao;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserLoginActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_login);
		initActionBar();
		initView();
	}

	private EditText etUName,etUPwd;
	
	private Button btLogin;
	private RelativeLayout toRegist;
	private void initView(){
		etUName=(EditText)super.findViewById(R.id.tv_pc_account_username);
		etUPwd=(EditText)super.findViewById(R.id.tv_pc_account_userpwd);
		btLogin=(Button)super.findViewById(R.id.bt_pc_login);
		btLogin.setOnClickListener(this);
		initUser();
		
		toRegist=(RelativeLayout)super.findViewById(R.id.r_pc_goRegist);
		toRegist.setOnClickListener(this);
	}
	
	private void initUser(){
		
	}
	
	private Boolean findUser(){
		Boolean ret=false;
		sp =super.getSharedPreferences("app_user",Context.MODE_PRIVATE);
		int state = sp.getInt("user", 1);
	     if(state==1){
	    	 String name=String.valueOf(etUName.getText());
	    	 String pwd=String.valueOf(etUPwd.getText());
	    	 String user = collectionDao.findUser(name, pwd);
	    	 if(user!=null || !user.equals("")){
	    		 Log.d("aya", "用户已经注册");
	    		 ret=true;
	    	 }
	     }
		return ret;
	}
	/*
	 *处理点击事件 
	 */
	private CollectionDao collectionDao = new CollectionDao(this);
	private SharedPreferences sp;
	private Intent intent;
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bt_pc_login:
			
			
			String uname=String.valueOf(etUName.getText());
			String upwd=String.valueOf(etUPwd.getText());
			
			
			if(findUser()){
				sp=super.getSharedPreferences("app_pramas", Context.MODE_PRIVATE);
				sp.edit().putString("user_login",String.valueOf(etUName.getText())).commit();
				finish();
				super.overridePendingTransition(R.anim.back_zoom_in, R.anim.zoom_out);
			}else{
				Toast.makeText(this,uname+" "+ upwd+"请注册" ,Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.r_pc_goRegist:
			intent = new Intent(this,RegistActivity.class);
			startActivity(intent);
			super.overridePendingTransition(R.anim.zoom_in, R.anim.zoom_out);
			break;
		}
	}
	/**
	 * 
	 */
	/**
	 * 处理头部显示样式
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_login, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			super.onBackPressed();// finish 当前activity
        	super.overridePendingTransition(R.anim.back_zoom_in, R.anim.back_zoom_out);
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	private ActionBar actionBar;
	private void initActionBar(){
		actionBar=super.getActionBar();
		actionBar.show();
		
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeAsUpIndicator(R.drawable.ic_up);
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setDisplayUseLogoEnabled(false);
		//设置logo
		actionBar.setLogo(R.drawable.logo);
		Drawable colorDrawable=new ColorDrawable(android.R.color.transparent);
		actionBar.setIcon(colorDrawable);
		//自定义区域
		actionBar.setDisplayShowCustomEnabled(true);
		TextView tvTitle=new TextView(this);
		tvTitle.setText("登录");
		tvTitle.setTextSize(18);
		int colorVal=getResources().getColor(R.color.white);
		//tvTitle.setTextColor(Color.WHITE);
		tvTitle.setTextColor(colorVal);
		tvTitle.setGravity(Gravity.CENTER);
		actionBar.setCustomView(tvTitle);
	}

	
}
