package com.cloudy.androidapi.intent;

import java.util.Iterator;
import java.util.Set;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.content.ComponentName;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.Toast;

public class ReceiveCategoryA extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EditText editText = new EditText(this);
		editText.setBackgroundResource(R.drawable.edit_text_bg);
		editText.setGravity(Gravity.TOP|Gravity.LEFT);
		editText.setMinLines(10);
		setContentView(editText);
		
		if(getIntent() != null){
			ComponentName componentName = getIntent().getComponent();
			Set<String> categories = getIntent().getCategories();
			Iterator<String> iterator = categories.iterator();
			String category = "";
			while(iterator.hasNext()){
				category += iterator.next()+";";
			}
			Toast.makeText(this, "A œÏ”¶Intent("+componentName.toShortString()+")+category="
					+category , Toast.LENGTH_LONG).show();
			editText.setText("A œÏ”¶Intent( "+ componentName.toShortString() + ") + category=" + category);
		}
	}

}














