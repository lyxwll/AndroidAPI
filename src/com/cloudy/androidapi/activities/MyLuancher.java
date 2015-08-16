package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.activity.ExpandableListViewActivity;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyLuancher extends LauncherActivity{
	
	String[] names = {"ExpandableListViewActivity","PreferenceTest"};
	Class<?>[] cls = {ExpandableListViewActivity.class,PreferenceTest.class};
	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		//
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autocomplete_textview,names);
		setListAdapter(adapter);
		
		SharedPreferences preferences = getSharedPreferences("com.cloudy.androidapi_preference", MODE_PRIVATE);
		String name = preferences.getString("name", "������");
		System.out.println("Name"+name);
	}
	
	/**
	 * ����ʵ����ͼ������android.widget.ListView���ض�λ��
	 */
	@Override
	protected Intent intentForPosition(int position) {
		Intent intent = new Intent();
		//���������һ��Class���󷵻ص�setComponent�������)
		intent.setClass(MyLuancher.this, cls[position]);
		return intent;
	}

}
