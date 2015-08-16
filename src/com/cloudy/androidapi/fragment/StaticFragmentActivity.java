package com.cloudy.androidapi.fragment;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class StaticFragmentActivity extends FragmentActivity{
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.static_fragment_layout);
		
	}

}
