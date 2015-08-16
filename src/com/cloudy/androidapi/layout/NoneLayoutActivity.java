package com.cloudy.androidapi.layout;

import com.cloudy.androidapi.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NoneLayoutActivity extends Activity {

	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout linerlayout = new LinearLayout(this);
		linerlayout.setOrientation(linerlayout.VERTICAL);
		TextView textView = new TextView(this);
		textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
									LayoutParams.WRAP_CONTENT));
		textView.setText(R.string.none_layout);

		//��Ӱ�ť
		Button button =new Button(this);
		button.setText(R.string.my_button);
		
		// ��Textview��ӵ�LinearLayout����
		linerlayout.addView(textView);
		linerlayout.addView(button);
		setContentView(linerlayout);
	}

}
