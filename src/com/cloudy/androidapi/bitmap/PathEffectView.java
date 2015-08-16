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
	PathEffect[] effects = new PathEffect[7];//路径效果的数组
	int[] colors;//路径效果颜色
	private Paint paint;//画笔
	Path path;

	public PathEffectView(Context context) {
		super(context);
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(4);
		//创建并初始化Path
		path = new Path();
		path.moveTo(0, 0);
		for(int i = 0;i <=24;i++){
			//生成24个点,随机生成他们的坐标,并将他们连成一条直线
			path.lineTo(i*20, (float) (Math.random()*60));
		}
		//初始化7个颜色数组
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
		//将背景颜色填充成白色
		canvas.drawColor(Color.WHITE);
		//下面初始化7个路径效果
		effects[0] = null;//不适用路径效果
		//CornerPathEffect:圆角效果,参数10为圆角的半径
		effects[1] = new CornerPathEffect(10);
		//DiscretePathEffect:折线路径效果,第一个参数为每一段的长度,第二个参数为原始路径的偏离度
		effects[2] = new DiscretePathEffect(10.0f, 15.0f);
		//DashPathEffect:虚线效果路径,第一个数组控制虚线段的长度,第二个参数设置重复的模式
		effects[3] = new DashPathEffect(new float[]{20,10,5,10}, phase);
		//初始化PathDashPathEffect
		Path p = new Path();
		//Path.Direction.CCW:逆时针
		p.addRect(0, 0, 8, 8, Path.Direction.CCW);
		//PathDashPathEffect:这种效果可以定义新的形状(路径),并讲其用作原始路径的轮廓标记
		effects[4] = new PathDashPathEffect(p, 12, phase, PathDashPathEffect.Style.ROTATE);
		//ComposePathEffect:将两种效果组合起来应用,先使用第一种,然后在此基础上应用第二种
		effects[5] = new ComposePathEffect(effects[2], effects[4]);
		//SumPathEffect:顺序地在一条路径中添加两种效果,这样每种效果都可以应用到原始路径中,而且两种结果可以结合起来
		effects[6] = new SumPathEffect(effects[4], effects[3]);
		
		//将画布下移到8,8处开始绘制
		canvas.translate(8, 8);
		//依次使用7种不同的路径效果,7种不同的颜色来绘制路径
		for(int i= 0;i< effects.length;i++){
			paint.setPathEffect(effects[i]);
			paint.setColor(colors[i]);
			canvas.drawPath(path, paint);
			canvas.translate(0, 60);//将画布向下移动60
			//改变phase的值,形成动画
			phase += 1;
			invalidate();//通知重绘
		}
	}
	

}
