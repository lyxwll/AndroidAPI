package com.cloudy.androidapi.internet.bitmap;


import android.util.Log;

public class Logg {
	
	public static void v(String tag, String msg) {
		if(Config.DEBUG)
		Log.v(tag, msg);
	}

	public static void d(String tag, String msg) {
		if(Config.DEBUG) {
			Log.d(tag, msg);
		}
	}

	public static void i(String tag, String msg) {
		if(Config.DEBUG) {
			Log.i(tag, msg);
		}
			
	}

	public static void w(String tag, String msg) {
		if(Config.DEBUG) {
			Log.w(tag, msg);
		}
	}

	public static void e(String tag, String msg) {
		if(Config.DEBUG) {
			Log.e(tag, msg);
		}
	}

	public static void out(String msg) {
		if(Config.DEBUG && msg!=null) {
			System.out.println(msg);
		}
	}
}
