package com.cloudy.androidapi.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MultiDownloadUtil {
	
	private DownListener downListener;//���ؼ���
	
	private String path;//����������Դ��·��
	private String targetFile;//���屣����Դ��·��
	private int threadNum;//����ʹ�ö����߳�������
	private DownloadThread[] threads;//���������̵߳Ķ���
	private int fileSize;//���������ļ����ܴ�С
	
	/**
	 * ������
	 * @param path
	 * @param targetFile
	 * @param threadNum
	 * @param downListener
	 */
	public MultiDownloadUtil(String path,String targetFile,int threadNum,
			DownListener downListener) {
		this.path = path;
		this.targetFile = targetFile;
		this.threadNum = threadNum;
		threads = new DownloadThread[threadNum];
	}
	
	/**
	 * ���ص��÷���
	 */
	public void download(){
		new MyThread().start();
	}
	class MyThread extends Thread{
		@Override
		public void run() {
			if(downListener != null){
				downListener.onStartDownLoad();
			}
			//
			down();
		}
	}
	/**
	 * ������
	 */
	public void down(){
		try {
			URL url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setReadTimeout(5 * 1000);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "image/gif,image/jpeg,image/pjpeg,application/x-shockwave-flash," + 
													"application/xaml+xml,application/vnd.ms-xpsdocment," + 
													"application/x-ms-xbap,application/x-ms-application," +
													"application/cnd.ms-excel,application/vnd.ms-powerpoint," +
													"application/msword,*/*");
			connection.setRequestProperty("Accept-Language", "zh_CN");
			connection.setRequestProperty("Charset", "UTF-8");
			connection.setRequestProperty("User-Agent", "Mozilla/4.0(compatible;MSIE 7.0;Windows NT 5.2;" + 
														"Trident/4.0;.NET CTR 1.1.4322;.NET CLR 2.0.50727;" +
														".NET CLR 3.0.04506.30;.NET CLR 3.0.4506.2152" +
														".NET CLR 3.5.30729)");
			connection.setRequestProperty("Connection", "Keep-Alive");
			//�õ��ļ��Ĵ�С
			fileSize = connection.getContentLength();
			connection.disconnect();
			//������һ���ֽ�,��ʡ��һ���̵߳Ŀ���
			int currentPartSize = fileSize / threadNum + 1;
			
			RandomAccessFile accessFile = new RandomAccessFile(targetFile, "rw");
			//�����ļ��Ĵ�С
			accessFile.setLength(fileSize);
			accessFile.close();
			for(int i = 0;i < threadNum;i++){
				//����ÿ���߳����ؿ�ʼ��λ��
				int startPos = i * currentPartSize;
				//ÿһ���߳�ʹ��һ��RandomAccessFile��������
				RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");
				//��λ���̵߳�����λ��
				randomAccessFile.seek(startPos);
				//���������߳�
				threads[i] = new DownloadThread(startPos, currentPartSize, randomAccessFile);
				//���������߳�
				threads[i].start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * �����̵߳�����,ÿ���߳������ļ���һ����
	 */
	private class DownloadThread extends Thread{
		private int startPos;//��ǰ�̵߳�����λ��
		private int currentPartSize;//���嵱ǰ�̸߳������ص��ļ��Ĵ�С
		private RandomAccessFile currentAccessFile;//��ǰ�߳���Ҫ���ص��ļ���
		
		public int length = 0;//������߳������ص��ֽ���
		public int leftLength;//ʣ�������ֽ�
		public boolean isCompleted = false;
		
		public DownloadThread(int startPos,int currentPartSize,
				RandomAccessFile currentAccessFile){
			this.startPos = startPos;
			this.currentPartSize = currentPartSize;
			this.currentAccessFile = currentAccessFile;
			this.leftLength = currentPartSize;
		}
		
		@Override
		public void run() {
			try {
				URL url;
				url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(5*1000);
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept", "image/gif,image/jpeg,image/pjpeg," + 
									"application/x-shockwave-flash,application/xaml+xml," + 
									"application/vnd.ms-xpsdocment,application/x-ms-xbap" + 
									"application/x-ms-application,application/cnd.ms-excel" + 
									"application/vnd.ms-powerpoint,application/msword,*/*");
				connection.setRequestProperty("Accept-Language", "zh_CN");
				connection.setRequestProperty("Charset", "UTF-8");
				
				InputStream inputStream = connection.getInputStream();
				//����startPos���ֽ�,�������߳�ֻ�����Լ�������ǲ���
				inputStream.skip(this.startPos);
				byte[] buffer = new byte[20480];//
				int hasRead = 0;
				//��ȡ�������ݲ�д�뱾��
				if(leftLength <= buffer.length){
					hasRead = inputStream.read(buffer, 0, leftLength);
				}else{
					hasRead = inputStream.read(buffer, 0, buffer.length);
				}
				while(length < currentPartSize && hasRead != -1){
					currentAccessFile.write(buffer, 0, leftLength);
					//�ۼƸ��߳����ص��ܴ�С
					length += hasRead;
					leftLength -= hasRead;
					//���ؽ��Ȼص�
					int rate = getCompleteRate();
					if(downListener != null){
						downListener.onCompleteRateChanged(rate);
					}
					if(leftLength <= buffer.length){
						hasRead = inputStream.read(buffer, 0, leftLength);
					}else{
						hasRead = inputStream.read(buffer, 0, buffer.length);
					}
					currentAccessFile.close();
					inputStream.close();
					isCompleted = true;
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(isAllDownLoadCompleted() && downListener != null){
				downListener.onDownloadCompleted(targetFile);
			}
		}
	}
	
	/**
	 * ��ȡ������ɵİٷֱȽ���ֵ
	 */
	public int getCompleteRate(){
		int num = 0;
		int sumSize = 0;
		for(int i = 0;i < threadNum;i++){
			if(threads[i] != null){
				sumSize += threads[i].length;
			}
		}
		//
		num = sumSize * 100 / fileSize;
		
		return num;
	}
	/**
	 * �Ƿ�ȫ���������
	 * @return
	 */
	public boolean isAllDownLoadCompleted(){
		boolean flag = true;
		for(int i = 0;i < threads.length;i++){
			if(threads[i].isCompleted == false){
				flag = false;
			}
		}
		return flag;
	}
	

}
