package com.aya.fragment;

import com.aya.wangyi.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class ReadCommendFragment extends Fragment implements OnClickListener, OnCheckedChangeListener {

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	private ImageView ivbHead,ivgHead;
	private TextView tvPass;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.read__main_commentd_layout, null);
		
		ivbHead=(ImageView)view.findViewById(R.id.iv_read_main_test_head);
		ivgHead=(ImageView)view.findViewById(R.id.iv_read_main_test_head_female);
		
		tvPass=(TextView)view.findViewById(R.id.tv_read_main_pass_opt);
		
		
		ivbHead.setOnClickListener(this);
		ivgHead.setOnClickListener(this);
		tvPass.setOnClickListener(this);
		
		
		
		return view;
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	/**
	 * 处理RadioGroup切换
	 */
	@Override
	public void onCheckedChanged(RadioGroup group, int position) {
		// TODO Auto-generated method stub
		
	}

	/**
	 *处理点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.iv_read_main_test_head:
			
			break;
		case R.id.iv_read_main_test_head_female:
			
			break;
		case R.id.tv_read_main_pass_opt:
			
			break;
		}
	}

}
