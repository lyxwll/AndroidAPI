package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.EditText;
import android.widget.Toast;

public class MenuListenerActivity extends Activity {

	// 定义字体大小
	final int FONT_10 = 0x12;
	final int FONT_12 = 0x13;
	final int FONT_14 = 0x14;
	final int FONT_16 = 0x15;
	final int FONT_18 = 0x16;

	// 普通菜单项
	final int PLAIN_ITLEM = 0x17;

	// 定义颜色的菜单标志
	final int FONT_RED = 0x18;
	final int FONT_GRENN = 0x19;
	final int FONT_BLUE = 0x1A;

	EditText editText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);

		editText = (EditText) findViewById(R.id.edit_menu);

	}

	/**
	 * 创建菜单方法
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// 生成fontsize子菜单
		SubMenu fontMenu = menu.addSubMenu(R.string.font_size);
		fontMenu.setHeaderTitle(R.string.font_size_title);
		fontMenu.setHeaderIcon(R.drawable.fruit_apple);
		// 添加菜单选项
		MenuItem font_10 = fontMenu.add(0, FONT_10, 0, R.string.font_size_10);
		MenuItem font_12 = fontMenu.add(0, FONT_12, 0, R.string.font_size_12);
		MenuItem font_14 = fontMenu.add(0, FONT_14, 0, R.string.font_size_14);
		MenuItem font_16 = fontMenu.add(0, FONT_16, 0, R.string.font_size_16);
		MenuItem font_18 = fontMenu.add(0, FONT_18, 0, R.string.font_size_18);

		// 添加一个普通菜单
		menu.add(1, PLAIN_ITLEM, 0, R.string.normal_menu).setIcon(
				R.drawable.fruit_pear);

		// 添加颜色选择菜单
		SubMenu colorMenu = menu.addSubMenu(R.string.color_menu);
		colorMenu.setHeaderTitle(R.string.color_menu_title);
		colorMenu.setHeaderIcon(R.drawable.fruit_little_apple);

		// 添加颜色子菜单里的选择项
		colorMenu.add(2, FONT_RED, 0, R.string.color_red);
		colorMenu.add(2, FONT_GRENN, 0, R.string.color_grenn);
		colorMenu.add(2, FONT_BLUE, 0, R.string.color_blue);

		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 选中菜单事件监听 的方法
	 */
	class MyMenuItemClickLisener implements OnMenuItemClickListener {

		@Override
		public boolean onMenuItemClick(MenuItem item) {
			switch (item.getItemId()) {
			case FONT_10:
				editText.setTextSize(10*2);
				break;
			case FONT_12:
				editText.setTextSize(12*2);
				break;
			case FONT_14:
				editText.setTextSize(14*2);
				break;
			case FONT_16:
				editText.setTextSize(16*2);
				break;
			case FONT_18:
				editText.setTextSize(18*2);
				break;
			case PLAIN_ITLEM:
				//Toast.makeText(this, "normal_menu", Toast.LENGTH_SHORT).show();
				break;
			case FONT_RED:
				editText.setTextColor(Color.RED);
				break;
			case FONT_GRENN:
				editText.setTextColor(Color.GREEN);
				break;
			case FONT_BLUE:
				editText.setTextColor(Color.BLUE);
				break;
			}
			return false;
		}

	}
}
