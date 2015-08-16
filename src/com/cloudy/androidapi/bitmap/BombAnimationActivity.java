package com.cloudy.androidapi.bitmap;

import java.lang.reflect.Field;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class BombAnimationActivity extends FragmentActivity{
	
	private AnimationDrawable anim;//爆炸动画
	private MyView myView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		//使用FrameLayout布局管理器
		FrameLayout frameLayout = new FrameLayout(this);
		setContentView(frameLayout);
		frameLayout.setBackgroundColor(Color.WHITE);
		myView = new MyView(this);
		//给Myview设置动画
		myView.setBackgroundResource(R.anim.bomb_animation);
		myView.setVisibility(View.INVISIBLE);//设置为隐藏
		//获取到AnimationDrawable
		anim = (AnimationDrawable) myView.getBackground();
		frameLayout.addView(myView);
		
		frameLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){//按下事件
					MediaPlayer mediaPlayer = MediaPlayer.create(BombAnimationActivity.this, R.raw.bomb);
					//先停止动画
					anim.stop();
					float x = event.getX();
					float y = event.getY();
					myView.setLocation((int)(y-40), (int)(x-20));
					myView.setVisibility(View.VISIBLE);
					//启动动画
					anim.start();
					mediaPlayer.setLooping(false);
					mediaPlayer.start();
					
					mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
						@Override
						public void onCompletion(MediaPlayer mp) {
							mp.reset();
							mp.release();
							mp = null;
						}
					});
				}
				return false;
			}
		});
	}
	
	class MyView extends ImageView{
		public MyView(Context context) {
			super(context);
		}
		public void setLocation(int top,int left){
			//控制帧动画的位置和大小
			this.setFrame(left,top,left+80,top+80);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			try {
				//此类表示的字段.关于字段的信息可以被访问,并且该字段的值可以被动态地访问
				Field field = AnimationDrawable.class.getDeclaredField("mCurFrame");
				//尝试设置访问标志的值。这个标志设置为false将使访问检查，设置为true，将禁用它们
				field.setAccessible(true);
				//获取动画的当前帧
				int curFrame = field.getInt(anim);
				//int curFrame = anim.mCurFram;
				//如果是最后一帧
				if(curFrame == anim.getNumberOfFrames()-1){
					//让该View隐藏
					setVisibility(View.INVISIBLE);
				}
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	

}
