package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.bean.Person;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RemoteViews;

public class NotificationActivity extends Activity{
	
	private static final int NOTIFICATION_ID = 0x1234;
	private static final int CUSTOM_NOTIFICATION_ID = 0x123456;
	
	private static final int UPDATE_PROGRESS = 0x12;
	public int max = 100;
	public int progress = 0;
	
	NotificationManager notificationManager;
	Notification notification;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//ʵ��Parcelable���л�
		if(getIntent()!=null){
			//getExtras()����ͼ�м���һ��map��չ����
			Bundle bundle = getIntent().getExtras();
			if(bundle.containsKey("person")){
				/* getParcelable();�������������ص�ֵ����NULL
				 * ����������͵�ӳ�䲻���ڸ��������ֵ��ʽ��������� */
				Person person = bundle.getParcelable("person");
				person.print();
			}
		}
		
		setContentView(R.layout.notify_layout);
		
		findViewById(R.id.show_notify).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//��ȡ��NotificationManager
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				Intent intent = new Intent(NotificationActivity.this, PopupWindowActivity.class);
				//��Intent���з�װ
				PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
				//ʵ����һ��Notification����
				notification = new Notification();
				//����Notification������
				notification.icon = R.drawable.ic_launcher;//����icon
				//��ȡtickerText������
				notification.tickerText = getResources().getString(R.string.ticker_text);
				notification.defaults = Notification.DEFAULT_SOUND;
				notification.when = System.currentTimeMillis();
				notification.defaults = Notification.DEFAULT_ALL;
				notification.setLatestEventInfo(NotificationActivity.this,
						getString(R.string.tilte_notify), getString(R.string.content_notify), pendingIntent);
				notificationManager.notify(NOTIFICATION_ID, notification);
				
			}
		});
		
		findViewById(R.id.del_notify).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//ϵͳ֪ͨ������
				NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.cancel(NOTIFICATION_ID);
				
			}
		});
		
		findViewById(R.id.custom_show_notify).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				progress = 0;// ÿ�ε��ǰ  ����Ϊ0
				notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				Intent intent = new Intent(NotificationActivity.this, PopupWindowActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this, 0, intent, 0);
				//ʵ����һ��notification����
				notification = new Notification();
				notification.icon = R.drawable.fruit_apple;
				notification.tickerText = getResources().getString(R.string.ticker_text);
				notification.when = System.currentTimeMillis();
				notification.defaults = Notification.DEFAULT_ALL;
				RemoteViews remoteViews = new RemoteViews(NotificationActivity.this.getPackageName(), R.layout.notification_custom_layout);
				remoteViews.setImageViewResource(R.id.custom_notify_icon, R.drawable.fruit_apple);
				remoteViews.setTextViewText(R.id.custom_notify_title, getString(R.string.custom_notify_content));
				remoteViews.setProgressBar(R.id.custom_notify_progress, max, progress, false);
				notification.contentView = remoteViews;
				notification.contentIntent = pendingIntent;
				notificationManager.notify(CUSTOM_NOTIFICATION_ID, notification);
				new MyThread().start();
			}
		});
		
		findViewById(R.id.custom_del_notify).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				notificationManager.cancel(CUSTOM_NOTIFICATION_ID);
			}
		});
	}

	class MyThread extends Thread {
		boolean flag = true;
		@Override
		public void run() {
			while(flag) {
				if(progress <= max) {
					handler.sendEmptyMessage(UPDATE_PROGRESS);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					flag = false;//���ñ�־λ���߳�ֹͣ
				}
			}
		}
	}
	
	Handler handler = new Handler () {
		public void handleMessage(Message msg) {
			switch(msg.what) {
			case UPDATE_PROGRESS:
				if(progress <= max) {
					progress += 5;
					notification.contentView.setProgressBar(R.id.custom_notify_progress, max, progress, false);
					notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
					notificationManager.notify(CUSTOM_NOTIFICATION_ID, notification);
				}
				break;
			}
		};
	};

}
