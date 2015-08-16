package com.cloudy.androidapi.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SpyC extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if(action.equals("com.cloudy.order")){
			if(intent.hasExtra("order")){
				int[] orders = intent.getIntArrayExtra("order");
				System.out.print("C received information");
				for(int i = 0;i < orders.length;i++){
					System.out.print(orders[i]+" ");
				}
				System.out.println();
			}
		}else if(action.equals("com.cloudy.rescue")){
			if(intent.hasExtra("reply")){
				int[] replys = intent.getIntArrayExtra("reply");
				System.out.println("C received information");
				for(int i=0;i < replys.length;i++){
					System.out.print(replys[i]+" ");
				}
				System.out.println();
			}
		}
	}
	
}
