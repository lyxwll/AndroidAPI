package com.cloudy.androidapi.applicationpush;

import com.baidu.frontia.FrontiaApplication;

import android.app.Application;
import android.content.res.Configuration;

public class MyAndroidApplication extends FrontiaApplication {
/**
 * ������Ĺ�����ʵ����Application�ļ̳��࣬��ô������Ҫ�������Ϊcom.baidu.frontia.FrontiaApplication��
 * �����û��ʵ��Application�ļ̳��࣬��ô������AndroidManifest.xml��Application��ǩ���������ԣ� 
 * <application android:name="com.baidu.frontia.FrontiaApplication"
 */
	@Override
	public void onCreate() {
		super.onCreate();

	}
}