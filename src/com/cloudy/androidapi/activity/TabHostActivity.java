package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabHost.TabContentFactory;;


public class TabHostActivity extends TabActivity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		TabHost tabHost = getTabHost();
		
		LayoutInflater.from(this).inflate(R.layout.table_host_layout,
				tabHost.getTabContentView(),true);
		
		//��ӵ�һ����ǩҳ
		tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("�ѽӵ绰").setContent(R.id.tab01));
		//��ӵڶ�����ǩҳ
		tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("�����绰").setContent(R.id.tab02));
		//��ӵ�������ǩҳ
		tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("δ�ӵ绰").setContent(R.id.tab03));
		
		//�ڶ��ַ�ʽ
		TabSpec spec = tabHost.newTabSpec("tab3c");
		spec.setIndicator("tab3c");
		Intent intent = new Intent(this,SeekBarActivity.class);
		//spec.setContent(intent);
		spec.setContent(new TabContentFactory() {
			
			@Override
			public View createTabContent(String tag) {
				return TabHostActivity.this.getLayoutInflater()
						.inflate(R.layout.date_time_picker, null);
			}
		});
		
		tabHost.addTab(spec);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				System.out.println("tabid="+tabId);
			}
		});
		
		
	}

}
