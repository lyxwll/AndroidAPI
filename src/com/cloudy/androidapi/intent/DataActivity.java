package com.cloudy.androidapi.intent;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class DataActivity extends Activity implements OnClickListener{
	/**
	 * �������ִ�д�绰����������ָ����ĳ����
	 * ���룺���û�У��յĲ���������;����getData��һ���绰�����URI������
	 * ���ǵ绰��һ����ȷ�ĵ绰�����URI��
	 */
	static final String ACTION = Intent.ACTION_CALL;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout relativeLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		Button button = new Button(this);
		button.setLayoutParams(layoutParams);
		button.setText("call:13752907959");
		relativeLayout.addView(button);
		setContentView(relativeLayout);
	
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(ACTION);
		//URI��һ����ȷ�ĵ绰�����URI��
		Uri uri = Uri.parse("tel:13752907959");
		intent.setData(uri);
		//intent.setAction(ACTION);
		if(isIntentAvailable(this, intent)){
			startActivity(intent);
		}
		
	}
	
	/**
	 * ����Ƿ���Ӧ���������Intent
	 */
	public boolean isIntentAvailable(Context context, Intent intent) {
		//PackageManager:������ǰ������ص��豸�ϰ�װ��Ӧ�ó������Ϣ����
		PackageManager packageManager = context.getPackageManager();
		// ResolveInfo:��ѯ���б���ΪIntent�Ļ
		List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(
				intent, 0);
		if (resolveInfo.size() > 0) {
			return true;
		}
		return false;
	}
	
	

}
