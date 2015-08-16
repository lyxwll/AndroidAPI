package com.cloudy.androidapi.http;

import com.cloudy.androidapi.http.AsyncHttpClient.HttpClientThread;


public interface OnCompleteListener {
	public void onComplete(HttpClientThread clientThread);
}
