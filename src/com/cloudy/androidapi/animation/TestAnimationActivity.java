package com.cloudy.androidapi.animation;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TestAnimationActivity extends FragmentActivity{
	
	ImageView imageView;
	ImageView imageView2;
	ImageView imageView3;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.test_animation);
		
		imageView = (ImageView) findViewById(R.id.image_anim);
		imageView.setVisibility(View.VISIBLE);
		
		imageView2 = (ImageView) findViewById(R.id.image_anim2);
		imageView2.setVisibility(View.VISIBLE);
		
		imageView3 = (ImageView) findViewById(R.id.image_anim3);
		imageView3.setVisibility(View.VISIBLE);
		//loadAnimation:从资源加载一个动画对象
		Animation translateAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_animation);
		imageView.setAnimation(translateAnimation);
		
		Animation rotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_animation);
		imageView2.setAnimation(rotateAnimation);
		imageView2.startAnimation(rotateAnimation);
		
		Animation scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation);
		imageView3.setAnimation(scaleAnimation);
		
	}
	

}









