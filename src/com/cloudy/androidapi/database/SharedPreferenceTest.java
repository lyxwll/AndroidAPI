package com.cloudy.androidapi.database;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SharedPreferenceTest extends FragmentActivity{
	
	private SharedPreferences preferences;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		preferences = getSharedPreferences("login", MODE_PRIVATE);
		
		String name = "";
		String password ="";
		
		name = preferences.getString("name", "");
		password = preferences.getString("password", "");
		
		System.out.println("name =" + name +";password =" + password);
		
		if(name.equals(""))
			name = "zhangsan";
		if(password.equals(""))
			password = "123456";
		
		//通过使用SharedPreferences来存储少量的数据
		Editor editor = preferences.edit();
		editor.putString("name", name);
		editor.putString("password", password);
		editor.commit();//最后一定要提交
	}
	

}
