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
	 * ViewFlipper:��ViewAnimator�� ����ӵ���������������ͼ֮����ж�������
	 * �����Ҫ�������Զ�ÿ��child֮����һ�������ת
	 */
	ViewFlipper viewFlipper;
	GestureDetector detector;//ʹ�ø�����MotionEvents���������ƺ��¼�
	Animation[] animations = new Animation[4];//����Ӧ�õ�����ͼ�����棬����������Ķ���
	
	public static final int FLUP_DISTANCE = 50;//�����ľ��볬��50px,���л�ͼƬ
	
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
		
		//����Anim����
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
		 * ֪ͨonFling�¼����������������MotionEvent��ƥ������MotionEvent����������ٶ���x���y�ᣬ��ÿ������ع�����
		 * e1:��ʼfling�ĵ�һ���·��¼���
		 * e2������ǰonFling�ƶ��¼���
		 * velocityX���ͳ�������x�������ÿ����ٶȡ�
		 * velocityY���ͳ壬��ÿ���������y��������ٶȡ�
		 */
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			//���󻬶�
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

		//����Ļ�ϻ���ʱ
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			return false;
		}

		//���û��ڴ������ϰ���,��δ�ƶ����ɿ�ʱ�����÷���
		@Override
		public void onShowPress(MotionEvent e) {
			
		}

		//�������ϵ�����¼�
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}
	}
	

}
























