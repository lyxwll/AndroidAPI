package com.cloudy.androidapi.internet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class SingleDownloadUtil {
	
	String path;
	String targetFile;
	DownListener downListener;
	
	/**
	 * 构造器
	 * @param path
	 * @param targetFile
	 * @param downListener
	 */
	public SingleDownloadUtil(String path,String targetFile,DownListener downListener) {
		this.downListener = downListener;
		this.targetFile = targetFile;
		this.path = path;
	}
	
	//下载
	public void download(){
		if(downListener != null){
			downListener.onDownloadCompleted(targetFile);
		}
		//启动线程
		new DownloadThread().start();
	}
	
	class DownloadThread extends Thread{
		long length = 0;
		@Override
		public void run() {
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
				connection.connect();
				InputStream inputStream = connection.getInputStream();
				OutputStream outputStream = new FileOutputStream(targetFile);
				long fileSize = connection.getContentLength();
				System.out.println("fileSize=" + connection.getContentLength());
				//跳过startPos个字节,表明该线程只下载自己负责的那部分
				byte[] buffer = new byte[20480];//
				int hasRead = -1;
				while((hasRead = inputStream.read(buffer, 0, buffer.length)) != -1){
					outputStream.write(buffer, 0, hasRead);
					length += hasRead;
					int rate = (int)((length * 100) / fileSize);//下载的百分比
					if(downListener != null){
						downListener.onCompleteRateChanged(rate);
					}
				}
				inputStream.close();
				outputStream.close();
				connection.disconnect();
				if(downListener != null){
					downListener.onDownloadCompleted(targetFile);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
