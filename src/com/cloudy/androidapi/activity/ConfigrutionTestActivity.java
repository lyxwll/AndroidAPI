package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class ConfigrutionTestActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.configrution_test_layout);
		
		findViewById(R.id.change_config_btn).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Configuration configuration = getResources().getConfiguration();
				if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
					ConfigrutionTestActivity.this.setRequestedOrientation(Configuration.ORIENTATION_PORTRAIT);
				}else{
					ConfigrutionTestActivity.this.setRequestedOrientation(Configuration.ORIENTATION_LANDSCAPE);
				}
			}
		});
		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		String str = newConfig.orientation ==Configuration.ORIENTATION_LANDSCAPE ? 
				"∫·∆¡œ‘ æ":" ˙∆¡œ‘ æ";
		Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
		
		super.onConfigurationChanged(newConfig);
	}

}













