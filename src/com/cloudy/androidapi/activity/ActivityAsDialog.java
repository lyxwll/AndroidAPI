package com.cloudy.androidapi.activity;

/**
 * ʹ��Activity ��Ϊ Dialog
 */

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class ActivityAsDialog extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//ȥ�����ڱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ȫ����ʾ
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.userlogin_layout);
		
		
		
		
	}

}
