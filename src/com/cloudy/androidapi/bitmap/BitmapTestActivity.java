package com.cloudy.androidapi.bitmap;

import java.io.IOException;
import java.io.InputStream;

import com.cloudy.androidapi.R;

import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class BitmapTestActivity extends FragmentActivity{
	
	String[] images = null;
	AssetManager assetManager = null;
	int currentIma = 0;
	ImageView imageView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.bitmap_test);
		
		imageView = (ImageView) findViewById(R.id.bit_imageview);
		try {
			assetManager = getAssets();
			//��ȡ/assets/Ŀ¼�µ������ļ�
			images = assetManager.list("");
			//images = assetManager.list("images");//�����assets����ͼƬ�Ƿ���images�ļ���
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��ȡ��btn��ť
		Button button = (Button) findViewById(R.id.bitmap_btn);
		//Ϊbtn��ť���¼�������,�ü���������鿴��һ��ͼƬ
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//�����������Խ��
				if(currentIma >= images.length){
					currentIma = 0;
				}
				//�ҵ���һ��ͼƬ�ļ�
				while(!images[currentIma].endsWith(".png") && !images[currentIma].endsWith(".jpg") && !images[currentIma].endsWith(".gif")){
					currentIma++;
					//����ѷ�������Խ��
					if(currentIma >= images.length){
						currentIma = 0;
					}
				}
				InputStream assetFile = null;
				try {
					//��ָ����Դ��Ӧ��������
					assetFile = assetManager.open(images[currentIma++]);
					//�����assets����ͼƬ�Ƿ���images�ļ���
					//assetFile = assetManager.open("images"+images[currentIma++]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
				//�жϵ�ǰImageView����ʾ��ͼƬ�Ƿ��ѱ�����,�����ͼƬ��δ������,ϵͳǿ�ƻ��ո�ͼƬ
				if(bitmapDrawable != null && !bitmapDrawable.getBitmap().isRecycled()){
					bitmapDrawable.getBitmap().recycle();
				}
				//�ı�ImageView��ʾ��ͼƬ:����BitmapFactory��ָ��������������������Bitmap����
				imageView.setImageBitmap(BitmapFactory.decodeStream(assetFile));
			}
		});
	}
	
	
	
	

}
