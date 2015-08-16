package com.cloudy.androidapi.broadcastreceiver;

import com.cloudy.androidapi.activities.LaunchModeTest;
import com.cloudy.androidapi.fragment.FragmentTestActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver{

	/**
	 * 接收广播
	 * 广播实际上也是一个Intent
	 * 静态注册的广播接收器能接收到的广播由AndroidManifest里面注册的receiver的Intent-filter指定
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		System.out.println("receiver broadcast action = "+ action);
		if(action.equals("com.cloudy.start_launchmodetest")){
			//启动一个Activity
			Intent intent2 = new Intent();
			intent2.setClass(context, LaunchModeTest.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);
		}else if(action.equals("com.cloudy.start_fragmenttestactivity")){
			//启动另一个Activity
			Intent intent2 = new Intent();
			intent2.setClass(context, FragmentTestActivity.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);
		}
		
	}
	

}
