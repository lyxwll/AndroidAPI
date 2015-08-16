package com.cloudy.androidapi.service;

import java.io.ObjectInputStream.GetField;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

/**
 *开发服务继承Service
 *MyService
 * @author Administrator
 */
public class MyService extends Service{
	
	private int count = 0;//定义一个计数器
	private MyBinder myBinder = new MyBinder();
	
	/*使用Binder
	 * public class MyBinder extends Binder{
		//用来通信的
		public int getCount(){
			return count;
		}
	}*/
	
	//使用aidl
	public class MyBinder extends GetCount.Stub{

		@Override
		public int getCount() throws RemoteException {
			return 0;
		}
		
	}

	/**
	 * 客户端与服务绑定时回调此方法
	 */
	@Override
	public IBinder onBind(Intent intent) {
		System.out.println("onBind");
		new MyThread().start();
		return myBinder;
	}
	/**
	 * 重新绑定
	 */
	@Override
	public void onRebind(Intent intent) {
		System.out.println("onRebind");
		super.onRebind(intent);
	}
	/**
	 * 接触绑定
	 */
	@Override
	public boolean onUnbind(Intent intent) {
		System.out.println("onUnbind");
		return super.onUnbind(intent);
	}

	/**
	 * 第一次创建的时候回调此方法
	 */
	@Override
	public void onCreate() {
		System.out.println("onCreate");
		super.onCreate();
	}
	/**
	 * 每次调用startService方式都会回调此方法
	 */
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("onStartCommand");
		//计数器加1
		count++;
		return super.onStartCommand(intent, flags, startId);
	}
	/**
	 * 当调用stopService方法或
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
