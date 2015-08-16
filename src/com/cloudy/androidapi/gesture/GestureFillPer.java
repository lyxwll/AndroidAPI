package com.cloudy.androidapi.gesture;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class GestureFillPer extends FragmentActivity{
	
	/**
	 * ViewFlipper:简单ViewAnimator将 已添加到它的两个或多个视图之间进行动画处理
	 * 如果需要，可以自动每个child之间以一定间隔翻转
	 */
	ViewFlipper viewFlipper;
	GestureDetector detector;//使用附带的MotionEvents检测各种手势和事件
	Animation[] animations = new Animation[4];//可以应用到的试图，表面，或其它物体的动画
	
	public static final int FLUP_DISTANCE = 50;//滑动的距离超过50px,就切换图片
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.vire_fillper);
		
		viewFlipper = (ViewFlipper) findViewById(R.id.view_fillper);
		detector = new GestureDetector(new MyGesture());
		
		viewFlipper.addView(addImageView(R.drawable.images1));
		viewFlipper.addView(addImageView(R.drawable.images2));
		viewFlipper.addView(addImageView(R.drawable.images3));
		viewFlipper.addView(addImageView(R.drawable.images4));
		viewFlipper.addView(LayoutInflater.from(this).inflate(R.layout.userlogin_layout, null));
		viewFlipper.addView(LayoutInflater.from(this).inflate(R.layout.button_layout, null));
		
		//加载Anim布局
		animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
		animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
		animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
		animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);
		
	}
	
	float startX;
	float endX;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return detector.onTouchEvent(event);
		/*switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			if(startX - endX > FLUP_DISTANCE){
				viewFlipper.setInAnimation(animations[0]);
				viewFlipper.setInAnimation(animations[1]);
				viewFlipper.showPrevious();
				return true;
			}
			if(endX - startX > FLUP_DISTANCE){
				viewFlipper.setInAnimation(animations[2]);
				viewFlipper.setInAnimation(animations[3]);
				viewFlipper.showNext();
				return true;
			}
			break;
		}
		return false;*/
	}
	
	public ImageView addImageView(int resId){
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(resId);
		imageView.setScaleType(ImageView.ScaleType.CENTER);
		return imageView;
	}
	
	class MyGesture implements OnGestureListener{

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		/**
		 * 通知onFling事件当发生最初下来的MotionEvent和匹配起来MotionEvent。所计算的速度沿x轴和y轴，以每秒的像素供给。
		 * e1:开始fling的第一个下发事件。
		 * e2触发当前onFling移动事件。
		 * velocityX此猛冲像素沿x轴测量的每秒的速度。
		 * velocityY此猛冲，以每秒的像素沿y轴测量的速度。
		 */
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			//向左滑动
			if(e1.getX() - e2.getX() > FLUP_DISTANCE){
				viewFlipper.setInAnimation(animations[0]);
				viewFlipper.setInAnimation(animations[1]);
				viewFlipper.showPrevious();
				return true;
			}
			if(e2.getX() - e1.getX() > FLUP_DISTANCE){
				viewFlipper.setInAnimation(animations[2]);
				viewFlipper.setInAnimation(animations[3]);
				viewFlipper.showNext();
				return true;
			}
			return false;
		}

		@Override
		public void onLongPress(MotionEvent e) {
			
		}

		//在屏幕上滑动时
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		//当用户在触摸屏上按下,且未移动和松开时触发该方法
		@Override
		public void onShowPress(MotionEvent e) {
			
		}

		//触摸屏上的轻击事件
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
	}
	

}
























