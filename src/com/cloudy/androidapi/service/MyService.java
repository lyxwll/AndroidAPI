package com.cloudy.androidapi.service;

import java.io.ObjectInputStream.GetField;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

/**
 *��������̳�Service
 *MyService
 * @author Administrator
 */
public class MyService extends Service{
	
	private int count = 0;//����һ��������
	private MyBinder myBinder = new MyBinder();
	
	/*ʹ��Binder
	 * public class MyBinder extends Binder{
		//����ͨ�ŵ�
		public int getCount(){
			return count;
		}
	}*/
	
	//ʹ��aidl
	public class MyBinder extends GetCount.Stub{

		@Override
		public int getCount() throws RemoteException {
			return 0;
		}
		
	}

	/**
	 * �ͻ���������ʱ�ص��˷���
	 */
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		new MyThread().start();
		return myBinder;
	}
	/**
	 * ���°�
	 */
	@Override
	public void onRebind(Intent intent) {
		System.out.println("onRebind");
		super.onRebind(intent);
	}
	/**
	 * �Ӵ���
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("onUnbind");
		return super.onUnbind(intent);
	}

	/**
	 * ��һ�δ�����ʱ��ص��˷���
	 */
	@Override
	public void onCreate() {
		System.out.println("onCreate");
		super.onCreate();
	}
	/**
	 * ÿ�ε���startService��ʽ����ص��˷���
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand");
		//��������1
		count++;
		return super.onStartCommand(intent, flags, startId);
	}
	/**
	 * ������stopService������
	 */
	@Override
	public void onDestroy() {
		System.out.println("onDestroy");
		super.onDestroy();
	}
	
	//
	class MyThread extends Thread{
		@Override
		public void run() {
			while(count < 1000000){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
			}
		}
	}
	
	
	
	
	
}
