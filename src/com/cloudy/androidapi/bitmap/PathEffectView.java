package com.cloudy.androidapi.bitmap;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.DiscretePathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathDashPathEffect;
import android.graphics.PathEffect;
import android.graphics.SumPathEffect;
import android.util.AttributeSet;
import android.view.View;

public class PathEffectView extends View{
	
	float phase;
	PathEffect[] effects = new PathEffect[7];//·��Ч��������
	int[] colors;//·��Ч����ɫ
	private Paint paint;//����
	Path path;

	public PathEffectView(Context context) {
		super(context);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		//��������ʼ��Path
		path = new Path();
		path.moveTo(0, 0);
		for(int i = 0;i <=24;i++){
			//����24����,����������ǵ�����,������������һ��ֱ��
			path.lineTo(i*20, (float) (Math.random()*60));
		}
		//��ʼ��7����ɫ����
		colors= new int[]{Color.BLACK,Color.BLUE,0xff00ffff,Color.GREEN,Color.MAGENTA,
					Color.RED,Color.YELLOW};
		
	}

	/*public PathEffectView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public PathEffectView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}*/
	
	@Override
	protected void onDraw(Canvas canvas) {
		//��������ɫ���ɰ�ɫ
		canvas.drawColor(Color.WHITE);
		//�����ʼ��7��·��Ч��
		effects[0] = null;//������·��Ч��
		//CornerPathEffect:Բ��Ч��,����10ΪԲ�ǵİ뾶
		effects[1] = new CornerPathEffect(10);
		//DiscretePathEffect:����·��Ч��,��һ������Ϊÿһ�εĳ���,�ڶ�������Ϊԭʼ·����ƫ���
		effects[2] = new DiscretePathEffect(10.0f, 15.0f);
		//DashPathEffect:����Ч��·��,��һ������������߶εĳ���,�ڶ������������ظ���ģʽ
		effects[3] = new DashPathEffect(new float[]{20,10,5,10}, phase);
		//��ʼ��PathDashPathEffect
		Path p = new Path();
		//Path.Direction.CCW:��ʱ��
		p.addRect(0, 0, 8, 8, Path.Direction.CCW);
		//PathDashPathEffect:����Ч�����Զ����µ���״(·��),����������ԭʼ·�����������
		effects[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.ROTATE);
		//ComposePathEffect:������Ч���������Ӧ��,��ʹ�õ�һ��,Ȼ���ڴ˻�����Ӧ�õڶ���
		effects[5] = new ComposePathEffect(effects[2], effects[4]);
		//SumPathEffect:˳�����һ��·�����������Ч��,����ÿ��Ч��������Ӧ�õ�ԭʼ·����,�������ֽ�����Խ������
		effects[6] = new SumPathEffect(effects[4], effects[3]);
		
		//���������Ƶ�8,8����ʼ����
		canvas.translate(8, 8);
		//����ʹ��7�ֲ�ͬ��·��Ч��,7�ֲ�ͬ����ɫ������·��
		for(int i= 0;i< effects.length;i++){
			paint.setPathEffect(effects[i]);
			paint.setColor(colors[i]);
			canvas.drawPath(path, paint);
			canvas.translate(0, 60);//�����������ƶ�60
			//�ı�phase��ֵ,�γɶ���
			phase += 1;
			invalidate();//֪ͨ�ػ�
		}
	}
	

}
