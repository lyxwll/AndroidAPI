package com.cloudy.androidapi.internet;
/**
 * �߳����� �ӿ���
 *DownListener.java
 *DownListener
 * @author Administrator
 */
public interface DownListener {
	/**
	 * ��ʼ����
	 */
	void onStartDownLoad();
	
	/**
	 * ���ؽ��ȱ仯
	 */
	void onCompleteRateChanged(int completeRate);
	
	/**
	 * �������
	 */
	void onDownloadCompleted(String fileName);

}
