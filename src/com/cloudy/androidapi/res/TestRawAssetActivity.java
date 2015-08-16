package com.cloudy.androidapi.res;

import java.io.IOException;

import com.cloudy.androidapi.R;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.MediaController;
import android.widget.VideoView;

public class TestRawAssetActivity extends FragmentActivity{
	
	MediaPlayer mediaPlayer;
	MediaPlayer mediaPlayer2;
	VideoView videoView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.test_raw_asset);
		
		//mediaPlayer = MediaPlayer.create(this, R.raw.loves_me_not);
		findViewById(R.id.play_raw).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mediaPlayer.start();
			}
		});
		
		AssetManager assetManager = getAssets();//获取到AssetManager
		AssetFileDescriptor assetFileDescriptor;
		try {
			assetFileDescriptor = assetManager.openFd("mp3/loves_me_not.mp3");
			mediaPlayer2 = new MediaPlayer();
			mediaPlayer2.setDataSource(assetFileDescriptor.getFileDescriptor());
			mediaPlayer2.prepare();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		findViewById(R.id.play_asset).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mediaPlayer2.start();
			}
		});
		
		videoView = (VideoView) findViewById(R.id.play_video);
		//MediaController将创建控件的默认设置，并把它们放在一个窗口浮在上面的应用程序。
		//如果空闲三秒窗口将消失，当用户触摸所述锚固视图出现。
		MediaController controller = new MediaController(this);//初始化一个控制面板
		videoView.setMediaController(controller);
		Uri uri = Uri.parse("http://192.168.1.220/33.mp4");
		videoView.setVideoURI(uri);
		videoView.requestFocus();
		videoView.start();//开始播放
		
		
	}

}
