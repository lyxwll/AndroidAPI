package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class PreferenceTest extends PreferenceActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//������ʾ�������õĲ����ļ�
		addPreferencesFromResource(R.xml.preference_xml);
		
	}

}
