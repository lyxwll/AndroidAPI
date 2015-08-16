package com.cloudy.androidapi.activity;

/*
 * �û���¼�����ʵ��
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
	// �����������༭������:Username,Password,RepeatPassword
	private EditText mUsername;
	private EditText mPassword;
	private EditText mRepeatPwd;
	// ������������ť����Login,Cancel
	private Button mLogin;
	private Button mCancel;
	// ������һ��������Ϣ������
	private String errorMsg;
	// δͨ����Ϣ��λ��
	// 0:��ʾmusername,1:mpassword 2:mrepeatpwd 3:���벻һ��
	private int location = -1;
	// �������뷨
	InputMethodManager inputMethod;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ��ò�����ͼ
		setContentView(R.layout.userlogin_layout);
		// ͨ��SystemService��ȡ�����뷨������ʵ��
		inputMethod = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		// ��ʼ����ͼ:�����˳�ʼ����ͼ�ķ���
		intiView();

	}

	// ��ʼ����ͼ�ķ���
	private void intiView() {
		// ͨ��Id�ҵ�View��ͼ
		mUsername = (EditText) findViewById(R.id.username);
		mPassword = (EditText) findViewById(R.id.password);
		mRepeatPwd = (EditText) findViewById(R.id.repeat_pwd);
		mLogin = (Button) findViewById(R.id.login_btn);
		mCancel = (Button) findViewById(R.id.cancel_btn);

		// ����Login_btn��Cancel_btn�ĵ�������¼�:
		// ʹ�õ����Զ����ButtonListener()�෽��
		mLogin.setOnClickListener(new ButtonListener());
		mCancel.setOnClickListener(new ButtonListener());

		setFirstFocus();
	}

	// �������Ⱦ۽���λ��
	public void setFirstFocus() {
		mUsername.requestFocus();
	}

	// ���ð�ť��������¼��ķ���
	class ButtonListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// ͨ��View���Id
			switch (v.getId()) {
			// ��������Login_btn
			case R.id.login_btn:
				// ���������Ϣ��ȷʱ,����login()������¼
				if (checkLoginInfo()) {
					login();
				} else {
					errorFocus();
					/*
					 * Toast.makeText();����ͼ��ʾ���û�����Ϊһ�������Ĺ۵������Ӧ�ó���
					 * Toast.show();��ָ����ʱ����ʾ��ͼ
					 * Toast.LENGTH_SHORT:�ں̵ܶ�ʱ������ʾ��ص���ͼ���ı�֪ͨ
					 */
					Toast.makeText(UserInterfaceActivity.this, errorMsg,
							Toast.LENGTH_SHORT).show();
				}
				break;
			// ��������ȡ����ť
			case R.id.cancel_btn:
				// Activity.finish();�رյ�ǰ�,���صõ������Ľ���
				UserInterfaceActivity.this.finish();
				break;
			}
		}

		// ��Ϣδͨ�����۽��з���
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

		// ��¼��Ϣ���ķ���;
		private boolean checkLoginInfo() {
			// ����ɹ���¼������
			boolean isSuccess = true;
			if (mUsername.getText().toString() == null
					|| mUsername.getText().toString().length() < 6) {
				// ����û���Ϊ�ջ�������6���ַ�,��:��¼���ɹ�
				isSuccess = false;
				location = 0;
				// ��ʾ�Ĵ�����ϢΪ:�û���Ϊ�ջ�������6���ַ�
				// X.Resources.getString(int id):������һ���ض�����Դ�й�����Id���ַ�����ʽ
				errorMsg = getResources().getString(R.string.empty_name);
			} else if (mPassword.getText().toString() == null
					|| mPassword.getText().toString().length() < 8) {
				// �������Ϊ�ջ�������8���ַ�,��:��¼���ɹ�
				isSuccess = false;
				location = 1;
				// ��ʾ�Ĵ�����ϢΪ:����Ϊ�ջ�������8���ַ�
				errorMsg = getResources().getString(R.string.empty_pwd);
			} else if (mRepeatPwd.getText().toString() == null
					|| mRepeatPwd.getText().toString().length() < 8) {
				// ����ظ�����Ϊ�ջ�������8���ַ�,��:��¼���ɹ�
				isSuccess = false;
				location = 2;
				// ��ʾ�Ĵ�����ϢΪ:����Ϊ�ջ�������8���ַ�
				errorMsg = getResources().getString(R.string.empty_pwd);
			} else if (!mPassword.getText().toString()
					.equals(mRepeatPwd.getText().toString())) {
				// ����������ظ����벻һ��,��:��¼���ɹ�
				isSuccess = false;
				location = 3;
				// ��ʾ�Ĵ�����ϢΪ:�������ظ����벻һ��
				errorMsg = getResources().getString(R.string.pwd_not_same);
			}

			return isSuccess;
		}

		// ��¼��Ϣ��ʾ�ķ���
		private void login() {
			//
			String userName = mUsername.getText().toString();
			String password = mPassword.getText().toString();
			errorMsg = getResources().getString(R.string.login_msg) + ",name="
					+ userName + ",password=" + password;
			// �ں̵ܶ�ʱ������ʾһ����ص���ͼ���ı�֪ͨ
			Toast.makeText(UserInterfaceActivity.this, errorMsg,
					Toast.LENGTH_SHORT).show();

		}

	}

}
