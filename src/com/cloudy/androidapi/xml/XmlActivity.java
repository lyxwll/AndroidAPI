package com.cloudy.androidapi.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.cloudy.androidapi.R;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

public class XmlActivity extends FragmentActivity implements OnClickListener{
	
	ListView listView;
	PersonAdapter adapter;
	ArrayList<PersonBean> arrayList = new ArrayList<PersonBean>();
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.parse_person);
		
		listView = (ListView) findViewById(R.id.list_person);
		adapter = new PersonAdapter(this, arrayList);
		listView.setAdapter(adapter);
		
		findViewById(R.id.sax).setOnClickListener(this);
		findViewById(R.id.dom).setOnClickListener(this);
		findViewById(R.id.pull).setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.sax:
			arrayList = XmlUtils.readFromXmlSAX(getInputStream());
			adapter.setList(arrayList);
			adapter.notifyDataSetInvalidated();
			break;
		case R.id.dom:
			arrayList = XmlUtils.readFromXmlDOM(getInputStream());
			adapter.setList(arrayList);
			adapter.notifyDataSetInvalidated();
			break;
		case R.id.pull:
			arrayList = XmlUtils.readFromXmlPULL(getInputStream());
			adapter.setList(arrayList);
			adapter.notifyDataSetInvalidated();
			break;
		}
	}
	/**
	 * 获取输入流
	 * @return
	 */
	public InputStream getInputStream(){
		InputStream inputStream = null;
		AssetManager assetManager = getAssets();
		try {
			inputStream = assetManager.open("person.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputStream;
	}
	
	
	
	
	
	
	

}






