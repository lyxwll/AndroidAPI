package com.cloudy.androidapi.service;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.service.MyService.MyBinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;

public class MyServiceTestActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.service_test);
		
		findViewById(R.id.start_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				bind();
			}
		});
		
		findViewById(R.id.stop_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				unbind();
			}
		});
		
		findViewById(R.id.count_btn).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(myBinder != null){
					try {
						System.out.println("count=" + myBinder.getCount());
					} catch (RemoteException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
	MyBinder myBinder;
	class MyConnectService implements ServiceConnection{
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			System.out.println("onServiceConnected");
			System.out.println("componentName=" + name.toString());
			//��service��ʵ�Ͼ��ǰ�serviceʱ���ص�IBinder����,Ҳ����MyBinder����
			if(service instanceof MyBinder){
				myBinder = (MyBinder) service;
			}
		}
		//�Ͽ�����
		@Override
		public void onServiceDisconnected(ComponentName name) {
			System.out.println("onServiceDisconnected");
			myBinder = null;
		}
	}
	
	MyConnectService connectService = new MyConnectService();
	//
	public void bind(){
		Intent intent = new Intent("com.cloudy.start");
		bindService(intent, connectService, Context.BIND_AUTO_CREATE);
	}
	
	//
	public void unbind(){
		if(myBinder != null){
			unbindService(connectService);//��Ӧ�ó������Ͽ�����
		}
	}
	
	
	

}
