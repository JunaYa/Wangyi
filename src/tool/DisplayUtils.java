package tool;

import android.app.Activity;
import android.util.DisplayMetrics;

public class DisplayUtils {
	
	private static DisplayUtils instance;
	private Activity mActivity;
	private DisplayUtils(Activity mActivity){
		this.mActivity=mActivity;
	}
	public static DisplayUtils getInstance(Activity mActivity){
		if(instance==null){
			instance=new DisplayUtils(mActivity);
		}
		return instance;
	}
	public final  int[] getScreenSize(){
		int[] size=new int[2];
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		size[0]=dm.widthPixels;
		size[1]=dm.heightPixels;
		return size;
	}
	public final static int getWindowWidth(Activity mActivity) {
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public final static int getWindowHeight(Activity mActivity) {
		DisplayMetrics dm = new DisplayMetrics();
		mActivity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
}
