package com.cloudy.androidapi.mymap;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.BDNotifyListener;//�����õ�λ�����ѹ��ܣ���Ҫimport����
//���ʹ�õ���Χ�����ܣ���Ҫimport������
import com.baidu.location.BDGeofence;
import com.baidu.location.BDLocationStatusCodes;
import com.baidu.location.GeofenceClient;
import com.baidu.location.GeofenceClient.OnAddBDGeofencesResultListener;
import com.baidu.location.GeofenceClient.OnGeofenceTriggerListener;
import com.baidu.location.GeofenceClient.OnRemoveBDGeofencesResultListener;
import com.baidu.location.LocationClientOption.LocationMode;

public class TestLocationActivity extends FragmentActivity{
	
	public LocationClient mLocationClient = null;//��λ�ͻ���
	public BDLocationListener mListener = new MyLocationListener();//��λ����
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mLocationClient = new LocationClient(getApplicationContext());//����LocationClientlei
		mLocationClient.registerLocationListener(mListener);//ע���������
		setOption(mLocationClient);
		
	}
	
	public void setOption(LocationClient mLocationClient){
		LocationClientOption clientOption = new LocationClientOption();
		clientOption.setLocationMode(LocationMode.Hight_Accuracy);//���ö�λģʽ
		clientOption.setCoorType("bd0911");//���صĶ�λ����ǰٶȾ�γ��,Ĭ��ֵgcj02
		clientOption.setScanSpan(5000);//���÷���λ����ļ��ʱ��Ϊ5000s
		clientOption.setIsNeedAddress(true);//���صĶ�λ���������ַ��Ϣ
		clientOption.setNeedDeviceDirect(true);//���صĶ�λ��������ֻ���ͷ�ķ���
		mLocationClient.setLocOption(clientOption);
		mLocationClient.start();//������λ����
		mLocationClient.requestLocation();//����λ����,�첽��ȡ��ǰλ��
	}
	
	class MyLocationListener implements BDLocationListener{
		/**
		 * ��ȡ��λλ��ʱ,�ص��˷���
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
