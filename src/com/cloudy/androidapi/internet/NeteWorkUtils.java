package com.cloudy.androidapi.internet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class NeteWorkUtils {
	
	public interface OnImageDownload{
		void imageDownloaded(Bitmap bitmap);
	}
	
	public static class NetWorkThread extends Thread{
		String path;
		OnImageDownload imageDownload;
		//ImageView imageView;
		
		public NetWorkThread(String path,OnImageDownload imageDownload) {
			this.path = path;
			this.imageDownload = imageDownload;
		}
		@Override
		public void run() {
			getURL(path, imageDownload);
		}
	}
	/**
	 * ʹ��URL����web��Դ�ϵ�ͼƬ
	 */
	public static void getURL(String path,OnImageDownload imageDownload){
		try {
			URL url = new URL(path);
			//�õ�������
			InputStream inputStream = url.openStream();
			Bitmap bitmap =  BitmapFactory.decodeStream(inputStream);
			//imageDownload.setImageBitmap(bitmap);
			if(imageDownload != null){
				//�ص�ͼƬ����
				imageDownload.imageDownloaded(bitmap);
			}
			inputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//*******************************************
	/**
	 * get ����ʽ
	 * @param url
	 * @param params
	 * @param onHttpComplete
	 */
	public static void get(String url,String params,OnHttpComplete onHttpComplete){
		new HttpThread(url, params, onHttpComplete,0).start();
	}
	/**
	 * post ����ʽ
	 * @param url
	 * @param params
	 * @param onHttpComplete
	 */
	public static void post(String url,String params,OnHttpComplete onHttpComplete){
		new HttpThread(url, params, onHttpComplete,1).start();
	}
	
	//*******************************************
	
	public static interface OnHttpComplete{
		void onSuccess(String result);
	}
	
	public static class HttpThread extends Thread{
		String url;//��ַ
		String params;//����
		OnHttpComplete onHttpComplete;//�ص�ʵ��
		int method;//���󷽷�
		
		public HttpThread(String url,String params,OnHttpComplete onHttpComplete,int method){
			this.url = url;
			this.params = params;
			this.onHttpComplete = onHttpComplete;
			this.method = method;
		}
		@Override
		public void run() {
			String result = "";
			if(method == 0){
				result = sendGet(url, params);
			}else if(method == 1){
				result = sendPost(url, params);
			}
			if(onHttpComplete != null){
				onHttpComplete.onSuccess(result);
			}
		}
	}
	/**
	 * ʹ��URlConnection����GET����
	 */
	public static String sendGet(String url,String params){
		StringBuilder builder = new StringBuilder();
		try {
			URL realUrl = new URL(url + "?" + params);
			//��ȡ��URLConnection����
			URLConnection urlConnection = realUrl.openConnection();
			//����ͨ����������
			//���ÿͻ��˽�����Щ���͵���Ϣ,ͨ�����ʾ�����������͵���Ϣ
			urlConnection.setRequestProperty("accept", "*/*");
			//���ֳ�����
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			//�������������
			urlConnection.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");
			//ָ���ͻ��˽��ܵ��ַ���
			urlConnection.setRequestProperty("accept-charset", "utf-8;GBK");
			//��������
			urlConnection.connect();
			//��ȡ���е���Ӧ��ͷ���ֶ�
			Map<String, List<String>> headers = urlConnection.getHeaderFields();
			//����������Ӧ��ͷ���ֶ�
			for(String key:headers.keySet()){
				System.out.println(key + "-------------->>" + headers.get(key));
			}
			//��ȡ��������,Ҳ������Ӧ����
			InputStream inputStream = urlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = bufferedReader.readLine();
			while(line != null && line.length() > 0){
				builder.append(line);
				line = bufferedReader.readLine();
			}
			inputStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	//*********************************************************
	
	//ʹ��UrlConnection����post����
	public static String sendPost(String url,String params){
		StringBuilder builder = new StringBuilder();
		URL realUrl;
		try {
			realUrl = new URL(url + "?" + params);
			//��ȡ��URLConnection����
			URLConnection urlConnection = realUrl.openConnection();
			//����ͨ����������
			//���ÿͻ��˽�����Щ���͵���Ϣ,ͨ�����ʾ�����������͵���Ϣ
			//setRequestProperty();����ָ��������ͷ�ֶε�ֵ����ֵ��ֻ�����ɵ�ǰ��URLConnectionʵ���������ӽ���֮ǰ�÷���ֻ�ܱ����á�
			urlConnection.setRequestProperty("accept", "*/*");
			//���ֳ�����
			urlConnection.setRequestProperty("connection", "Keep-Alive");
			//�������������
			urlConnection.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT5.1;SV1)");
			//ָ���ͻ��˽��ܵ��ַ���
			urlConnection.setRequestProperty("accept-charset", "utf-8;GBK");
			urlConnection.setDoOutput(true);//����ʹ��post��ʽ
			urlConnection.setDoInput(true);//���ÿ���ʹ��������
			//��������
			urlConnection.connect();
			//��Ҫʹ�������
			OutputStream outputStream = urlConnection.getOutputStream();
			BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
			bufferedWriter.write(params);
			bufferedWriter.flush();
			outputStream.close();
			Map<String, List<String>> headers = urlConnection.getHeaderFields();
			//����������Ӧ��ͷ���ֶ�
			for(String key:headers.keySet()){
				System.out.println(key + "-------------->>" + headers.get(key));
			}
			//��ȡ��������,Ҳ������Ӧ����
			InputStream inputStream = urlConnection.getInputStream();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			String line = bufferedReader.readLine();
			while(line != null && line.length() > 0){
				builder.append(line);
				line = bufferedReader.readLine();
			}
			inputStream.close();
			bufferedReader.close();
			bufferedWriter.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	
	
	
	
	
	
	

}
