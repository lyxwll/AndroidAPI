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
				System.out.println("���������Ϊ: "+editText.getText().toString());
				
			}
		});
		
		/*
		 * ����������һ���������:�������ηǷ��ַ��ķ���
		 * TextWatcher();�������������ַ��ĸı��
		 */
		editText.addTextChangedListener(new TextWatcher() {
			//�ڻ�ȡ������,�ַ��ı�ʱ�ص��˷���
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				System.out.println("OnTextChanged");
				System.out.println("CharSequence s="+s.toString());
				System.out.println(start+", "+before+", "+count);
				
				Editable editable = editText.getText();
				//�õ��ı���Ӵ�
				CharSequence sequence = s.subSequence(start, start+count);
				System.out.println("sequence="+sequence);
				//���õ����Ӵ��滻��
				if(sequence.toString().contains("��ȥ")){
					String str = sequence.toString().replace("��ȥ", "@%#*$");
					editable.replace(start, start+count, str);
				}
				
			}
			/*���Ȼص��˷���
			 * �ڻ�ȡ������,���ַ���δ�ı�֮ǰ
			 */
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				System.out.println("beforeTextChanged");
				System.out.println("CharSequence s="+s.toString());
				System.out.println(start+", "+count+", "+after);
				
			}
			
			//���ı䷢��֮��,�ص��˷���
			@Override
			public void afterTextChanged(Editable s) {
				System.out.println("afterTextChanged s= "+s);
				
			}
		});
		
	}

}
