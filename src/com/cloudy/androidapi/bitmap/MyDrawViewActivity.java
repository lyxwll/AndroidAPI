package com.cloudy.androidapi.bitmap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class MyDrawViewActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		TestMyDrawView drawView = new TestMyDrawView(this);
		setContentView(drawView);
		
	}

}
