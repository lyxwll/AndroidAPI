package com.cloudy.androidapi.intent;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class CategoryActivity extends Activity implements OnClickListener{
	
	static final String ACTION = "com.cloudy.androidapi.intent.CategoryActivity";
	static final String CATEGORY = "com.cloudy.androidapi.intent.MY_CATEGORY";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		RelativeLayout relativeLayout = new RelativeLayout(this);
		RelativeLayout.LayoutParams layoutParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, android.widget.LinearLayout.LayoutParams.WRAP_CONTENT);
		Button button = new Button(this);
		button.setLayoutParams(layoutParams);
		button.setText("������ͼ��ȥ");
		relativeLayout.addView(button);
		setContentView(relativeLayout);
	
		button.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		Intent intent = new Intent(ACTION);
		intent.addCategory(CATEGORY);
		intent.addCategory(Intent.CATEGORY_DEFAULT);
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
