package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EditTextActivity extends Activity{

	@SuppressWarnings("unused")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edittext_layout);
		
		final EditText editText = (EditText) findViewById(R.id.input1);
		Button button = (Button) findViewById(R.id.get_msg);
		
		editText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//Editable editable = editText.getText();
				//editable.replace(0, editable.length()-2, "helloword");
				//editable.append("xxxxxxx");
				System.out.println("输入的内容为: "+editText.getText().toString());
				
			}
		});
		
		/*
		 * 给输入框添加一个输入监听:可以屏蔽非法字符的方法
		 * TextWatcher();就是用来监听字符的改变的
		 */
		editText.addTextChangedListener(new TextWatcher() {
			//在获取到输入,字符改变时回调此方法
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println("OnTextChanged");
				System.out.println("CharSequence s="+s.toString());
				System.out.println(start+", "+before+", "+count);
				
				Editable editable = editText.getText();
				//拿到改变的子串
				CharSequence sequence = s.subSequence(start, start+count);
				System.out.println("sequence="+sequence);
				//将拿到的子串替换掉
				if(sequence.toString().contains("我去")){
					String str = sequence.toString().replace("我去", "@%#*$");
					editable.replace(start, start+count, str);
				}
				
			}
			/*最先回调此方法
			 * 在获取到输入,但字符还未改变之前
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				System.out.println("beforeTextChanged");
				System.out.println("CharSequence s="+s.toString());
				System.out.println(start+", "+count+", "+after);
				
			}
			
			//当改变发生之后,回调此方法
			@Override
			public void afterTextChanged(Editable s) {
				System.out.println("afterTextChanged s= "+s);
				
			}
		});
		
	}

}
