package com.cloudy.androidapi.internet;
/**
 * 线程下载 接口类
 *DownListener.java
 *DownListener
 * @author Administrator
 */
public interface DownListener {
	/**
	 * 开始下载
	 */
	void onStartDownLoad();
	
	/**
	 * 下载进度变化
	 */
	void onCompleteRateChanged(int completeRate);
	
	/**
	 * 下载完成
	 */
	void onDownloadCompleted(String fileName);

}
