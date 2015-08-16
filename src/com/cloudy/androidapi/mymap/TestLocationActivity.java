package com.cloudy.androidapi.mymap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//假如用到位置提醒功能，需要import该类
//如果使用地理围栏功能，需要import如下类
import com.baidu.location.BDGeofence;
import com.baidu.location.BDLocationStatusCodes;
import com.baidu.location.GeofenceClient;
import com.baidu.location.GeofenceClient.OnAddBDGeofencesResultListener;
import com.baidu.location.GeofenceClient.OnGeofenceTriggerListener;
import com.baidu.location.GeofenceClient.OnRemoveBDGeofencesResultListener;
import com.baidu.location.LocationClientOption.LocationMode;

public class TestLocationActivity extends FragmentActivity{
	
	public LocationClient mLocationClient = null;//定位客户端
	public BDLocationListener mListener = new MyLocationListener();//定位监听
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mLocationClient = new LocationClient(getApplicationContext());//声明LocationClientlei
		mLocationClient.registerLocationListener(mListener);//注册监听函数
		setOption(mLocationClient);
		
	}
	
	public void setOption(LocationClient mLocationClient){
		LocationClientOption clientOption = new LocationClientOption();
		clientOption.setLocationMode(LocationMode.Hight_Accuracy);//设置定位模式
		clientOption.setCoorType("bd0911");//返回的定位结果是百度经纬度,默认值gcj02
		clientOption.setScanSpan(5000);//设置发起定位请求的间隔时间为5000s
		clientOption.setIsNeedAddress(true);//返回的定位结果包含地址信息
		clientOption.setNeedDeviceDirect(true);//返回的定位结果包含手机机头的方向
		mLocationClient.setLocOption(clientOption);
		mLocationClient.start();//启动定位服务
		mLocationClient.requestLocation();//发起定位请求,异步获取当前位置
	}
	
	class MyLocationListener implements BDLocationListener{
		/**
		 * 获取定位位置时,回调此方法
		 */
		@Override
		public void onReceiveLocation(BDLocation location) {
			if(location == null)
				return;
			StringBuffer buffer = new StringBuffer(256);
			buffer.append("time:");
			buffer.append(location.getTime());
			buffer.append("\nerror code :");
			buffer.append(location.getLocType());
			buffer.append("\nlatitude :");
			buffer.append(location.getLatitude());
			buffer.append("\nlontitude :");
			buffer.append(location.getLongitude());
			buffer.append("\nradius :");
			buffer.append(location.getRadius());
			
			if(location.getLocType() == BDLocation.TypeGpsLocation){
				buffer.append("\nspeed : ");
				buffer.append(location.getSpeed());
				buffer.append("\nsatellite : ");
				buffer.append(location.getSatelliteNumber());
			}else if(location.getLocType() == BDLocation.TypeNetWorkLocation){
				buffer.append("\naddr : ");
				buffer.append(location.getAddrStr());
			}
			System.out.println(buffer.toString());
		}
		
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mLocationClient != null && mLocationClient.isStarted()){
			mLocationClient.stop();
		}
	}
	
	
	
	
	
	
	

}
