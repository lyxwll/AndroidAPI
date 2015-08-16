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
	 * 活动操作：执行打电话操作给数据指定的某个人
	 * 输入：如果没有，空的拨号器启动;否则getData是一个电话号码的URI被拨出
	 * 或是电话：一个明确的电话号码的URI。
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
		//URI：一个明确的电话号码的URI。
		Uri uri = Uri.parse("tel:13752907959");
		intent.setData(uri);
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
