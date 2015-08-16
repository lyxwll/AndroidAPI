package com.cloudy.androidapi.internet;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MultiDownloadUtil {
	
	private DownListener downListener;//下载监听
	
	private String path;//定义下载资源的路径
	private String targetFile;//定义保存资源的路径
	private int threadNum;//定义使用多少线程来下载
	private DownloadThread[] threads;//定义下载线程的对象
	private int fileSize;//定义下载文件的总大小
	
	/**
	 * 构造器
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
	 * 下载调用方法
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
	 * 下载总
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
			//得到文件的大小
			fileSize = connection.getContentLength();
			connection.disconnect();
			//多下载一个字节,节省了一条线程的开销
			int currentPartSize = fileSize / threadNum + 1;
			
			RandomAccessFile accessFile = new RandomAccessFile(targetFile, "rw");
			//设置文件的大小
			accessFile.setLength(fileSize);
			accessFile.close();
			for(int i = 0;i < threadNum;i++){
				//计算每条线程下载开始的位置
				int startPos = i * currentPartSize;
				//每一个线程使用一个RandomAccessFile进行下载
				RandomAccessFile randomAccessFile = new RandomAccessFile(targetFile, "rw");
				//定位该线程的下载位置
				randomAccessFile.seek(startPos);
				//创建下载线程
				threads[i] = new DownloadThread(startPos, currentPartSize, randomAccessFile);
				//启动下载线程
				threads[i].start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 单条线程的下载,每条线程下载文件的一部分
	 */
	private class DownloadThread extends Thread{
		private int startPos;//当前线程的下载位置
		private int currentPartSize;//定义当前线程负责下载的文件的大小
		private RandomAccessFile currentAccessFile;//当前线程需要下载的文件快
		
		public int length = 0;//定义该线程已下载的字节数
		public int leftLength;//剩余下载字节
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
				//跳过startPos个字节,表明该线程只下载自己负责的那部分
				inputStream.skip(this.startPos);
				byte[] buffer = new byte[20480];//
				int hasRead = 0;
				//读取网络数据并写入本地
				if(leftLength <= buffer.length){
					hasRead = inputStream.read(buffer, 0, leftLength);
				}else{
					hasRead = inputStream.read(buffer, 0, buffer.length);
				}
				while(length < currentPartSize && hasRead != -1){
					currentAccessFile.write(buffer, 0, leftLength);
					//累计该线程下载的总大小
					length += hasRead;
					leftLength -= hasRead;
					//下载进度回调
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
	 * 获取下载完成的百分比进度值
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
	 * 是否全部下载完成
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
