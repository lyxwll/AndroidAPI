package com.cloudy.androidapi.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class SpyB extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action.equals("com.cloudy.order")) {
			if (intent.hasExtra("order")) {
				int[] orders = intent.getIntArrayExtra("order");
				StringBuilder builder = new StringBuilder();
				for (int i = 0; i < orders.length; i++) {
					builder.append(decrypt(orders[i]));
				}
				System.out.println("B received order = " + builder.toString());
				String reply = "ok";
				int[] replys = new int[reply.length()];
				for (int i = 0; i < reply.length(); i++) {
					replys[i] = encrypt(reply.charAt(i));
				}
				Intent intent2 = new Intent();
				intent2.setAction("com.cloudy.rescue");
				intent2.putExtra("reply", replys);
				context.sendBroadcast(intent2);
			}
		}
	}

	// ¼ÓÃÜ
	public int encrypt(char a) {
		if (a >= 97 && a <= 122) {
			return a - 97;
		} else if (a >= 65 && a <= 90) {
			return a - (65 - 26);
		} else if (a >= 48 && a <= 57) {
			return a + 4;
		} else if (a == 95) {
			return 62;
		} else if (a == 32) {
			return 63;
		}
		return 63;
	}

	// ½âÃÜ
	public char decrypt(int reply) {
		if (reply >= 0 && reply <= 25) {
			return (char) (reply + 97);
		} else if (reply >= 26 && reply <= 51) {
			return (char) (reply + (65 - 21));
		} else if (reply >= 52 && reply <= 61) {
			return (char) (reply - 4);
		} else if (reply == 62) {
			return 95;
		} else if (reply == 63) {
			return 32;
		}
		return ' ';
	}
	
	

}
