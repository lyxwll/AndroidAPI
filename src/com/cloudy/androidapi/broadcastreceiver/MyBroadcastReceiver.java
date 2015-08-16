package com.cloudy.androidapi.broadcastreceiver;

import com.cloudy.androidapi.activities.LaunchModeTest;
import com.cloudy.androidapi.fragment.FragmentTestActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver{

	/**
	 * ���չ㲥
	 * �㲥ʵ����Ҳ��һ��Intent
	 * ��̬ע��Ĺ㲥�������ܽ��յ��Ĺ㲥��AndroidManifest����ע���receiver��Intent-filterָ��
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		System.out.println("receiver broadcast action = "+ action);
		if(action.equals("com.cloudy.start_launchmodetest")){
			//����һ��Activity
			Intent intent2 = new Intent();
			intent2.setClass(context, LaunchModeTest.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);
		}else if(action.equals("com.cloudy.start_fragmenttestactivity")){
			//������һ��Activity
			Intent intent2 = new Intent();
			intent2.setClass(context, FragmentTestActivity.class);
			intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent2);
		}
		
	}
	

}
