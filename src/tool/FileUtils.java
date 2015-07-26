package tool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class FileUtils {
	public static int saveToSdCard(String fileName,Bitmap bitmap){
		int ret=0;
		PrintStream out=null;
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return -1;
		}
		File file=new File(Environment.getExternalStorageDirectory().toString()+File.separator+fileName);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdir();
		}
		try {
			out=new PrintStream(new FileOutputStream(file));
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			ret=-2;
		}finally{
			out.flush();
			out.close();
			if(!bitmap.isRecycled()) bitmap.recycle();
		}
		
		return ret;
	}
	
	public static Bitmap readImageFile(String fileName){
		if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			return null;
		}
		File file=new File(Environment.getExternalStorageDirectory().toString()+File.separator+fileName);
		if(!file.exists()){
			return null;
		}
		return BitmapFactory.decodeFile(fileName);
	}
	
}
