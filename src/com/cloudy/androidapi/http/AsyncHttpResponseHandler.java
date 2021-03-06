package com.cloudy.androidapi.http;

import org.json.JSONArray;
import org.json.JSONObject;
/**
 * 
 * @author Administrator
 * 事件回调类
 */
public class AsyncHttpResponseHandler {
	
	/**
	 * 获取请求结果成功，返回JSONObject
	 * @param jsonObject
	 */
	public void onSuccess(JSONObject jsonObject){
	}
	
	/**
	 * 获取请求成功，返回的是JSONArray
	 * @param jsonArray
	 */
	public void onSuccess(JSONArray jsonArray) {
		
	}
	
	/**
	 * 请求失败
	 * @param result 请求结果
	 * @param statusCode 请求状态码
	 * @param errorResponse 错误信息
	 */
	public void onFailure(String result, int statusCode, String errorResponse) {
		
	}
	
	/**
	 * 开始上传
	 * @param path 上传地址
	 */
	public void onStartUpload(){
		
	}

	/**
	 * 上传进度回调
	 * @param progress 已上传的进度
	 */
	public void onUploadProgress(int progress) {
		
	}
	
	/**
	 * 上传完成回调
	 * @param path 上传的地址
	 */
	public void onUploadCompleted(){
		
	}
}
