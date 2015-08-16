package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceTest extends PreferenceActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//设置显示参数设置的布局文件
		addPreferencesFromResource(R.xml.preference_xml);
		
	}

}
