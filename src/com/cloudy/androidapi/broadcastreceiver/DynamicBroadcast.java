package com.cloudy.androidapi.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class DynamicBroadcast extends FragmentActivity{
	
	MyBroadcastReceiver broadcastReceiver;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.cloudy.start");
		broadcastReceiver = new MyBroadcastReceiver();
		//动态注册广播
		registerReceiver(broadcastReceiver, filter);
	}
	
	class MyBroadcastReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if(action.equals("com.cloudy.start")){
				System.out.println("received broadcast--------->> com.cloudy.start");
			}
		}
	}
	
	//动态注销广播接收器
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//注销广播
		unregisterReceiver(broadcastReceiver);
	}
	

}
