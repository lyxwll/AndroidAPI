package com.cloudy.androidapi.mymap;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TestMapActivity extends FragmentActivity{
	
	MapView mapView;
	BaiduMap baiduMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//在使用SDK各组件之间初始化context信息,传入ApplicationContext
		//注意该方法要在setContentView方法之前实现
		SDKInitializer.initialize(getApplicationContext());
		setContentView(R.layout.test_map_layout);
		mapView = (MapView) findViewById(R.id.bmapView);
		baiduMap = mapView.getMap();
		baiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}
	
	
	
	
	

}
