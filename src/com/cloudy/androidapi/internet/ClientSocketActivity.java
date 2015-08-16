package com.cloudy.androidapi.internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.cloudy.androidapi.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ClientSocketActivity extends FragmentActivity{
	private static final int NEW_MESSAGE = 1234;
	private EditText out;
	private EditText input;
	private Button send;
	private OutputStream outputStream;
	
	//接收消息,并显示
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case NEW_MESSAGE:
				//将读取到的消息追加显示到文本框
				System.out.println("msg.obj=" + msg.obj);
				out.append("\n" + msg.obj.toString() + "\n");
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.client_chat_layout);
		
		out = (EditText) findViewById(R.id.out);
		input = (EditText) findViewById(R.id.input_chat);
		send = (Button) findViewById(R.id.send_chat);
		
		Socket socket;
		try {
			socket = new Socket("192.168.1.117",6969);
			new Thread(new ClientThread(socket, handler)).start();
			outputStream = socket.getOutputStream();
			//android4.0以上使用此方法
			/*Thread thread = new Thread(){
				@Override
				public void run() {
					socket = new Socket("192.168.1.117",6969);
					new Thread(new ClientThread(socket, handler)).start();
					outputStream = socket.getOutputStream();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};*/
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//启动客户端ClientThread线程来自服务器的数据
		send.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					outputStream.write((input.getText().toString() + "\n").getBytes("utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				//清空input输入框
				input.setText("");
			}
		});
	}
	
	public class ClientThread implements Runnable{
		//该线程负责处理的socket
		private Socket socket;
		private Handler handler;
		private BufferedReader bufferedReader = null;//输入流
		
		public ClientThread(Socket socket,Handler handler) {
			this.socket = socket;
			this.handler = handler;
			try {
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void run() {
			String content = null;
			try {
				while((content = bufferedReader.readLine()) != null){
					Message message = new Message();
					message.what = NEW_MESSAGE;//用户定义的消息代码，以便收件人能识别什么该消息是什么
					message.obj = content;//发送给收件人的任意对象
					handler.sendMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
		

}
