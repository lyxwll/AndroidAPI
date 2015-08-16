package com.cloudy.androidapi.intent;

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

public class IntentTest extends Activity{
	
	String[] names = {"Component","Action","Category","Data","Type","Extra"};
	
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
				//∆Ù∂ØComponentAttr’‚∏ˆActivity
				intent = new Intent(IntentTest.this, ComponentAttr.class);
				break;
			case 1:
				intent = new Intent(IntentTest.this, ActionActivity.class);
				break;
			case 2:
				intent = new Intent(IntentTest.this, CategoryActivity.class);
				break;
			case 3:
				intent = new Intent(IntentTest.this, DataActivity.class);
				break;
			case 4:
				break;
			
			}
			if(intent != null){
				startActivity(intent);
			}
		}
	}
	
}
















