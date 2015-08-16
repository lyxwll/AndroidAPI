package com.cloudy.androidapi.bitmap;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class TestMyDrawView extends View{
	
	public TestMyDrawView(Context context) {
		this(context,null);
	}

	public TestMyDrawView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public TestMyDrawView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);//���ð�ɫ�Ļ���
		Paint paint = new Paint();
		paint.setAntiAlias(true);//ȥ�����
		paint.setARGB(255, 0, 0, 255);//������ɫͨ��
		//paint.setColor(0xffff0000);//������ɫֵ
		paint.setStyle(Paint.Style.STROKE);//���û�����ʽΪ������
		paint.setStrokeWidth(3);//���߿��
		
		//����Բ��
		canvas.drawCircle(40, 40, 30, paint);
		//����������
		canvas.drawRect(10, 80, 70, 140, paint);
		//���ƾ���
		canvas.drawRect(10, 150, 70, 190, paint);
		//����Բ�Ǿ���
		RectF rectF1 = new RectF(10, 200, 70, 230);
		canvas.drawRoundRect(rectF1, 15,15,paint);
		//������Բ
		RectF rectF2 = new RectF(10, 240, 70, 270);
		canvas.drawOval(rectF2, paint);
		//����һ��Path����,��ճ�һ��������
		Path path = new Path();
		path.moveTo(10, 340);
		path.lineTo(70, 340);
		path.lineTo(40, 290);
		path.close();
		//����Path����������
		canvas.drawPath(path, paint);
		//����һ��Path����,��ճ�һ�������
		Path path2 = new Path();
		path2.moveTo(26, 360);
		path2.lineTo(54, 360);
		path2.lineTo(70, 392);
		path2.lineTo(40, 420);
		path2.lineTo(10, 392);
		path2.close();
		//����Path2���������
		canvas.drawPath(path2, paint);
		
		//�������������
		paint.setStyle(Paint.Style .FILL);
		paint.setColor(Color.RED);
		//
		canvas.drawCircle(120, 40, 30, paint);
		//
		canvas.drawRect(90, 80,150,140 ,paint);
		//
		canvas.drawRect(90, 150, 150, 190, paint);
		//
		RectF rectF = new RectF(90, 200, 150, 230);
		canvas.drawRoundRect(rectF, 15, 15, paint);
		//
		RectF rectF3 = new RectF(90, 240, 150, 270);
		canvas.drawOval(rectF3, paint);
		
		//
		Path path3 = new Path();
		path3.moveTo(90, 340);
		path3.lineTo(150, 340);
		path3.lineTo(120, 290);
		path3.close();
		//
		canvas.drawPath(path3, paint);
		//
		Path path4 = new Path();
		path4.moveTo(106, 360);
		path4.lineTo(134, 360);
		path4.lineTo(150, 392);
		path4.lineTo(120, 420);
		path4.lineTo(90, 392);
		path4.close();
		//
		canvas.drawPath(path4, paint);
		
		//*************************************
		//���ý����������,ΪPaint���ý�����
		/*����LinearGradient�����ý�����ɫ����,
		 * ��һ��,�ڶ���������ʾ�������  ����������� �յ��ڶԽǵ�����λ��
		 * ����,���ĸ�������ʾ�����յ�;�����������ʾ������ɫ;
		 * ������������ʾ����,����Ϊ��,ֵΪ0--1 new float[]{0.25f,0.5f,0.75f,1},���Ϊ��,��ɫ���ȷֲ�,���ݶ���
		 * ���߸���������ʾƽ�̷�ʽ
		 * CLAMP�ظ����һ����ɫ�����
		 * MIRROR:�ظ���ɫ��ͼ��ˮƽ��ֱ�����Ծ���ʽ�����з�תЧ��
		 * REPEAT:�ظ���ɫ��ͼ��ˮƽ��ֱ����
		 */
		Shader mShader = new LinearGradient(10, 10, 60, 60, new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.YELLOW,Color.BLACK}, 
											null, Shader.TileMode.REPEAT);
		paint.setShader(mShader);//���û������Ч��,���Խ���
		//������Ӱ
		paint.setShadowLayer(50, 10, 10, Color.BLACK);
		//����Բ��
		canvas.drawCircle(200, 40, 30, paint);
		//����������
		canvas.drawRect(170, 80, 230, 140, paint);
		//���ƾ���
		canvas.drawRect(170, 150, 230, 190, paint);
		//����Բ�Ǿ���
		RectF rectF4 = new RectF(170, 200, 230, 230);
		canvas.drawRoundRect(rectF4, 15, 15, paint);
		//������Բ
		RectF rectF5 = new RectF(170, 240, 230, 270);
		canvas.drawOval(rectF5, paint);
		//
		Path path5 = new Path();
		path5.moveTo(170, 340);
		path5.lineTo(230, 340);
		path5.lineTo(200, 290);
		path5.close();
		//����������
		canvas.drawPath(path5, paint);
		//
		Path path6 = new Path();
		path6.moveTo(186, 360);
		path6.lineTo(214, 360);
		path6.lineTo(230, 392);
		path6.lineTo(200, 420);
		path6.lineTo(170, 392);
		path6.close();
		//���������
		canvas.drawPath(path6, paint);
		
		//***************
		//�����ַ���С�����
		paint.setTextSize(24);
		paint.setShader(null);
		//�����߸��ַ�
		canvas.drawText(getResources().getString(R.string.circle), 340, 50, paint);
		canvas.drawText(getResources().getString(R.string.square), 340, 120, paint);
		canvas.drawText(getResources().getString(R.string.rectangle), 340, 175, paint);
		canvas.drawText(getResources().getString(R.string.round_rect), 340, 220, paint);
		canvas.drawText(getResources().getString(R.string.oval), 340, 260, paint);
		canvas.drawText(getResources().getString(R.string.triangle), 340, 325, paint);
		canvas.drawText(getResources().getString(R.string.pentagon), 340, 390, paint);
	}
	
	

}
