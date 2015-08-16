package com.cloudy.androidapi.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * ����˫����ʵ�ֻ�ͼ��
 *DrawBitmapView
 * @author Administrator
 */
public class DrawBitmapView extends View{
	
	float preX;//ǰһ�������X
	float preY;//ǰһ�������Y
	private Path path;
	public Paint paint = null;
	
	//�ؼ��Ŀ��
	final int VIEW_WIDTH;
	final int VIEW_HEIGHT;
	//����һ���ڴ��е�ͼƬ,��ͼƬ��Ϊ������
	Bitmap cacheBitmap = null;
	//���建�����Ļ�������
	Canvas cacheCanvas = null;

	public DrawBitmapView(Context context) {
		super(context);
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		
		VIEW_WIDTH = windowManager.getDefaultDisplay().getWidth();
		VIEW_HEIGHT = windowManager.getDefaultDisplay().getHeight();
		
		//����һ���뻺������С��ȵ�Bitmap
		cacheBitmap = Bitmap.createBitmap(VIEW_WIDTH, VIEW_HEIGHT, Bitmap.Config.ARGB_8888);
		cacheCanvas = new Canvas();//����һ�黭��
		path = new Path();
		//��cacheCanvas���Ƶ��ڴ��е�cacheBitmap��
		cacheCanvas.setBitmap(cacheBitmap);
		//���û��ʵ���ɫ
		paint = new Paint(Paint.DITHER_FLAG);//����λ��־ʹ����
		paint.setColor(Color.RED);
		//���û��ʷ��
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(2);
		paint.setAntiAlias(true);
		paint.setDither(true);//���÷�����
		
	}

	/*public DrawBitmapView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public DrawBitmapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}*/
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//��ȡ�϶��¼�������λ��
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			path.moveTo(x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			//�����һ��������һ�����α��������ӽ����Ƶ㣨X1��Y1���������ڣ�X2��Y2��������
			//���û��MOVETO����������Ϊ�����������һ�㱻�Զ�����Ϊ��0,0����
			path.quadTo(preX, preY, x, y);
			preX = x;
			preY = y;
			break;
		case MotionEvent.ACTION_UP:
			cacheCanvas.drawPath(path, paint);
			path.reset();//����κ�ֱ�ߺ����ߵ�·����ʹ��ա��ⲻ��ı�����������á�
			break;
		}
		invalidate();//֪ͨ�ػ�
		//����true,��ʾ�����Ѿ��������,�¼������·�
		return true;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		Paint bmpPaint = new Paint();
		//��cacheBitmap�����ڸ�View�����
		canvas.drawBitmap(cacheBitmap, 0, 0, bmpPaint);
		//����Path����
		canvas.drawPath(path, paint);
		
		
	}
	
	

}
