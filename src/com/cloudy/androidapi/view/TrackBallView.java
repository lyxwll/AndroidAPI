package com.cloudy.androidapi.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 * �Զ���view:������Բ��
 * ������������������д����
 */
public class TrackBallView extends View {
	//Բ������
	public int currentX = 100;
	public int currentY = 100;

	//ʹ��new���� viewʱ;�����һ�������Ĺ�����,��Log��־�в鿴
	public TrackBallView(Context context) {
		super(context);
		//this(context,null);
		Log.d(VIEW_LOG_TAG, "TrackBallView(Context context)");
	}

	//ʹ���Զ��岼���ļ�,��������������Ĺ�����
	public TrackBallView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//this(context,attrs,0);
		Log.d(VIEW_LOG_TAG, "TrackBallView(Context context, AttributeSet attrs)");
	}

	//�ڲ����ļ���ʹ��ʱ,��������style����ʱ,��������������Ĺ�����
	public TrackBallView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		Log.d(VIEW_LOG_TAG, "TrackBallView(Context context, AttributeSet attrs, int defStyle)");
	}

	@SuppressLint("DrawAllocation") 
	////����Բ�Ľ���
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//����һ������
		Paint paint =new Paint();
		//�����
		paint.setAntiAlias(true);
		//���û�����ɫ
		paint.setColor(Color.BLUE);
		//��Բ,�뾶Ϊ:25
		canvas.drawCircle(currentX, currentY, 25, paint);
	}

}
