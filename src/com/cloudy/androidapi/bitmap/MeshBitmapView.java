package com.cloudy.androidapi.bitmap;

import com.cloudy.androidapi.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MeshBitmapView extends View{
	//��¼������ָ�Ϊ���ٸ�
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	//��¼ͼƬ�����Ķ���
	private final int COUNT = (WIDTH+1)*(HEIGHT+1)*2;
	//����һ������,����21*21�������XY����
	private final float[] orig = new float[COUNT*2];
	//����һ������,����21*21�����㾭��Ť�����XY����
	private final float[] verts = new float[COUNT*2];
	//���账���ͼ��
	private Bitmap bitmap;
	
	public MeshBitmapView(Context context) {
		this(context,null);
	}
	public MeshBitmapView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	public MeshBitmapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setFocusable(true);
		//������ԴId����ͼƬ
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.b2);
		//��ȡͼƬ�Ŀ��
		float imageWidth = bitmap.getWidth();
		float imageHeight = bitmap.getHeight();
		int index = 0;
		for(int y = 0;y <= HEIGHT;y++){
			float fy = imageHeight*y/HEIGHT;
			for(int x = 0;x <= WIDTH;x++){
				float fx = imageWidth*x/WIDTH;
				//��ʼ��orig,verts����
				orig[index*2+0] = verts[index * 2+0] = fx;
				orig[index*2+1] = verts[index*2 + 1] = fy;
				index += 1;
			}
		}
		setBackgroundColor(Color.WHITE);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//��Bitmap��verts�������Ť��,�ӵ�һ����(�ɵ��������0����)��ʼŤ��
		canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
	}
	/**
	 * ���߷���:���ݴ����¼���λ�ü���verts���������ֵ
	 * @param cx
	 * @param xy
	 */
	public void wrap(float cx,float cy){
		for(int i = 0;i < COUNT*2;i+=2){
			float dx = cx - orig[i+0];
			float dy = cy - orig[i+1];
			float dd = dx*dx + dy*dy;
			//����ÿ��������뵱ǰ�����ľ���
			float d = (float) Math.sqrt(dd);
			//����Ť����,���뵱ǰ��(cx,cy)ԽԶ,Ť����ԽС
			float pull = 80000/(float)(dd*d);
			//��verts����(����Bitmap�ϵ�17*17���㾭��Ť���������)���¸�ֵ
			if(pull >= 1){
				verts[i+0] = cx;
				verts[i+1] = cy;
			}else{
				//���Ƹ�������������ƫ��
				verts[i+0] = orig[i+0]+dx*pull;
				verts[i+1] = orig[i+1]+dx*pull;
			}
		}
		invalidate();
	}
	
	private void wipe(float cx,float cy){
		for(int i= 0;i < COUNT*2;i += 2){
			//��ֵΪԭʼֵ,������Ť��
			verts[i+0] = orig[i+0];
			verts[i+1] = orig[i+1];
		}
		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			wrap(event.getX(), event.getY());
			break;
		case MotionEvent.ACTION_UP:
			wipe(event.getX(), event.getY());
			break;
		}
		return true;
	}
	
	
	

}
