package tool;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

/**
 * Bitmap������, ��Ҫ�����޸�
 */
public class BitmaptoCard {
	private static int FREE_SD_SPACE_NEEDED_TO_CACHE = 1;
	private static int MB = 1024 * 1024;

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String dir, Bitmap bm, String filename,
			int quantity, boolean recyle) {
		boolean ret = true;
		if (bm == null) {
			return false;
		}

		if (FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
			bm.recycle();
			bm = null;
			return false;
		}

		File dirPath = new File(dir);

		if (!exists(dir)) {
			dirPath.mkdirs();
		}

		if (!dir.endsWith(File.separator)) {
			dir += File.separator;
		}

		File file = new File(dir + filename);
		OutputStream outStream = null;
		try {
			file.createNewFile();
			outStream = new FileOutputStream(file);
			bm.compress(Bitmap.CompressFormat.JPEG, quantity, outStream);
			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			ret = false;
		} catch (IOException e) {
			e.printStackTrace();
			ret = false;
		} catch (OutOfMemoryError e) {
			e.printStackTrace();
			ret = false;
		} finally {
			if (outStream != null) {
				try {
					outStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (recyle && !bm.isRecycled()) {
				bm.recycle();
				bm = null;
				Log.e("BitmaptoCard", "saveBmpToSd, recyle");
			}
		}

		return ret;
	}

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String dir, Bitmap bm, String filename,
			int quantity) {
		return saveBmpToSd(dir, bm, filename, quantity, false);
	}

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String dir, String srcFile,
			String filename, int quantity) {
		if (srcFile == null) {
			return false;
		}
		Bitmap bmp = BitmapFactory.decodeFile(srcFile);
		return saveBmpToSd(dir, bmp, filename, quantity);
	}

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String dir, String srcFile,
			String filename, int quantity, boolean recyle) {
		if (srcFile == null) {
			return false;
		}
		Bitmap bmp = BitmapFactory.decodeFile(srcFile);
		return saveBmpToSd(dir, bmp, filename, quantity, recyle);
	}

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String dir, String srcFile,
			String filename, int quantity, float size, boolean recyle) {
		if (srcFile == null) {
			return false;
		}
		Bitmap bmp = convertToThumb(readFileToBuffer(srcFile), size);
		return saveBmpToSd(dir, bmp, filename, quantity, recyle);
	}

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String dir, String srcFile,
			String filename, int quantity, float size) {
		if (srcFile == null) {
			return false;
		}
		Bitmap bmp = convertToThumb(readFileToBuffer(srcFile), size);
		return saveBmpToSd(dir, bmp, filename, quantity);
	}

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String dir, Bitmap bmp, String filename,
			int quantity, float size) {
		if (bmp == null) {
			return false;
		}
		bmp = convertToThumb(readBitmap(bmp), size);
		return saveBmpToSd(dir, bmp, filename, quantity);
	}

	/**
	 * ��ȡsdcard·��
	 * 
	 * @return
	 */
	public static String getSdcardPath() {
		return Environment.getExternalStorageDirectory().getPath()
				+ File.separator;
	}

	/**
	 * ��֤�ļ��Ƿ����
	 * 
	 * @param url
	 * @return
	 */
	public static boolean exists(String url) {
		File file = new File(url);

		return file.exists();
	}

	/**
	 * ���sdcard���ÿռ�
	 * 
	 * @return
	 */
	public static int freeSpaceOnSd() {
		StatFs stat = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());

		double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat
				.getBlockSize()) / MB;

		return (int) sdFreeMB;
	}

	/**
	 * Bitmap --> byte[]
	 * 
	 * @param bmp
	 * @return
	 */
	private static byte[] readBitmap(Bitmap bmp) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bmp.compress(Bitmap.CompressFormat.JPEG, 60, baos);
		try {
			baos.flush();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	/**
	 * ����Bitmap��sdcard
	 * 
	 * @param dir
	 * @param bm
	 * @param filename
	 * @param quantity
	 */
	public static boolean saveBmpToSd(String filePath, Bitmap bm, int quantity) {
		if (filePath == null) {
			return false;
		}

		int end = filePath.lastIndexOf(File.separator);
		String dir = filePath.substring(0, end);
		String filename = filePath.substring(end);

		return saveBmpToSd(dir, bm, filename, quantity);
	}

	/**
	 * @description: ͨ���ļ�·������Ӧ�ļ�תΪbyte[]
	 * @param fileName
	 * @return
	 */
	public static byte[] getByte(String fileName) {
		if (fileName == null || "".equals(fileName)) {
			return new byte[0];
		}
		File file = new File(fileName);
		if (file.exists()) {
			try {
				FileInputStream fin = new FileInputStream(fileName);
				int length = fin.available();
				byte[] buffer = new byte[length];
				fin.read(buffer);
				// res = EncodingUtils.getString(buffer, "UTF-8");
				fin.close();
				return buffer;
			} catch (Exception e) {
				Log.e("BitmaptoCard", "getByte fail:" + fileName);
				return new byte[0];
			}
		} else {
			Log.e("BitmaptoCard", "getByte file no exists :" + fileName);
			return new byte[0];
		}

	}

	/**
	 * ��ͼƬ�����������ļ����뵽byte��������
	 * 
	 * @param filePath
	 * @return
	 */
	public static byte[] readFileToBuffer(String filePath) {
		if (filePath == null || filePath.trim().equals("")) {
			Log.e("BitmaptoCard", "readFileToBuffer, path is null:" + filePath);
			return null;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			Log.e("BitmaptoCard", "readFileToBuffer, file is not exists:"
					+ filePath);
			return null;
		}

		byte[] buffer = new byte[(int) file.length()];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			BufferedInputStream bis = new BufferedInputStream(fis);
			bis.read(buffer);
			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return buffer;
	}

	/**
	 * ���ͼƬ�Ƿ񳬹�һ��ֵ��������С
	 * 
	 * @param view
	 * @param strFileName
	 */
	public static Bitmap convertToThumb(byte[] buffer, float size) {
		// ��ȡԭͼ���
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		options.inPurgeable = true;
		options.inInputShareable = true;

		Bitmap bm = BitmapFactory.decodeByteArray(buffer, 0, buffer.length,
				options);

		// �������ű���
		float reSize = options.outWidth / size;

		if (options.outWidth > options.outHeight) {
			reSize = options.outHeight / size;
		}

		if (reSize <= 0) {
			reSize = 1;
		}

		Log.d("BitmaptoCard", "convertToThumb, reSize:" + reSize);

		// ����
		options.inJustDecodeBounds = false;
		options.inSampleSize = (int) reSize;

		if (bm != null && !bm.isRecycled()) {
			bm.recycle();
			bm = null;
			Log.e("BitmaptoCard", "convertToThumb, recyle");
		}

		bm = BitmapFactory.decodeByteArray(buffer, 0, buffer.length, options);

		if (bm == null) {
			Log.e("BitmaptoCard", "convertToThumb, decode fail:" + null);
			return null;
		}

		return bm;
	}

}