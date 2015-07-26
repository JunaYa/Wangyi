package com.aya.wangyi;

import com.aya.util.CollectionDao;

import android.os.Bundle; 
import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
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
import android.widget.TextView;

public class RegistActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		initView();
		initActionBar();
	}

	private Button button;
	private EditText uname,pwd;
	private void initView(){
		uname=(EditText)super.findViewById(R.id.et_regist_name);
		pwd=(EditText)super.findViewById(R.id.et_regist_pwd);
		button=(Button)super.findViewById(R.id.bt_regist);
		button.setOnClickListener(this);
	}
	 /**
	  * �洢�����أ������ԣ�
	  */
	private CollectionDao collection = new CollectionDao(this);
	private SharedPreferences sp;
	private void save(){
		String strname=String.valueOf(uname.getText());
		String strpwd=String.valueOf(pwd.getText());
		collection.insertUser(strname, strpwd);
		sp=super.getSharedPreferences("app_user", MODE_PRIVATE);
		sp.edit().putInt("user",1).commit();
	}
	/**
	 * �������¼�
	 */
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.bt_regist:
			save();
			finish();
			break;
		}
	}
	/**
	 * ����ͷ����ʾ��ʽ
	 */
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.regist, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case android.R.id.home:
			super.onBackPressed();// finish ��ǰactivity
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
		//����logo
		actionBar.setLogo(R.drawable.logo);
		Drawable colorDrawable=new ColorDrawable(android.R.color.transparent);
		actionBar.setIcon(colorDrawable);
		//�Զ�������
		actionBar.setDisplayShowCustomEnabled(true);
		TextView tvTitle=new TextView(this);
		tvTitle.setText("ע����������");
		tvTitle.setTextSize(18);
		int colorVal=getResources().getColor(R.color.white);
		//tvTitle.setTextColor(Color.WHITE);
		tvTitle.setTextColor(colorVal);
		tvTitle.setGravity(Gravity.CENTER);
		actionBar.setCustomView(tvTitle);
	}

}

