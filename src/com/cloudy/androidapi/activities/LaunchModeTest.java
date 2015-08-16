package com.cloudy.androidapi.activities;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;

public class LaunchModeTest extends Activity{
	
	String[] names = {"Standard","singleTop","singleTask","singleInstance"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		System.out.println("IntentTest onCreate");
		LinearLayout linearLayout = new LinearLayout(this);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ListView listView = new ListView(this);
		listView.setLayoutParams(layoutParams);
		//listView.setSelector(null);
		listView.setCacheColorHint(this.getResources().getColor(R.color.transparent));
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autocomplete_textview, names);
		listView.setAdapter(adapter);
		linearLayout.addView(listView);
		setContentView(linearLayout);
		
		listView.setOnItemClickListener(new MyOnItemClickListener());
		
	}
	
	class MyOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent = null;
			switch (position) {
			case 0:
				//∆Ù∂ØLaunchModeStandard’‚∏ˆActivity
				intent = new Intent(LaunchModeTest.this, LaunchModeStandard.class);
				break;
			case 1:
				intent = new Intent(LaunchModeTest.this, LaunchModeSingleTop.class);
				break;
			case 2:
				intent = new Intent(LaunchModeTest.this, LaunchModeSingleTask.class);
				break;
			case 3:
				intent = new Intent(LaunchModeTest.this, LaunchModeSingleInstance.class);
				break;
			
			}
			if(intent != null){
				startActivity(intent);
			}
		}
	}
	
}
















