package com.aya.util;



import com.aya.wangyi.R;

import net.tsz.afinal.FinalBitmap;
import android.app.Application;

public class NewsApplication extends Application {

	private FinalBitmap finalBitmap=null;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		finalBitmap=FinalBitmap.create(this);
		finalBitmap.configBitmapLoadThreadSize(3);//Ïß³Ì³ß´ç
		finalBitmap.configDiskCachePath(getFilesDir().toString());//
		finalBitmap.configDiskCacheSize(1024*1024*10);
		int memory=(int)Runtime.getRuntime().maxMemory()/8;
		finalBitmap.configMemoryCacheSize(memory);
		finalBitmap.configLoadingImage(R.drawable.biz_pread_adapter_bg_default);
		finalBitmap.configLoadfailImage(R.drawable.biz_pread_adapter_bg_default);
		//finalBitmap.configBitmapMaxHeight(bitmapHeight);
	}
	
	public FinalBitmap getFinalBitmap(){
		return finalBitmap;
	}
}
