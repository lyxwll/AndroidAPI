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
	//����Ŀ�ȸ߶�
	private int tableWidth;
	private int tableHeight;
	//���ĵĴ�ֱλ��
	private int racketY;
	//�������ĵĿ��,�߶�
	private int PACKET_WIDTH = 50;
	private int PACKET_HEIGHT = 100;
	//��Ĵ�С
	private final int BALL_SIZE = 25;
	
	//������������ٶ�
	private int ySpeed = 20;
	Random random = new Random();
	//����һ��-0.5--0.5�ı���.���ڿ���С������з���
	private double xyRate = random.nextDouble()-0.5;
	//С����������ٶ�
	private int xSpeed = (int) (ySpeed*xyRate*2);
	//ballX,ballY����С�������  ��ʼ����
	private int ballX = random.nextInt(200)+20;
	private int ballY = random.nextInt(10)+20;
	//racketX �������ĵ�ˮƽλ��
	private int racketX = random.nextInt(200);
	//��Ϸ�Ƿ����
	private boolean isLose = false;
	
	//���򻬶�
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
		//����pinpangView �ؼ�
		final GameView gameView = new GameView(this);
		setContentView(gameView);//
		//��ȡ���ڹ�����
		WindowManager windowManager = getWindowManager();
		//��ȡ��Ļ�Ŀ��
		tableWidth = windowManager.getDefaultDisplay().getWidth();
		tableHeight = windowManager.getDefaultDisplay().getHeight();
		PACKET_WIDTH = tableWidth/2;
		racketX = 0;
		racketY = tableHeight - 80;//���ĵ�Y����
		
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
		//Touch�¼��������ĵ�λ��,���һ���
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
					if((endX - startX) > 10 && racketX < tableWidth-BALL_SIZE){//�һ�,������δ�����ұ߽߱�
						racketX += 15;
						startX = endX;
						startY = endY;
					}else if((startX - endX) > 10 && racketX > 0){//��,������δ������߽߱�
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
				//���С�� ������߽߱���ұ߽߱�
				if(ballX <= 0||ballX >= tableWidth-BALL_SIZE){//��ײ��Ե����,�ٶȷ���䷴
					xSpeed = -xSpeed;//�ص�,�ı��ٶȷ���
				}
				//���С��ĸ߶ȳ��������ĵ�λ��,�Һ��������ķ�Χ֮��,��Ϸ����
				if(ballY >= racketY-BALL_SIZE &&(ballX < racketX||ballX > racketX+ PACKET_WIDTH)){
					timer.cancel();
					//������Ϸ������־Ϊtrue
					isLose = true;
				}
				//���С��λ������֮��,�Ҵﵽ����λ��,�������ĵ���С��,��С�򷴵���ȥ
				else if(ballY <= 0 //С��ײ�����ϱߵı߿�
						||(ballY >= racketY -BALL_SIZE//С��ĸ߶ȳ��������ĵĸ߶�
						&& ballX > racketX//�������ķ�Χ֮��
						&& ballX < racketX+PACKET_WIDTH)){
					ySpeed = -ySpeed;
				}
				//С����������,�����˶�
				ballX += xSpeed;
				ballY += ySpeed;
				//������Ϣ,֪ͨϵͳ�ػ�
				handler.sendEmptyMessage(123);
			}
		}, 0, 50);
	}
	
	//��С�������
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
			//�����Ϸ�Ѿ�����
			if(isLose){
				paint.setColor(Color.RED);
				paint.setTextSize(40);
				canvas.drawText("GameOver", 100, 200, paint);
			}else{
				//������ɫ������С��
				paint.setColor(Color.rgb(240, 240, 80));
				canvas.drawCircle(ballX, ballY, BALL_SIZE, paint);
				//������ɫ,����������
				paint.setColor(Color.rgb(80, 80, 200));
				canvas.drawRect(racketX, racketY, 
						racketX+PACKET_WIDTH, racketY+PACKET_HEIGHT, paint);
			}
		}
		
		
		
	}
	
	
	
	
	
	
	
	

}
