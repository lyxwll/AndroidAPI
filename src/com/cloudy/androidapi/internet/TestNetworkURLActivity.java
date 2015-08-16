package com.cloudy.androidapi.internet;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.bean.PostresultBean;
import com.cloudy.androidapi.internet.NeteWorkUtils.OnHttpComplete;
import com.cloudy.androidapi.internet.NeteWorkUtils.OnImageDownload;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

public class TestNetworkURLActivity extends FragmentActivity{
	
	public static final int LOAD_IMAGE = 123;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.test_url_layout);
		
		imageView = (ImageView) findViewById(R.id.image_url);
		String url = "http://192.168.1.219/hanchang/images/advert_manager_bg.jpg";
		
		new NeteWorkUtils.NetWorkThread(url, new OnImageDownload() {
			
			@Override
			public void imageDownloaded(Bitmap bitmap) {
				Message message = new Message();
				message.what = LOAD_IMAGE;
				message.obj = bitmap;
				handler.sendMessage(message);
			}
		}).start();
		
		//*********************************************************************************
		
		String path = "http://openapk.hk2008.8ahost.com/student/student/get_stage_type.php";
		String params = "name=zhangsan1&password=123456&stage_id=11&stage_type_id=1";
		
		NeteWorkUtils.get(path, params, new OnHttpComplete() {
			@Override
			public void onSuccess(String result) {
				System.out.println("result=" + result);
			}
		});
		
		//����post����
		String path2 = "http://openapk.hk2008.8ahost.com/jianzhi/add_janzhi.php";
		String params2 = "jd_name=������&jd_type_id=88&user_id=77&jd_time=2014-05-20��2014-6-20&jd_address=" +
						"�����ص�&jd_desc=��������&jd_money=5252&jd_laiyuan=��Ϣ��Դ&" +
						"jd_personcont=151515151&jd_username=��ϵ��&jd_phone=��ϵ�绰&jd_phoneb=��ϵ�绰����";
		
		NeteWorkUtils.post(path2, params2, new OnHttpComplete() {
			@Override
			public void onSuccess(String result) {
				System.out.println("post_result=" + result);
				try {
					JSONObject jsonObject = new JSONObject(result);
					PostresultBean bean = new PostresultBean();
					bean.error = jsonObject.optInt("error",-1);
					bean.result = jsonObject.optString("result", "");
					bean.operation = jsonObject.optString("operation", "");
					System.out.println(bean);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
		
		//
		String path3 = "http://192.168.1.219/33.mp3";
		String targetFile = Environment.getExternalStorageDirectory().toString() + File.separator + "33.mp3";
		System.out.println("targetFile =" + targetFile);
		new DownLoadManager(path3, targetFile, new DownListener() {
			
			@Override
			public void onStartDownLoad() {
				System.out.println("��ʼ����");
			}
			
			@Override
			public void onDownloadCompleted(String fileName) {
				System.out.println("�������");
			}
			
			@Override
			public void onCompleteRateChanged(int completeRate) {
				System.out.println("����:" + completeRate);
			}
		}).download();
	}
	
	Handler handler = new Handler(){ 
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOAD_IMAGE:
				Bitmap bitmap = (Bitmap) msg.obj;
				imageView.setImageBitmap(bitmap);
				break;
			}
		};
	};

}
