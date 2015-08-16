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
		button.setText("发送意图出去");
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
	 * 检测是否有应用来处理该Intent
	 */
	public boolean isIntentAvailable(Context context, Intent intent) {
		//PackageManager:检索当前各种相关的设备上安装的应用程序包信息的类
		PackageManager packageManager = context.getPackageManager();
		// ResolveInfo:查询所有表现为Intent的活动
		List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(
				intent, 0);
		if (resolveInfo.size() > 0) {
			return true;
		}
		return false;
	}
	
	

}
