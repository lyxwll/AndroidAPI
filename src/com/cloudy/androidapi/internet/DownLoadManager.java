package com.cloudy.androidapi.internet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
/**
 * �ļ�����    ������
 */
public class DownLoadManager {
	private String path;
	private String targetFile;
	private DownListener downListener;
    /**
     * ÿ���߳����ص��ֽ���
     */
    static final long unitSize = 1000 * 1024;
    
    private int threadCount;
    
    private long fileSize;
    
    //�����߳�����
    private DownloadThread[] downloadThreads;
	
	public DownLoadManager(String path, String targetFile, DownListener downListener){
		this.path = path;
		this.targetFile = targetFile;
		this.downListener = downListener;
	}
	
	/**
	 * �������߳�����
	 */
	public void download(){
		if(downListener != null) {
			downListener.onStartDownLoad();
		}
		new GetContentThread().start();
	}
	
	/**
	 * ���߳�����
	 */
	public void singleDownload(){
		if(downListener != null) {
			downListener.onStartDownLoad();
		}
		new SingleDownloadThread().start();
	}
	
	private class GetContentThread extends Thread {
		@Override
		public void run() {
			if(downListener != null) {
				downListener.onStartDownLoad();
			}
			doDownload(path, targetFile);
		}
	}
	
	/**
	 * �����ļ���С�� �������߳�����
	 * @param path
	 * @param targetFile
	 */
	private void doDownload(String path, String targetFile){
		try {
			HttpURLConnection httpConnection = (HttpURLConnection) new URL(path).openConnection();
			httpConnection.setRequestMethod("HEAD");
			int responseCode = httpConnection.getResponseCode();
	        if(responseCode >= 400){
	            System.out.println("Web��������Ӧ����!");
	            return;
	        }
	        String sHeader;
	        for(int i=1;;i++){
	            sHeader = httpConnection.getHeaderFieldKey(i);
	            if(sHeader != null && sHeader.equals("Content-Length")){
	                System.out.println("�ļ���СContentLength:"+httpConnection.getContentLength());
	                fileSize = Long.parseLong(httpConnection.getHeaderField(sHeader));
	                break;
	            }
	        }
	        httpConnection.disconnect();
	        
	        //�����ļ�
	        File newFile = new File(targetFile);
	        RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
	        raf.setLength(fileSize);
	        raf.close();
	        
	        //������Ҫ���ٸ��̣߳��������߳�����
	        threadCount = (int) (fileSize / unitSize);
	        
	        System.out.println("������ " + (fileSize % unitSize == 0 ? threadCount : threadCount + 1) + " ���߳�");
	        
	        if(fileSize > 20 * 1024* 1000) {//����ļ�Զ����20M�� ����ʹ�õ��߳�����
	        	new SingleDownloadThread().start();
	        } else {
	        	 long offset = 0;
	 	        if (fileSize <= unitSize) {// ���Զ���ļ��ߴ�С�ڵ���unitSize
	 	        	downloadThreads = new DownloadThread[1];
	 	        	downloadThreads[0] = new DownloadThread(path, targetFile, offset, fileSize);
	 	        	downloadThreads[0].start();
	 	        } else {// ���Զ���ļ��ߴ����unitSize
	 	        	if(fileSize % unitSize != 0) {
	 	        		downloadThreads = new DownloadThread[threadCount + 1];
	 	        	} else {
	 	        		downloadThreads = new DownloadThread[threadCount];
	 	        	}
	 	            for (int i = 0; i < threadCount; i++) {
	 	            	downloadThreads[i] = new DownloadThread( path, targetFile, offset, unitSize);
	 	            	downloadThreads[i].start();
	 	                offset = offset + unitSize;
	 	            }
	 	            if (fileSize % unitSize != 0) {// �����������������Ҫ�ٴ���һ���߳�����ʣ���ֽ�
	 	            	downloadThreads[threadCount] = new DownloadThread(path, targetFile, offset, fileSize - unitSize * threadCount);
	 	            	downloadThreads[threadCount].start();
	 	            }
	 	        }
	        }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ذٷֱ�
	 * @return
	 */
	private int getDownloadRate(){
		long sum = 0;
		for(int i=0; i< downloadThreads.length; i++) {
			sum += downloadThreads[i].downloadedLength;
		}
		int rate = (int) (sum  * 100  / fileSize);
		return rate;
	}

	/**
	 * �ж��Ƿ�ȫ���������
	 * @return
	 */
	private boolean isAllDownLoadCompleted() {
		boolean flg = true;
		for (int i = 0; i < downloadThreads.length; i++) {
			if (downloadThreads[i].isDownloadCompleted == false) {
				flg = false;
				break;
			}
		}
		return flg;
	}
	
	/**
	 * ���߳�������
	 */
	private class SingleDownloadThread extends Thread {
		long length = 0;
		@Override
		public void run() {
			try {
				URL url = new URL(path);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setConnectTimeout(5 * 1000);
				connection.setRequestMethod("GET");
				connection.setRequestProperty(
								"Accept",
								"image/gif, image/jpeg, image/pjpeg, application/x-shockwave-flash," +
								" application/xaml+xml, application/vnd.ms-xpsdocument," +
								" application/x-ms-xbap, application/x-ms-app lication, " +
								"application/cnd.ms- excel, application/vnd.ms-powerpoint, " +
								"application/msword, */*");
				connection.setRequestProperty("Accept-Language", "zh_CN");
				connection.setRequestProperty("Charset", "UTF-8");
				connection.setRequestProperty(
								"User-Agent",
								"Mozilla/4.0(compatible; MSIE 7.0; Windows NT 5.2; " +
								"Trident/4.0; . NET CTR 1.1.4322; .NET CLR 2.0.50727; " +
								".NET CLR 3.0.04506.30; .NET CLR 3.0.4506.2152; " +
								".NET CLR 3.5.30729)");
				connection.setRequestProperty("Connection", "Keep-Alive");
				connection.connect();
				InputStream inputStream = connection.getInputStream();
				OutputStream outputStream = new FileOutputStream(targetFile);
				long fileSize = connection.getContentLength();
				System.out.println("fileSize=" + connection.getContentLength());
				// ����startPos���ֽڣ��������߳�ֵ�����Լ�������ǲ����ļ�
				byte[] buffer = new byte[1024];
				int hasRead = -1;
				while ((hasRead = inputStream.read(buffer, 0, buffer.length)) != -1) {
					outputStream.write(buffer, 0, hasRead);
					length += hasRead;
					int rate = (int) ((length * 100) / fileSize ) ;
					if(downListener != null) {
						downListener.onCompleteRateChanged(rate);
					}
				}
				inputStream.close();
				outputStream.close();
				connection.disconnect();
				if(downListener != null) {
					downListener.onDownloadCompleted(targetFile);
				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
    
    /**
     * �����ļ����ص���
     */
    private class DownloadThread extends Thread {
        /**
         * �����ص��ļ�
         */
        private String url = null;
     
        /**
         * �����ļ���
         */
        private String fileName = null;
     
        /**
         * ƫ����
         */
        private long offset = 0;
     
        /**
         * ��������̵߳������ֽ���
         */
        private long length = 0;
     
        /**
         * �����ص��ֽ���
         */
        protected long downloadedLength = 0;
        
        /**
         * �Ƿ����������
         */
        protected boolean isDownloadCompleted = false;
        
        /**
         * @param url �����ļ���ַ
         * @param fileName ����ļ���
         * @param offset ���߳�����ƫ����
         * @param length ���߳����س���
         *
         * */
        protected DownloadThread(String url, String file, long offset, long length) {
            this.url = url;
            this.fileName = file;
            this.offset = offset;
            this.length = length;
            System.out.println("ƫ����=" + offset + ";�ֽ���=" + length);
        }
     
        public void run() {
            try {
                HttpURLConnection httpConnection = (HttpURLConnection) new URL(this.url).openConnection();
                httpConnection.setRequestMethod("GET");
                httpConnection.setRequestProperty("RANGE", "bytes=" + this.offset + "-" + (this.offset + this.length - 1));
                System.out.println("RANGE bytes=" + this.offset + "-" + (this.offset + this.length - 1));
                BufferedInputStream bis = new BufferedInputStream(httpConnection.getInputStream());
                byte[] buff = new byte[1024];
                int bytesRead;
                File newFile = new File(fileName);
                RandomAccessFile raf = new RandomAccessFile(newFile, "rw");
                while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
                    raf.seek(this.offset);
                    raf.write(buff, 0, bytesRead);
                    this.offset = this.offset + bytesRead;
                    downloadedLength += bytesRead;
                }
                raf.close();
                bis.close();
                isDownloadCompleted = true;
                httpConnection.disconnect();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
            if(downListener != null) {
            	downListener.onCompleteRateChanged(getDownloadRate());
            }
            if(isAllDownLoadCompleted() && downListener != null) {
            	downListener.onDownloadCompleted(targetFile);
            }
        }
     
    }
}