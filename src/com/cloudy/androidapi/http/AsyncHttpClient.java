package com.cloudy.androidapi.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * �ļ��ϴ�  ������
 *AsyncHttpClient.java
 *AsyncHttpClient
 * @author Administrator
 */
public class AsyncHttpClient implements OnCompleteListener{
	//�̳߳��������
	private static final int MAX_THREAD_POOL_NUM = 5; 
	
	private static AsyncHttpClient asyncHttpClient;
	
	//�������е��߳��б�
	private static ArrayList<HttpClientThread> runningHttpClientThreads;
	//�Ŷӵ��߳��б�
	private static ArrayList<HttpClientThread> queuedHttpClientThreads;
	
	
	private AsyncHttpClient() {
		//��ʼ���������е��б���Ŷӵ��б�
		if(runningHttpClientThreads == null) {
			runningHttpClientThreads = new ArrayList<HttpClientThread>();
		}
		if(queuedHttpClientThreads == null) {
			queuedHttpClientThreads = new ArrayList<HttpClientThread>();
		}
	}
	
	public static AsyncHttpClient getInstance() {
		if(asyncHttpClient ==null) {
			asyncHttpClient = new AsyncHttpClient();
		}
		return asyncHttpClient;
	}

	public void get(String serverAddress, String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
		//������һ�������߳�
		HttpClientThread clientThread = new HttpClientThread(serverAddress, url, params, asyncHttpResponseHandler, 0, this);
		//���뵽�ŶӶ�����
		queuedHttpClientThreads.add(clientThread);
		//�������̻߳�δ��,�ҵȴ��̶߳����л����߳�
		if(runningHttpClientThreads.size() <=  MAX_THREAD_POOL_NUM && queuedHttpClientThreads.size() > 0) {
			//�ӵȴ�������ȡ����һ�������뵽�����߳���ȥִ��
			HttpClientThread httpClientThread = queuedHttpClientThreads.remove(0);
			if(httpClientThread != null) {
				try {
					if(!httpClientThread.isRunning) {
						httpClientThread.start();
						runningHttpClientThreads.add(httpClientThread);
					}
				} catch (IllegalThreadStateException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void post(String serverAddress, String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
		//������һ�������߳�
		HttpClientThread clientThread = new HttpClientThread(serverAddress, url, params, asyncHttpResponseHandler, 1, this);
		//���뵽�ŶӶ�����
		queuedHttpClientThreads.add(clientThread);
		//�������̻߳�δ��,�ҵȴ��̶߳����л����߳�
		if(runningHttpClientThreads.size() <=  MAX_THREAD_POOL_NUM && queuedHttpClientThreads.size() > 0) {
			//�ӵȴ�������ȡ����һ�������뵽�����߳���ȥִ��
			HttpClientThread httpClientThread = queuedHttpClientThreads.remove(0);
			if(httpClientThread != null) {
				try {
					if(!httpClientThread.isRunning) {
						httpClientThread.start();
						runningHttpClientThreads.add(httpClientThread);
					}
				} catch (IllegalThreadStateException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * �ļ��ϴ��� �������ϴ��ļ�, ���ļ���С���ܳ���2M
	 * ʹ�÷���
	 * HttpParams httpParams = new HttpParams();
	 * httpParams.put("12.png",  Environment.getExternalStorageDirectory().toString() + File.separator + "12.png");
	 * httpParams.put("13.png",  Environment.getExternalStorageDirectory().toString() + File.separator + "13.png");
	 * HttpClientUtils.getInstance().upload("http://192.168.1.219/", "web/likun/single_upload.php", httpParams, new AsyncHttpResponseHandler(){
	 *		@Override
	 *		public void onStartUpload() {
	 *			System.out.println("��ʼ�ϴ�");
	 *		}
	 *		
	 *		@Override
	 *		public void onUploadProgress(int progress) {
	 *			System.out.println("upload progress=" + progress);
	 *			Message message = new Message();
	 *			message.what = 124;
	 *			message.arg1 = progress;
	 *			handler.sendMessage(message);
	 *		}
	 *		
	 *		@Override
	 *		public void onUploadCompleted() {
	 *			System.out.println("�ϴ����");
	 *		}
	 *		
	 *		@Override
	 *		public void onSuccess(JSONArray jsonArray) {
	 *			System.out.println("ͼƬ�ڷ������ĵ�ַ:" + jsonArray.toString());
	 *		}
	 *		
	 *		@Override
	 *		public void onFailure(String result, int statusCode,String errorResponse) {
	 *			System.out.println("result=" + result + ", statusCode=" + statusCode + ", errorResponse=" + errorResponse);
	 *		}
	 *		
	 *		
	 *	});
	 */
	public void upload(String serverAddress, String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
		//������һ�������߳�
		HttpClientThread clientThread = new HttpClientThread(serverAddress, url, params, asyncHttpResponseHandler, 2, this);
		//���뵽�ŶӶ�����
		queuedHttpClientThreads.add(clientThread);
		//�������̻߳�δ��,�ҵȴ��̶߳����л����߳�
		if(runningHttpClientThreads.size() <=  MAX_THREAD_POOL_NUM && queuedHttpClientThreads.size() > 0) {
			//�ӵȴ�������ȡ����һ�������뵽�����߳���ȥִ��
			HttpClientThread httpClientThread = queuedHttpClientThreads.remove(0);
			if(httpClientThread != null) {
				try {
					if(!httpClientThread.isRunning) {
						httpClientThread.start();
						runningHttpClientThreads.add(httpClientThread);
					}
				} catch (IllegalThreadStateException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ��һ�������߳�ִ����ʱ����ص��˷���
	 * �����������б����Ƴ�������
	 * �ڴ��Ŷ��б����ó�һ������ִ��
	 */
	@Override
	public void onComplete(HttpClientThread clientThread) {
		System.out.println("onComplete");
		//��ͬ����
		synchronized (runningHttpClientThreads) {
			if(runningHttpClientThreads.contains(clientThread)) {
				//�Ӷ�����һ�δ��߳�
				runningHttpClientThreads.remove(clientThread);
				System.out.println("runningHttpClientThreads.size()=" + runningHttpClientThreads.size());
			}
		}
		//�ӵȴ������߳���ȡ��һ���̲߳�ִ��
		//���û�м���������������һ�����߳�ִ��
		if(runningHttpClientThreads.size() <= MAX_THREAD_POOL_NUM && queuedHttpClientThreads.size() > 0) {
			HttpClientThread httpClientThread = queuedHttpClientThreads.remove(0);
			if(httpClientThread != null) {
				try {
					if(!httpClientThread.isRunning) {
						httpClientThread.start();
						runningHttpClientThreads.add(httpClientThread);
					}
				} catch (IllegalThreadStateException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ���������߳�, ����get��post
	 * @author eibit
	 */
	protected class HttpClientThread extends Thread{
		private String serverAddress;
		private String url;
		private HttpParams params;
		private AsyncHttpResponseHandler asyncHttpResponseHandler;
		//requestMethod 0 get, 1 post 
		private int requestMethod;
		
		private int statusCode;
		private boolean isSuccess = false;
		private String errorMsg;
		//�Ƿ���������
		private boolean isRunning = false;
		//�Ƿ��Ѿ����
		//private boolean isComplete= false;
		//ִ������¼������ӿ�
		private OnCompleteListener listener;
		
		
		public HttpClientThread(String serverAddress, String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler, int requestMethod, OnCompleteListener listener) {
			this.serverAddress = serverAddress;
			this.url = url;
			this.params = params;
			this.asyncHttpResponseHandler = asyncHttpResponseHandler;
			this.requestMethod = requestMethod;
			this.listener = listener;
		}
		
		public void run() {
			isRunning = true;
			System.out.println(Thread.currentThread().getName() + " is Running");
			if(requestMethod == 0) {
				//GET
				doGet(serverAddress, url, params, asyncHttpResponseHandler);
			} else if (requestMethod == 1){
				//POST
				doPost(serverAddress, url, params, asyncHttpResponseHandler);
			} else if(requestMethod == 2) {
				doUpload(serverAddress, url, params, asyncHttpResponseHandler);
			}
			//isComplete = true;
			if(listener != null) {
				listener.onComplete(this);
			}
		}
		
		/**
		 * ƴ��GET������URL��ַ
		 * @param serverAddress �����server��ַǰ׺
		 * @param url �����·����ַ
		 * @param params ����Ĳ����б�
		 * @return ƴ�Ӻ��������URL��ַ
		 */
		private String getGetUrl(String serverAddress, String url, HttpParams params) {
			String realUrl ="";
			//ȥ����ַ�����/
			if(serverAddress != null && serverAddress.charAt(serverAddress.length() - 1) == '/') {
				serverAddress = serverAddress.substring(0, serverAddress.length() -1);
			}
			//ȥ��urlǰ���/
			if(url != null && url.length() > 0 && url.charAt(0) == '/') {
				url = url.substring(1, url.length());
			}
			//ȥ��url����ģ�
			if(url != null && url.length() > 0 && url.charAt(url.length() - 1) == '?') {
				url = url.substring(0, url.length() -1);
			}
			if(params != null && params.getParamsCount() > 0) {
				realUrl = serverAddress + "/" + url + "?";
				for(int i=0; i< params.getParamsCount(); i++) {
					Param param = params.get(i);
					if(i != 0) { //�����Ż�ѡ��
						if(param.vObject != null) {
							try {
								String value = URLEncoder.encode(param.vObject.toString(), "utf-8");
								realUrl += "&" + param.key + "=" + value; 
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								realUrl += "&" + param.key + "=" + param.vObject; 
							}
						} else {
							realUrl += "&" + param.key + "="; 
						}
					} else {
						if(param.vObject != null) {
							try {
								String value = URLEncoder.encode(param.vObject.toString(), "utf-8");
								realUrl += param.key + "=" + value;
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								realUrl += param.key + "=" + param.vObject;
							}
						} else {
							realUrl += param.key + "=";
						}
					}
				}
			} else {
				realUrl = serverAddress + "/" + url;
			}
			return realUrl;
		}
		
		/**
		 * ִ��get����
		 * @param serverAddress  �����server��ַǰ׺
		 * @param url �����·����ַ
		 * @param params  ����Ĳ����б�
		 * @param asyncHttpResponseHandler �������ʱ�Ļص��ӿڶ���
		 */
		private void doGet(String serverAddress, String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
			String realUrl =getGetUrl(serverAddress, url, params);
			System.out.println("get realUrl="+ realUrl);
			//String response= getResponse(realUrl); //���� Apache HttpClient
			String response = getResponse(realUrl);
			if(isSuccess == true) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					if(asyncHttpResponseHandler != null) {
						asyncHttpResponseHandler.onSuccess(jsonObject);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					try {
						JSONArray jsonArray = new JSONArray(response);
						if(asyncHttpResponseHandler != null) {
							asyncHttpResponseHandler.onSuccess(jsonArray);
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
						if(asyncHttpResponseHandler != null) {
							asyncHttpResponseHandler.onFailure(response, statusCode, "Convert result to JSON ERROR, please check it");
						}
					}
				}
			} else {
				if(asyncHttpResponseHandler != null) {
					asyncHttpResponseHandler.onFailure(response, statusCode, errorMsg);
				}
			}
			
		}
		
		/**
		 * ʹ��HttpUrlConnection ִ��GET����
		 * @param realUrl �����������URl��ַ
		 * @return ����ķ��ؽ��
		 */
		private String myGet(String realUrl) {
			PrintWriter out = null;
			BufferedReader in = null;
			String result = "";
			try {
				URL url = new URL(realUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(10 * 1000);
				connection.setRequestMethod("GET");
				connection.setDoInput(true);
				connection
						.setRequestProperty(
								"Accept",
								"image/gif, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-app lication, application/cnd.ms- excel, application/vnd.ms-powerpoint, application/msword, */*");
				connection.setRequestProperty("Accept-Language", "zh_CN");
				connection.setRequestProperty("Charset", "UTF-8");
				connection
						.setRequestProperty(
								"User-Agent",
								"Mozilla/4.0(compatible; MSIE 7.0; Windows NT 5.2; Trident/4.0; . NET CTR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
				connection.setRequestProperty("Connection", "Keep-Alive");
				statusCode = connection.getResponseCode();
				if(statusCode == HttpURLConnection.HTTP_OK) {
					isSuccess = true;
					// ����BufferedReader����������ȡURL����Ӧ
					in = new BufferedReader(new InputStreamReader(
							connection.getInputStream()));
					String line;
					while ((line = in.readLine()) != null) {
						System.out.println("line=" + line);
						result += "\n" + line;
					}
				} else {
					isSuccess = false;
				}
				connection.disconnect();
			} catch (MalformedURLException e) {
				isSuccess = false;
				errorMsg = "url is not correct, please check it";
				e.printStackTrace();
			} catch (ProtocolException e) {
				isSuccess = false;
				errorMsg = "unknown protocol, url is not correct, please check it";
				e.printStackTrace();
			} catch (SocketTimeoutException e) {
				isSuccess = false;
				errorMsg = "connection timeout";
				e.printStackTrace();
			} catch (IOException e) {
				isSuccess = false;
				errorMsg = "IOexception occur";
				e.printStackTrace();
			}  finally {
				if(out != null) {
					out.close();
				}
				try {
					if(in != null) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//�����������ԭ��
			switch(statusCode) {
			case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
				errorMsg = "connect to server time out";
				break;
			case HttpURLConnection.HTTP_INTERNAL_ERROR:
				errorMsg = "connect to server internal error";
				break;
			case HttpURLConnection.HTTP_NOT_FOUND:
				errorMsg = "the url not found";
				break;
			case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
				errorMsg = "gateway timeout";
				break;
			}
			return result;
		}
		
		/**
		 * ʹ��HttpClient ִ��GET����
		 * @param url �����������url��ַ
		 * @return ����ķ��ؽ��
		 */
		private String getResponse(String url) {
			String response= "";
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			try {
				HttpResponse httpResponse = httpClient.execute(httpGet);
				statusCode = httpResponse.getStatusLine().getStatusCode();
				if(statusCode == HttpStatus.SC_OK ) {
					isSuccess = true;
					HttpEntity entity = httpResponse.getEntity();
					if(entity != null) {
						BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
						String line = "";
						while((line = bufferedReader.readLine()) != null) {
							System.out.println("line=" + line);
							response += line + "\n";
						}
						bufferedReader.close();
					}
				} else {
					isSuccess = false;
					errorMsg = "get error response from server";
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return response;
		}
		
		/**
		 * ƴ��POST URL ��ַ
		 * @param serverAddress �����URL��ַǰ׺
		 * @param url �����url·������
		 * @return ƴ�Ӻú��������URL��ַ
		 */
		private String getPostUrl(String serverAddress, String url) {
			String realUrl ="";
			//ȥ����ַ�����/
			if(serverAddress != null && serverAddress.charAt(serverAddress.length() - 1) == '/') {
				serverAddress = serverAddress.substring(0, serverAddress.length() -1);
			}
			//ȥ��urlǰ���/
			if(url != null && url.length() > 0 && url.charAt(0) == '/') {
				url = url.substring(1, url.length());
			}
			//ȥ��url����ģ�
			if(url != null && url.length() > 0 && url.charAt(url.length() - 1) == '?') {
				url = url.substring(0, url.length() -1);
			}
			realUrl = serverAddress + "/" + url;
			return realUrl;
		}
		
		
		/**
		 * ִ��post���󣬲����������
		 * @param serverAddress  �����URL��ַǰ׺
		 * @param url �����url·������
		 * @param params �������
		 * @param asyncHttpResponseHandler �������ʱ�Ļص��ӿڶ���
		 */
		private void doPost(String serverAddress, String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
			String realUrl = getPostUrl(serverAddress, url);
			System.out.println("post realUrl =" + realUrl);
			//String response = postResponse(realUrl, params); //ִ��apache httpclient
			String paramsString = getMyPostParamsString(params);
			System.out.println("paramsString=" + paramsString);
			String response = postResponse(realUrl, params);
			if(isSuccess == true) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					if(asyncHttpResponseHandler != null) {
						asyncHttpResponseHandler.onSuccess(jsonObject);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					try {
						JSONArray jsonArray = new JSONArray(response);
						if(asyncHttpResponseHandler != null) {
							asyncHttpResponseHandler.onSuccess(jsonArray);
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
						if(asyncHttpResponseHandler != null) {
							asyncHttpResponseHandler.onFailure(response, statusCode, "Convert result to JSON ERROR, please check it");
						}
					}
				}
			} else {
				if(asyncHttpResponseHandler != null) {
					asyncHttpResponseHandler.onFailure(response, statusCode, errorMsg);
				}
			}
		}
		

		/**
		 * ʹ��HttpClientִ��POST����
		 * @param url �����url��ַ
		 * @param params ����Ĳ���
		 * @return ����post������
		 */
		private String postResponse(String url, HttpParams params) {
			String result = "";
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
			for(int i=0; i< params.getParamsCount(); i++) {
				Param param = params.get(i);
				paramsList.add(new BasicNameValuePair(param.key, param.vObject.toString()));
			}
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(paramsList, HTTP.UTF_8));
				//����post����
				HttpResponse httpResponse = httpClient.execute(httpPost);
				//����������ɹ��ط�����Ӧ
				statusCode = httpResponse.getStatusLine().getStatusCode();
				if(statusCode == HttpStatus.SC_OK ) {
					isSuccess = true;
					result = EntityUtils.toString(httpResponse.getEntity());
					System.out.println("result =" + result);
				} else {
					isSuccess = false;
					errorMsg = "get erro response from server";
				}
			} catch (UnsupportedEncodingException e) {
				isSuccess = false;
				errorMsg = "get erro response from server:" + e.getMessage();
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				isSuccess = false;
				errorMsg = "get erro response from server:" + e.getMessage();
				e.printStackTrace();
			} catch (IOException e) {
				isSuccess = false;
				errorMsg = "get erro response from server:" + e.getMessage();
				e.printStackTrace();
			}
			return result;
		}

		/**
		 * ʹ�� URLConnectionִ����������
		 * @param url �����url��ַ
		 * @param params ����Ĳ����б�
		 * @return ����ķ��ؽ��
		 */
		private String sendPost(String url, String params) {
			PrintWriter out = null;
			BufferedReader in = null;
			String result = "";
			try {
				URL realUrl = new URL(url);
				// ����URL֮�������
				URLConnection connection = realUrl.openConnection();
				// ����ͨ�õ���������
				// ����ͨ�õ���������
				connection.setRequestProperty("accept", "*/*");
				//connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("user-agent",
						"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
				connection.setRequestProperty("Content-Type",  "application/x-www-form-urlencoded"); 
				connection.setConnectTimeout(5000);
				// ����POST�������������������
				connection.setDoOutput(true);
				connection.setDoInput(true);
				// ��ȡURLConnection�����Ӧ��������
				out = new PrintWriter(connection.getOutputStream());
				// �����������
				out.print(params);
				// flush������Ļ���
				out.flush();
				// ����BufferedReader����������ȡURL����Ӧ
				in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					System.out.println("line=" + line);
					result += "\n" + line;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null) {
						out.close();
					}
					if (in != null) {

						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		
		/**
		 * ִ��post���󣬲����������
		 * @param serverAddress  �����URL��ַǰ׺
		 * @param url �����url·������
		 * @param params �������
		 * @param asyncHttpResponseHandler �������ʱ�Ļص��ӿڶ���
		 */
		private void doUpload(String serverAddress, String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
			String realUrl = getPostUrl(serverAddress, url);
			//��ʼ�ϴ��ص�
			if(asyncHttpResponseHandler != null) {
				asyncHttpResponseHandler.onStartUpload();
			}
			System.out.println("post realUrl =" + realUrl);
			String response = upload(realUrl, params, asyncHttpResponseHandler);
			if(isSuccess == true) {
				try {
					JSONObject jsonObject = new JSONObject(response);
					if(asyncHttpResponseHandler != null) {
						asyncHttpResponseHandler.onSuccess(jsonObject);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					try {
						JSONArray jsonArray = new JSONArray(response);
						if(asyncHttpResponseHandler != null) {
							asyncHttpResponseHandler.onSuccess(jsonArray);
						}
					} catch (JSONException e1) {
						e1.printStackTrace();
						if(asyncHttpResponseHandler != null) {
							asyncHttpResponseHandler.onFailure(response, statusCode, "Convert result to JSON ERROR, please check it");
						}
					}
				}
			} else {
				if(asyncHttpResponseHandler != null) {
					asyncHttpResponseHandler.onFailure(response, statusCode, errorMsg);
				}
			}
		}
		
		private String upload(String url, HttpParams params, AsyncHttpResponseHandler asyncHttpResponseHandler) {
			BufferedReader in = null;
			String result = "";
			String end = "\r\n";
			String twoHyphens = "--";
			String boundary = "*****";
			try {
				URL realUrl = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
				// ����ͨ�õ���������
				// ����ͨ�õ���������
				// ����POST�������������������
				connection.setDoOutput(true);
				connection.setDoInput(true);
				connection.setUseCaches(false);
				connection.setRequestMethod("POST");
				connection.setRequestProperty("accept", "*/*");
				//connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("Connection", "Keep-Alive");
				connection.setRequestProperty("Charset", "UTF-8");
				connection.setRequestProperty("Content-Type",
						"multipart/form-data;boundary=" + boundary);
				connection.setConnectTimeout(5000);
				
				long sumSize = 0; //�ܵ��ļ��Ĵ�С
				long sendLength = 0; //�ѷ��͵��ܳ���
				for(int i=0; i< params.getParamsCount(); i++) {
					String fileName = params.get(i).key; //�õ��ļ���
					String filePath = params.get(i).vObject.toString(); //�õ��ļ�·��
					File file = new File(filePath);
					if(file.exists()) {
						sumSize += file.length(); //ͳ��ÿ���ļ��ĳ���
					}
				}
				System.out.println("sumSize=" + sumSize);
				if(sumSize > 1024*1024*2) { //����ļ�����2M����ȡ���ϴ�
					isSuccess  = false;
					result = "upload canceled";
					statusCode = 404;
					errorMsg = "File size bigger than 2M, cancel uploading";
					return result;
				}
 
				
				/* ����DataOutputStream */
				DataOutputStream ds = new DataOutputStream(connection.getOutputStream());
				for(int i=0; i< params.getParamsCount(); i++) {
					String fileName = params.get(i).key; //�õ��ļ���
					String filePath = params.get(i).vObject.toString(); //�õ��ļ�·��
					ds.writeBytes(twoHyphens + boundary + end);
					ds.writeBytes("Content-Disposition: form-data; " + "name=\"file1\";filename=\"" + fileName + "\"" + end);
					ds.writeBytes(end);
					/* ȡ���ļ���FileInputStream */
					FileInputStream fStream = new FileInputStream(filePath);
					/* ����ÿ��д��1024bytes */
					int bufferSize = 1024;
					byte[] buffer = new byte[bufferSize];
					int length = -1;
					/* ���ļ���ȡ������������ */
					while ((length = fStream.read(buffer)) != -1) {
						/* ������д��DataOutputStream�� */
						ds.write(buffer, 0, length);
						ds.flush();
						sendLength += length;
						if(asyncHttpResponseHandler != null) {
							asyncHttpResponseHandler.onUploadProgress((int)(sendLength * 100 / sumSize));
						}
					}
					ds.writeBytes(end);
					ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
					/* close streams */
					fStream.close();
					ds.flush();
				}
				ds.close();
				//�ϴ���ɻص�
				if(asyncHttpResponseHandler != null) {
					asyncHttpResponseHandler.onUploadCompleted();
				}

				// ����BufferedReader����������ȡURL����Ӧ
				in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				statusCode = connection.getResponseCode();
				if(statusCode == HttpURLConnection.HTTP_OK) {
					isSuccess = true;
					String line;
					while ((line = in.readLine()) != null) {
						System.out.println("line=" + line);
						result += "\n" + line;
					}
				} else {
					isSuccess = false;
				}
				connection.disconnect();
			} catch (MalformedURLException e) {
				isSuccess = false;
				errorMsg = "url is not correct, please check it";
				e.printStackTrace();
			} catch (ProtocolException e) {
				isSuccess = false;
				errorMsg = "unknown protocol, url is not correct, please check it";
				e.printStackTrace();
			} catch (SocketTimeoutException e) {
				isSuccess = false;
				errorMsg = "connection timeout";
				e.printStackTrace();
			} catch (IOException e) {
				isSuccess = false;
				errorMsg = "IOexception occur";
				e.printStackTrace();
			}  finally {
				try {
					if(in != null) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//�����������ԭ��
			switch(statusCode) {
			case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
				errorMsg = "connect to server time out";
				break;
			case HttpURLConnection.HTTP_INTERNAL_ERROR:
				errorMsg = "connect to server internal error";
				break;
			case HttpURLConnection.HTTP_NOT_FOUND:
				errorMsg = "the url not found";
				break;
			case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
				errorMsg = "gateway timeout";
				break;
			}
			return result;
		}
		
		/**
		 * ���������ƴ�ӳ�URL��ʽ name=12345&passwd=123456
		 * @param params ��������б�
		 * @return ����ƴ�Ӻ�Ĳ����б�
		 */
		private String getMyPostParamsString(HttpParams params) {
			String paramString ="";
			if(params != null && params.getParamsCount() > 0) {
				for(int i=0; i< params.getParamsCount(); i++) {
					Param param = params.get(i);
					if(i != 0) { //�����Ż�ѡ��
						if(param.vObject != null) {
							try {
								String value = URLEncoder.encode(param.vObject.toString(), "utf-8");
								paramString += "&" + param.key + "=" + value; 
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								paramString += "&" + param.key + "=" + param.vObject; 
							}
						} else {
							paramString += "&" + param.key + "="; 
						}
					} else {
						if(param.vObject != null) {
							try {
								String value = URLEncoder.encode(param.vObject.toString(), "utf-8");
								paramString += param.key + "=" + value;
							} catch (UnsupportedEncodingException e) {
								e.printStackTrace();
								paramString += param.key + "=" + param.vObject;
							}
						} else {
							paramString += param.key + "=";
						}
					}
				}
			} 
			return paramString;
		}
		
		
		/**
		 * ʹ��HttpURLConnectionִ��post����
		 * @param realUrl �����url��ַ
		 * @param params �������������б���ʽ name=12345&passwd=123456
		 * @author ����ķ��ؽ��
		 */
		private String myPost(String realUrl, String params) {
			PrintWriter out = null;
			BufferedReader in = null;
			String result = "";
			try {
				URL url = new URL(realUrl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(10 * 1000);
				connection.setRequestMethod("POST");
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection
						.setRequestProperty(
								"Accept",
								"image/gif, image/jpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-app lication, application/cnd.ms- excel, application/vnd.ms-powerpoint, application/msword, */*");
				connection.setRequestProperty("Accept-Language", "zh_CN");
				connection.setRequestProperty("Charset", "UTF-8");
				connection
						.setRequestProperty(
								"User-Agent",
								"Mozilla/4.0(compatible; MSIE 7.0; Windows NT 5.2; Trident/4.0; . NET CTR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729)");
				connection.setRequestProperty("Connection", "Keep-Alive");
				// �õ��ļ��Ĵ�С
				out = new PrintWriter(connection.getOutputStream());
				out.print(params);
				out.flush();
				statusCode = connection.getResponseCode();
				if(statusCode == HttpURLConnection.HTTP_OK) {
					isSuccess = true;
					// ����BufferedReader����������ȡURL����Ӧ
					in = new BufferedReader(new InputStreamReader(
							connection.getInputStream()));
					String line;
					while ((line = in.readLine()) != null) {
						System.out.println("line=" + line);
						result += "\n" + line;
					}
				} else {
					isSuccess = false;
				}
				connection.disconnect();
			} catch (MalformedURLException e) {
				isSuccess = false;
				errorMsg = "url is not correct, please check it";
				e.printStackTrace();
			} catch (ProtocolException e) {
				isSuccess = false;
				errorMsg = "unknown protocol, url is not correct, please check it";
				e.printStackTrace();
			} catch (SocketTimeoutException e) {
				isSuccess = false;
				errorMsg = "connection timeout";
				e.printStackTrace();
			} catch (IOException e) {
				isSuccess = false;
				errorMsg = "IOexception occur";
				e.printStackTrace();
			}  finally {
				if(out != null) {
					out.close();
				}
				try {
					if(in != null) {
						in.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//�����������ԭ��
			switch(statusCode) {
			case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
				errorMsg = "connect to server time out";
				break;
			case HttpURLConnection.HTTP_INTERNAL_ERROR:
				errorMsg = "connect to server internal error";
				break;
			case HttpURLConnection.HTTP_NOT_FOUND:
				errorMsg = "the url not found";
				break;
			case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
				errorMsg = "gateway timeout";
				break;
			}
			return result;
		}
	}	
}
