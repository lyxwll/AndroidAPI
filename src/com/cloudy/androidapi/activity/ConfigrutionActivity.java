package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ConfigrutionActivity extends Activity{
	
	EditText ori;
	EditText navigation;
	EditText touch;
	EditText mnc;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configuration_layout);
		
		//��ȡ��������
		ori = (EditText) findViewById(R.id.config_ori);
		navigation = (EditText) findViewById(R.id.config_navigation);
		touch = (EditText) findViewById(R.id.config_touch);
		mnc = (EditText) findViewById(R.id.config_mnc);
		Button button = (Button) findViewById(R.id.config_btn);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//
				Configuration configuration = getResources().getConfiguration();
				String screen = configuration.orientation == 
						Configuration.ORIENTATION_LANDSCAPE ? "������Ļ":"������Ļ";
				String mncCode = configuration.mnc+"";
				String naviName = 
						configuration.orientation ==Configuration.NAVIGATION_NONAV ? "û�з������":
						configuration.orientation ==Configuration.NAVIGATION_WHEEL ? "���ֿ��Ʒ���":
						configuration.orientation ==Configuration.NAVIGATION_DPAD ? "��������Ʒ���":"�켣����Ʒ���";
				navigation.setText(naviName);
				String touchName = configuration.touchscreen == 
						Configuration.TOUCHSCREEN_NOTOUCH ? "�޴�����":
						configuration.touchscreen == Configuration.TOUCHSCREEN_STYLUS 
						? "����ʽ������":"������ָ�Ĵ�����";
				ori.setText(screen);
				mnc.setText(mncCode);
				touch.setText(touchName);
				
			}
		});
	}
}
