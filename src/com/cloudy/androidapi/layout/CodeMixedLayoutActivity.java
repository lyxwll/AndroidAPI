package com.cloudy.androidapi.layout;

/*
 * 图片点击浏览
 */
import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CodeMixedLayoutActivity extends Activity{
	
	//定义一个访问图片的数组
	int[] images = new int[]{
		R.drawable.a,
		R.drawable.a2,
		R.drawable.a3,
		R.drawable.a4,
		R.drawable.z1,
		R.drawable.z2,
		R.drawable.z3,
		R.drawable.z4,
		R.drawable.z5,
		R.drawable.z6,
		R.drawable.z7,
		R.drawable.z8,
	};
	
	int currentImg = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.code_mixed);
		//获取linearlayout的布局容器
		LinearLayout linearLayout = (LinearLayout)findViewById(R.id.mixcode_layout);
		//创建ImageView组件
		final ImageView imageView = new ImageView(this);
		//将ImageView组件添加到layout布局中
		linearLayout.addView(imageView);
		//初始化时显示第一张图片
		imageView.setImageResource(images[0]);
		
		//设置点击事件,使用的是匿名内部类
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(currentImg >= 11){
					currentImg =-1;
				}
				//改变ImageView里显示的图片
				imageView.setImageResource(images[++currentImg]);
			}
		});
		
	}
			
}
