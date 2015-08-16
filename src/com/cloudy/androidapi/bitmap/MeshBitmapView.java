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
	//记录横纵向分割为多少格
	private final int WIDTH = 20;
	private final int HEIGHT = 20;
	//记录图片包含的顶点
	private final int COUNT = (WIDTH+1)*(HEIGHT+1)*2;
	//定义一个数组,保存21*21个顶点的XY坐标
	private final float[] orig = new float[COUNT*2];
	//定义一个数组,保存21*21个顶点经过扭曲后的XY坐标
	private final float[] verts = new float[COUNT*2];
	//所需处理的图像
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
		//根据资源Id加载图片
		bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.b2);
		//获取图片的宽高
		float imageWidth = bitmap.getWidth();
		float imageHeight = bitmap.getHeight();
		int index = 0;
		for(int y = 0;y <= HEIGHT;y++){
			float fy = imageHeight*y/HEIGHT;
			for(int x = 0;x <= WIDTH;x++){
				float fx = imageWidth*x/WIDTH;
				//初始化orig,verts数组
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
		//对Bitmap按verts数组进行扭曲,从第一个点(由第五个参数0控制)开始扭曲
		canvas.drawBitmapMesh(bitmap, WIDTH, HEIGHT, verts, 0, null, 0, null);
	}
	/**
	 * 工具方法:根据触摸事件的位置计算verts数组里面的值
	 * @param cx
	 * @param xy
	 */
	public void wrap(float cx,float cy){
		for(int i = 0;i < COUNT*2;i+=2){
			float dx = cx - orig[i+0];
			float dy = cy - orig[i+1];
			float dd = dx*dx + dy*dy;
			//计算每个坐标点与当前坐标点的距离
			float d = (float) Math.sqrt(dd);
			//计算扭曲度,距离当前点(cx,cy)越远,扭曲度越小
			float pull = 80000/(float)(dd*d);
			//对verts数组(保存Bitmap上的17*17个点经过扭曲后的坐标)重新赋值
			if(pull >= 1){
				verts[i+0] = cx;
				verts[i+1] = cy;
			}else{
				//控制各个顶点向触摸点偏移
				verts[i+0] = orig[i+0]+dx*pull;
				verts[i+1] = orig[i+1]+dx*pull;
			}
		}
		invalidate();
	}
	
	private void wipe(float cx,float cy){
		for(int i= 0;i < COUNT*2;i += 2){
			//赋值为原始值,不进行扭曲
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
