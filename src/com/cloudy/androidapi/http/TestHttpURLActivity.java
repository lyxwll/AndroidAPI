package com.cloudy.androidapi.http;

import java.io.File;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cloudy.androidapi.R;
import com.cloudy.androidapi.bean.PostresultBean;
import com.cloudy.androidapi.internet.NeteWorkUtils;
import com.cloudy.androidapi.internet.NeteWorkUtils.OnHttpComplete;
import com.cloudy.androidapi.internet.NeteWorkUtils.OnImageDownload;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class TestHttpURLActivity extends FragmentActivity {

	public static final int LOAD_IMAGE = 123;
	public static final int LOAD_PROGRESSBAR = 124;
	ImageView imageView;
	ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.test_url_layout);
		progressBar = (ProgressBar) findViewById(R.id.upload_progress);

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

		// *********************************************************************************

		String path = "http://openapk.hk2008.8ahost.com/student/student/get_stage_type.php";
		String params = "name=zhangsan1&password=123456&stage_id=11&stage_type_id=1";
		HttpParams httpParams = new HttpParams();
		httpParams.put("name", "zhangsan1");
		httpParams.put("password", "123456");
		httpParams.put("stage_id", 11);
		httpParams.put("stage_type", 1);
		HttpClientUtils.getInstance().get("http://openapk.hk2008.8ahost.com/",
				"student/student/get_stage_type.php?", httpParams,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(JSONArray jsonArray) {
					}

					@Override
					public void onSuccess(JSONObject jsonObject) {
						System.out.println("result=" + jsonObject.toString());
					}

					@Override
					public void onFailure(String result, int statusCode,
							String errorResponse) {
					}

				});

		//
		String path2 = "http://openapk.hk2008.8ahost.com/jianzhi/add_janzhi.php";
		String params2 = "jd_name=发传单&jd_type_id=88&user_id=77&jd_time=2014-05-20到2014-6-20&jd_address="
				+ "工作地点&jd_desc=工作介绍&jd_money=5252&jd_laiyuan=信息来源&"
				+ "jd_personcont=151515151&jd_username=联系人&jd_phone=联系电话&jd_phoneb=联系电话备份";
		HttpParams httpParams2 = new HttpParams();
		httpParams2.put("jd_name", "发传单");
		httpParams2.put("jd_type_id", 88);
		httpParams2.put("user_id", 77);
		httpParams2.put("jd_time", "2014-05-20到2014-6-2");
		httpParams2.put("jd_address", "工作地点");
		httpParams2.put("jd_desc", "工作介绍");
		httpParams2.put("jd_money", 5252);
		httpParams2.put("jd_laiyuan", "信息来源");
		httpParams2.put("jd_personcont", 151515151);
		httpParams2.put("jd_username", "联系人");
		httpParams2.put("jd_phone", "13752907959");
		httpParams2.put("jd_phoneb", "0234875964");
		HttpClientUtils.getInstance().post("http://openapk.hk2008.8ahost.com/",
				"jianzhi/add_janzhi.php?", httpParams2,
				new AsyncHttpResponseHandler() {
					@Override
					public void onSuccess(JSONObject jsonObject) {
						System.out.println(jsonObject.toString());
					}

					@Override
					public void onFailure(String result, int statusCode,
							String errorResponse) {
						super.onFailure(result, statusCode, errorResponse);
					}
				});

		//
		String path3 = "http://192.168.1.219/web/likun/single_upload.php";
		String targetFile = Environment.getExternalStorageDirectory()
				.toString() + File.separator + "radio.png";
		HttpParams httpParams3 = new HttpParams();
		httpParams3.put("file.png", Environment.getExternalStorageDirectory()
				.toString() + File.separator + "radio.png");
		HttpClientUtils.getInstance().upload("http://192.168.1.219/",
				"web/likun/single_upload.php", httpParams3,
				new AsyncHttpResponseHandler() {
					@Override
					public void onStartUpload() {
						System.out.println("开始上传");
					}

					@Override
					public void onUploadProgress(int progress) {
						System.out.println("upload progress=" + progress);
						Message message = new Message();
						message.what = 124;
						message.arg1 = progress;
						handler.sendMessage(message);
					}

					@Override
					public void onUploadCompleted() {
						System.out.println("上传完成");
					}

					@Override
					public void onSuccess(JSONArray jsonArray) {
						System.out.println("图片在服务器的地址:" + jsonArray.toString());
					}

					@Override
					public void onSuccess(JSONObject jsonObject) {
					}
				});

	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOAD_IMAGE:
				Bitmap bitmap = (Bitmap) msg.obj;
				imageView.setImageBitmap(bitmap);
				break;
			case LOAD_PROGRESSBAR:
				progressBar.setProgress(msg.arg1);
				break;
			}
		};
	};

}
