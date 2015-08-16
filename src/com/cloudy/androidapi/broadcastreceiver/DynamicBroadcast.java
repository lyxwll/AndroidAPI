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
		//��̬ע��㲥
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
	
	//��̬ע���㲥������
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//ע���㲥
		unregisterReceiver(broadcastReceiver);
	}
	

}
