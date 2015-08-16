package com.cloudy.androidapi.internet.bitmap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.cloudy.androidapi.internet.bitmap.BitmapDownloader.AnimateAppearance;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;

/**
 * 
 * ͼƬ������ʵ��
 */
public class BitmapUtil {
	private static BitmapDownloader mBitmapDownload;
	private static String cachePath;
	
	/**
	 * ��ȡBitmapDownloaderʵ��
	 * @return BitmapDownloaderʵ��,����ȡ��һ��������
	 */
	public static BitmapDownloader getInstance(int errorDrawable, int downloadingDrawable){
		if(mBitmapDownload == null){
			mBitmapDownload = BitmapDownloader.getInstance(0);
			if(!TextUtils.isEmpty(cachePath)){
				mBitmapDownload.setCachePath(cachePath);
			}
			//���ó��ִ���ʱ��ͼƬ
			mBitmapDownload.setErrorDrawable(errorDrawable);
			//����������״̬�µ�ͼƬ
			mBitmapDownload.setInProgressDrawable(downloadingDrawable);
			mBitmapDownload.setAnimateImageAppearance(AnimateAppearance.ANIMATE_NEVER);
			createCachePath(SystemBase.IMAGE_CACHE_PATH);
			setCachePath(SystemBase.IMAGE_CACHE_PATH);
		}
		return mBitmapDownload;
	}
	
	/**
	 * ������ͬʱ�������ͼƬ����
	 * @param maxDownload
	 * @return
	 */
	public static BitmapDownloader getInstance(int maxDownload){
		mBitmapDownload = BitmapDownloader.getInstance(maxDownload);
		if(!TextUtils.isEmpty(cachePath)){
			mBitmapDownload.setCachePath(cachePath);
		}
		return mBitmapDownload;
	}
	/**
	 * ������ͼƬ����λ��
	 * @param path
	 * @return
	 */
	public static BitmapDownloader getInstance(String path, int errorDrawable, int downloadingDrawable){
		getInstance(errorDrawable, downloadingDrawable);
		setCachePath(path);
		return mBitmapDownload;
	}
	
	/**
	 * �������ͼƬ����������ͼƬ����λ��
	 * @param maxDownload
	 * @param path
	 * @return
	 */
	public static BitmapDownloader getInstance(int maxDownload, String path){
		getInstance(maxDownload);
		setCachePath(path);
		return mBitmapDownload;
	}

	/**
	 * ����ͼƬ����·��
	 * @param path
	 */
	public static void setCachePath(String path){
		if(mBitmapDownload != null){
			 mBitmapDownload.setCachePath(path);
		}
	}

	/**
	 * ����ͼƬ����Ŀ¼
	 * @param path ����Ŀ¼·��
	 */
	public static void createCachePath(String path){
		File file = new File(path);
		if(!file.exists()) {
			File parent = file.getParentFile();
			createCachePath(parent.getAbsolutePath());
			file.mkdir();
		}
	}
	
	/**
	 * ͼƬ����ѭ��ѹ�������ͼƬ����1M��ÿ��ѹ��10%�� ֱ��С��1M���˷����ή��λͼ����
	 * @param image Դλͼ
	 * @return ѹ�����λͼ
	 */
	public static Bitmap compressImage(Bitmap image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// ����ѹ������������100��ʾ��ѹ������ѹ��������ݴ�ŵ�baos��
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		int options = 100;
		// ѭ���ж����ѹ����ͼƬ�Ƿ����100kb,���ڼ���ѹ��
		while (baos.toByteArray().length / 1024 > 1024) {
			baos.reset();// ����baos�����baos
			//����ѹ��options%����ѹ��������ݴ�ŵ�baos��
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);
			options -= 10;// ÿ�ζ�����10
		}
		//��ѹ���������baos��ŵ�ByteArrayInputStream��
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		// ��ByteArrayInputStream��������ͼƬ
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
		return bitmap;
	}

	/**
	 * ��λͼ��������ѹ���������ŵ�480 * 800
	 * @param path Դλͼ·��
	 * @return ���ش�����λͼ
	 */
	public static Bitmap comp(String path) {
		Bitmap image = BitmapFactory.decodeFile(path);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		// �ж����ͼƬ����2M,����ѹ������������ͼƬ��BitmapFactory.decodeStream��ʱ���
		if (baos.toByteArray().length / 1024 > 1024 * 2) {
			baos.reset();// ����baos�����baos
			// ����ѹ��50%����ѹ��������ݴ�ŵ�baos��
			image.compress(Bitmap.CompressFormat.JPEG, 50, baos);
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		// ��ʼ����ͼƬ����ʱ��options.inJustDecodeBounds ���true��
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		// ���������ֻ��Ƚ϶���800*480�ֱ��ʣ����ԸߺͿ���������Ϊ
		float hh = 800f;// �������ø߶�Ϊ800f
		float ww = 480f;// �������ÿ��Ϊ480f
		// ���űȡ������ǹ̶��������ţ�ֻ�ø߻��߿�����һ�����ݽ��м��㼴��
		int be = 1;// be=1��ʾ������
		if (w > h && w > ww) {// �����ȴ�Ļ����ݿ�ȹ̶���С����
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {// ����߶ȸߵĻ����ݿ�ȹ̶���С����
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;// �������ű���
		// ���¶���ͼƬ��ע���ʱ�Ѿ���options.inJustDecodeBounds ���false��
		isBm = new ByteArrayInputStream(baos.toByteArray());
		bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
		return compressImage(bitmap);// ѹ���ñ�����С���ٽ�������ѹ��
	}

	/**
	 * ��λͼ�洢��·��ΪdestPath��·����. ���Ŀ���ļ����ڣ��򸲸Ǳ���
	 * @param bitmap Դλͼ
	 * @param destPath �洢Ŀ���ļ�·��
	 */
	public static void writeImage(Bitmap bitmap, String destPath) {
		try {
			File file = new File(destPath);
			if (file.exists()) {
				file.delete();
			}
			if (!file.exists()) {
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				file.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(file);
			if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
				out.flush();
				out.close();
				out = null;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ��ȡ���ʵ�Bitmapƽʱ��ȡBitmap�������������.
	 * @param path ·��.
	 * @param data byte[]����.
	 * @param context ������
	 * @param uri uri
	 * @param target ģ�����߸ߵĴ�С.
	 * @param width �Ƿ��ǿ��
	 * @return Bitmap
	 */
	public static Bitmap getResizedBitmap(String path, byte[] data, Context context, Uri uri, int target, boolean width) {
		Options options = null;
		if (target > 0) {
			Options info = new Options();
			// ��������true��ʱ��decodeʱ��Bitmap���ص�Ϊ�գ�
			// ��ͼƬ��߶�ȡ����Options��.
			info.inJustDecodeBounds = false;
			decode(path, data, context, uri, info);
			int dim = info.outWidth;
			if (!width)
				dim = Math.max(dim, info.outHeight);
			int ssize = sampleSize(dim, target);
			options = new Options();
			options.inSampleSize = ssize;
		}
		Bitmap bm = null;
		try {
			bm = decode(path, data, context, uri, options);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bm;

	}

	/**
	 * ����Bitmap�Ĺ��÷���.
	 * @param path ·��.
	 * @param data byte[]����.
	 * @param context ������
	 * @param uri uri
	 * @param options λͼѡ��
	 * @return bitmap
	 */
	public static Bitmap decode(String path, byte[] data, Context context, Uri uri, BitmapFactory.Options options) {
		Bitmap result = null;
		if (path != null) {
			result = BitmapFactory.decodeFile(path, options);
		} else if (data != null) {
			result = BitmapFactory.decodeByteArray(data, 0, data.length, options);
		} else if (uri != null) {
			// uri��Ϊ�յ�ʱ��contextҲ��ҪΪ��.
			ContentResolver cr = context.getContentResolver();
			InputStream inputStream = null;
			try {
				inputStream = cr.openInputStream(uri);
				result = BitmapFactory.decodeStream(inputStream, null, options);
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ȡ���ʵ�sampleSize. ����ͼ�ʵ�ֶ���2�ı�����.
	 * @param width
	 * @param target
	 * @return
	 */
	private static int sampleSize(int width, int target) {
		int result = 1;
		for (int i = 0; i < 10; i++) {
			if (width < target * 2) {
				break;
			}
			width = width / 2;
			result = result * 2;
		}
		return result;
	}

	/**
	 * bitmapת����base64
	 * @param bitmap
	 * @return ת������ַ���
	 */
	public static String bitmapToBase64(Bitmap bitmap) {
		if (bitmap == null)
			return "";
		return Base64.encodeToString(PhotoUtils.bitmapToByte(bitmap),
				Base64.DEFAULT);
	}
}
