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
		
		//获取布局内容
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
						Configuration.ORIENTATION_LANDSCAPE ? "横向屏幕":"纵向屏幕";
				String mncCode = configuration.mnc+"";
				String naviName = 
						configuration.orientation ==Configuration.NAVIGATION_NONAV ? "没有方向控制":
						configuration.orientation ==Configuration.NAVIGATION_WHEEL ? "滚轮控制方向":
						configuration.orientation ==Configuration.NAVIGATION_DPAD ? "方向键控制方向":"轨迹球控制方向";
				navigation.setText(naviName);
				String touchName = configuration.touchscreen == 
						Configuration.TOUCHSCREEN_NOTOUCH ? "无触摸屏":
						configuration.touchscreen == Configuration.TOUCHSCREEN_STYLUS 
						? "触摸式触摸屏":"接收手指的触摸屏";
				ori.setText(screen);
				mnc.setText(mncCode);
				touch.setText(touchName);
				
			}
		});
	}
}
