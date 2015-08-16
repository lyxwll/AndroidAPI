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
	
	private AnimationDrawable anim;//��ը����
	private MyView myView;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		//ʹ��FrameLayout���ֹ�����
		FrameLayout frameLayout = new FrameLayout(this);
		setContentView(frameLayout);
		frameLayout.setBackgroundColor(Color.WHITE);
		myView = new MyView(this);
		//��Myview���ö���
		myView.setBackgroundResource(R.anim.bomb_animation);
		myView.setVisibility(View.INVISIBLE);//����Ϊ����
		//��ȡ��AnimationDrawable
		anim = (AnimationDrawable) myView.getBackground();
		frameLayout.addView(myView);
		
		frameLayout.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if(event.getAction() == MotionEvent.ACTION_DOWN){//�����¼�
					MediaPlayer mediaPlayer = MediaPlayer.create(BombAnimationActivity.this, R.raw.bomb);
					//��ֹͣ����
					anim.stop();
					float x = event.getX();
					float y = event.getY();
					myView.setLocation((int)(y-40), (int)(x-20));
					myView.setVisibility(View.VISIBLE);
					//��������
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
			//����֡������λ�úʹ�С
			this.setFrame(left,top,left+80,top+80);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			try {
				//�����ʾ���ֶ�.�����ֶε���Ϣ���Ա�����,���Ҹ��ֶε�ֵ���Ա���̬�ط���
				Field field = AnimationDrawable.class.getDeclaredField("mCurFrame");
				//�������÷��ʱ�־��ֵ�������־����Ϊfalse��ʹ���ʼ�飬����Ϊtrue������������
				field.setAccessible(true);
				//��ȡ�����ĵ�ǰ֡
				int curFrame = field.getInt(anim);
				//int curFrame = anim.mCurFram;
				//��������һ֡
				if(curFrame == anim.getNumberOfFrames()-1){
					//�ø�View����
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
