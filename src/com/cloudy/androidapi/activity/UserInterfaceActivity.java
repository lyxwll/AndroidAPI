package com.cloudy.androidapi.activity;

/*
 * 用户登录界面的实例
 */

import java.net.ContentHandler;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserInterfaceActivity extends Activity {
	// 定义了三个编辑框属性:Username,Password,RepeatPassword
	private EditText mUsername;
	private EditText mPassword;
	private EditText mRepeatPwd;
	// 定义了两个按钮属性Login,Cancel
	private Button mLogin;
	private Button mCancel;
	// 定义了一个错误信息的属性
	private String errorMsg;
	// 未通过信息的位置
	// 0:表示musername,1:mpassword 2:mrepeatpwd 3:密码不一样
	private int location = -1;
	// 弹出输入法
	InputMethodManager inputMethod;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 获得布局视图
		setContentView(R.layout.userlogin_layout);
		// 通过SystemService获取到输入法管理器实例
		inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		// 初始化视图:调用了初始化视图的方法
		intiView();

	}

	// 初始化视图的方法
	private void intiView() {
		// 通过Id找到View视图
		mUsername = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mRepeatPwd = (EditText) findViewById(R.id.repeat_pwd);
		mLogin = (Button) findViewById(R.id.login_btn);
		mCancel = (Button) findViewById(R.id.cancel_btn);

		// 设置Login_btn和Cancel_btn的点击监听事件:
		// 使用的事自定义的ButtonListener()类方法
		mLogin.setOnClickListener(new ButtonListener());
		mCancel.setOnClickListener(new ButtonListener());

		setFirstFocus();
	}

	// 设置最先聚焦的位置
	public void setFirstFocus() {
		mUsername.requestFocus();
	}

	// 设置按钮点击监听事件的方法
	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// 通过View获得Id
			switch (v.getId()) {
			// 如果点击了Login_btn
			case R.id.login_btn:
				// 当检查检查信息正确时,调用login()方法登录
				if (checkLoginInfo()) {
					login();
				} else {
					errorFocus();
					/*
					 * Toast.makeText();当视图显示给用户，作为一个浮动的观点出现在应用程序
					 * Toast.show();在指定的时间显示视图
					 * Toast.LENGTH_SHORT:在很短的时间内显示相关的视图或文本通知
					 */
					Toast.makeText(UserInterfaceActivity.this, errorMsg,
							Toast.LENGTH_SHORT).show();
				}
				break;
			// 如果点击了取消按钮
			case R.id.cancel_btn:
				// Activity.finish();关闭当前活动,返回得到这个活动的界面
				UserInterfaceActivity.this.finish();
				break;
			}
		}

		// 信息未通过并聚焦行方法
		public void errorFocus() {
			switch (location) {
			case 0:
				mUsername.setFocusable(true);
				mUsername.setFocusableInTouchMode(true);
				mUsername.requestFocus();
				inputMethod.toggleSoftInput(InputMethodManager.SHOW_FORCED,
						InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			case 1:
				mPassword.setFocusable(true);
				mPassword.setFocusableInTouchMode(true);
				mPassword.requestFocus();
				inputMethod.toggleSoftInput(InputMethodManager.SHOW_FORCED,
						InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			case 2:
			case 3:
				mRepeatPwd.setFocusable(true);
				mRepeatPwd.setFocusableInTouchMode(true);
				mRepeatPwd.requestFocus();
				inputMethod.toggleSoftInput(InputMethodManager.SHOW_FORCED,
						InputMethodManager.HIDE_NOT_ALWAYS);
				break;
			}
		}

		// 登录信息检查的方法;
		private boolean checkLoginInfo() {
			// 定义成功登录的属性
			boolean isSuccess = true;
			if (mUsername.getText().toString() == null
					|| mUsername.getText().toString().length() < 6) {
				// 如果用户名为空或者少于6个字符,则:登录不成功
				isSuccess = false;
				location = 0;
				// 显示的错误信息为:用户名为空或者少于6个字符
				// X.Resources.getString(int id):返回与一个特定的资源有关联的Id的字符串形式
				errorMsg = getResources().getString(R.string.empty_name);
			} else if (mPassword.getText().toString() == null
					|| mPassword.getText().toString().length() < 8) {
				// 如果密码为空或者少于8个字符,则:登录不成功
				isSuccess = false;
				location = 1;
				// 显示的错误信息为:密码为空或者少于8个字符
				errorMsg = getResources().getString(R.string.empty_pwd);
			} else if (mRepeatPwd.getText().toString() == null
					|| mRepeatPwd.getText().toString().length() < 8) {
				// 如果重复密码为空或者少于8个字符,则:登录不成功
				isSuccess = false;
				location = 2;
				// 显示的错误信息为:密码为空或者少于8个字符
				errorMsg = getResources().getString(R.string.empty_pwd);
			} else if (!mPassword.getText().toString()
					.equals(mRepeatPwd.getText().toString())) {
				// 如果密码与重复密码不一样,则:登录不成功
				isSuccess = false;
				location = 3;
				// 显示的错误信息为:密码与重复密码不一样
				errorMsg = getResources().getString(R.string.pwd_not_same);
			}

			return isSuccess;
		}

		// 登录信息显示的方法
		private void login() {
			//
			String userName = mUsername.getText().toString();
			String password = mPassword.getText().toString();
			errorMsg = getResources().getString(R.string.login_msg) + ",name="
					+ userName + ",password=" + password;
			// 在很短的时间里显示一个相关的视图或文本通知
			Toast.makeText(UserInterfaceActivity.this, errorMsg,
					Toast.LENGTH_SHORT).show();

		}

	}

}
