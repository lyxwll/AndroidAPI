package com.cloudy.androidapi.contentprovider;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.fragment.FragmentLayoutActivity;
import com.cloudy.androidapi.fragment.FragmentTestActivity;
import com.cloudy.androidapi.fragment.StaticFragmentActivity;
import com.cloudy.androidapi.fragment.WeixinFragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;

public class ContentProviderActivityTest extends Activity{
	
	String[] names = {"Read Contacts","Contacts"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(this);
		LinearLayout.LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		ListView listView = new ListView(this);
		listView.setLayoutParams(layoutParams);
		listView.setCacheColorHint(this.getResources().getColor(R.color.transparent));
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.autocomplete_textview,names);
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
				//Æô¶¯HeaderStaticFragment
				intent = new Intent(ContentProviderActivityTest.this,ReadContactsActivity.class);
				break;
			case 1:
				//intent = new Intent(FragmentTestActivity.this, FragmentLayoutActivity.class);
				break;
			}
			if(intent != null){
				startActivity(intent);
			}
		}
	}
	
	
	

}
