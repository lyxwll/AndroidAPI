package com.cloudy.androidapi.internet.bitmap;

import android.os.Environment;
/**
 * ͼƬ�����������
 *
 */
public class SystemBase {
	/**
	 * androidϵͳSD��·��
	 */
	public static String SDCARDPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	/**
	 * APP�������ݸ�Ŀ¼
	 */
	public static String APP_CACHE_PATH = SDCARDPATH + "/fourteen";
	/**
	 * ������ݻ����ļ�����JSON���ݵ�
	 */
	public static String DATA_CACHE_PATH = APP_CACHE_PATH + "/cache/";
	/**
	 * ���ͼƬ�ļ������ļ�
	 */
	public static String IMAGE_CACHE_PATH = APP_CACHE_PATH + "/image";
}
