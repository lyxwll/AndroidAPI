package com.cloudy.androidapi.http;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author Administrator
 * �¼��ص���
 */
public class AsyncHttpResponseHandler {
	
	/**
	 * ��ȡ�������ɹ�������JSONObject
	 * @param jsonObject
	 */
	public void onSuccess(JSONObject jsonObject){
	}
	
	/**
	 * ��ȡ����ɹ������ص���JSONArray
	 * @param jsonArray
	 */
	public void onSuccess(JSONArray jsonArray) {
		
	}
	
	/**
	 * ����ʧ��
	 * @param result ������
	 * @param statusCode ����״̬��
	 * @param errorResponse ������Ϣ
	 */
	public void onFailure(String result, int statusCode, String errorResponse) {
		
	}
	
	/**
	 * ��ʼ�ϴ�
	 * @param path �ϴ���ַ
	 */
	public void onStartUpload(){
		
	}

	/**
	 * �ϴ����Ȼص�
	 * @param progress ���ϴ��Ľ���
	 */
	public void onUploadProgress(int progress) {
		
	}
	
	/**
	 * �ϴ���ɻص�
	 * @param path �ϴ��ĵ�ַ
	 */
	public void onUploadCompleted(){
		
	}
}
