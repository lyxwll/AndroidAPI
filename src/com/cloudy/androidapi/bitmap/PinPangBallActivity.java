package com.cloudy.androidapi.bitmap;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class PinPangBallActivity extends FragmentActivity{
	//桌面的宽度高度
	private int tableWidth;
	private int tableHeight;
	//球拍的垂直位置
	private int racketY;
	//定义球拍的宽度,高度
	private int PACKET_WIDTH = 50;
	private int PACKET_HEIGHT = 100;
	//球的大小
	private final int BALL_SIZE = 25;
	
	//球纵向的运行速度
	private int ySpeed = 20;
	Random random = new Random();
	//返回一个-0.5--0.5的比率.用于控制小球的运行方向
	private double xyRate = random.nextDouble()-0.5;
	//小球横向运行速度
	private int xSpeed = (int) (ySpeed*xyRate*2);
	//ballX,ballY代表小球的坐标  初始坐标
	private int ballX = random.nextInt(200)+20;
	private int ballY = random.nextInt(10)+20;
	//racketX 代表球拍的水平位置
	private int racketX = random.nextInt(200);
	//游戏是否结束
	private boolean isLose = false;
	
	//横向滑动
	float startX;
	float startY;
	float endX;
	float endY;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
							WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//创建pinpangView 控件
		final GameView gameView = new GameView(this);
		setContentView(gameView);//
		//获取窗口管理器
		WindowManager windowManager = getWindowManager();
		//获取屏幕的宽高
		tableWidth = windowManager.getDefaultDisplay().getWidth();
		tableHeight = windowManager.getDefaultDisplay().getHeight();
		PACKET_WIDTH = tableWidth/2;
		racketX = 0;
		racketY = tableHeight - 80;//球拍的Y坐标
		
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 123:
					gameView.invalidate();
					break;
				}
			}
		};
		//Touch事件控制球拍的位置,左右滑动
		gameView.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					System.out.println("ACTION_DOWN");
					startX = event.getX();
					startY = event.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					System.out.println("ACTION_MOVE");
					endX = event.getX();
					endY = event.getY();
					if((endX - startX) > 10 && racketX < tableWidth-BALL_SIZE){//右滑,且球拍未超过右边边界
						racketX += 15;
						startX = endX;
						startY = endY;
					}else if((startX - endX) > 10 && racketX > 0){//左滑,且球拍未超过左边边界
						racketX -= 15;
						startX = endX;
						startY = endY;
					}
					break;
				case MotionEvent.ACTION_UP:
					System.out.println("ACTION_UP");
					break;
				}
				gameView.invalidate();
				return true;
			}
		});
		
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				//如果小球 碰到左边边界或右边边界
				if(ballX <= 0||ballX >= tableWidth-BALL_SIZE){//碰撞边缘弹回,速度方向变反
					xSpeed = -xSpeed;//回弹,改变速度方向
				}
				//如果小球的高度超出了球拍的位置,且横向不在球拍范围之内,游戏结束
				if(ballY >= racketY-BALL_SIZE &&(ballX < racketX||ballX > racketX+ PACKET_WIDTH)){
					timer.cancel();
					//设置游戏结束标志为true
					isLose = true;
				}
				//如果小球位于球拍之内,且达到球拍位置,即球拍拍到了小球,则小球反弹出去
				else if(ballY <= 0 //小球撞到最上边的边框
						||(ballY >= racketY -BALL_SIZE//小球的高度超过了球拍的高度
						&& ballX > racketX//且在球拍范围之内
						&& ballX < racketX+PACKET_WIDTH)){
					ySpeed = -ySpeed;
				}
				//小球坐标增加,继续运动
				ballX += xSpeed;
				ballY += ySpeed;
				//发送消息,通知系统重绘
				handler.sendEmptyMessage(123);
			}
		}, 0, 50);
	}
	
	//画小球和球拍
	class GameView extends View{
		
		public GameView(Context context) {
			super(context);
			setFocusable(true);
		}
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setAntiAlias(true);
			//如果游戏已经结束
			if(isLose){
				paint.setColor(Color.RED);
				paint.setTextSize(40);
				canvas.drawText("GameOver", 100, 200, paint);
			}else{
				//设置颜色并绘制小球
				paint.setColor(Color.rgb(240, 240, 80));
				canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
				//设置颜色,并绘制球拍
				paint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(racketX, racketY, 
						racketX+PACKET_WIDTH, racketY+PACKET_HEIGHT, paint);
			}
		}
		
		
		
	}
	
	
	
	
	
	
	
	

}
