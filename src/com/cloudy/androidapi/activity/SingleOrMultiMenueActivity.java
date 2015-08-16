package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;

public class SingleOrMultiMenueActivity extends Activity{
	//性别菜单标识
	final int GENDER_MALE = 0x12;
	final int GENDER_FAMALE = 0x13;
	//颜色菜单标识
	final int COLOR_RED = 0x14;
	final int COLOR_GRENN = 0x15;
	final int COLOR_BLUE = 0x16;
	//性别菜单数组
	MenuItem[] genderMenus = new MenuItem[2];
	//颜色菜单项数组
	MenuItem[] menuItems = new MenuItem[3];
	//颜色数组
	String[] colors = {"红色","绿色","蓝色"};
	
	EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
		editText = (EditText) findViewById(R.id.edit_menu);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//仅仅会调用一次
		System.out.println("==============>>>>");
		//添加性别选择子菜单
		SubMenu genderMenu = menu.addSubMenu("性别");
		genderMenu.setHeaderTitle("请选择性别");
		genderMenu.setHeaderIcon(R.drawable.fruit_peach);
		genderMenu.setIcon(R.drawable.fruit_pear);
		
		genderMenus[0] = genderMenu.add(0, GENDER_MALE, 0, "男").setChecked(true);
		genderMenus[1] = genderMenu.add(0, GENDER_FAMALE, 0, "女");
		
		genderMenu.setGroupCheckable(0, true, true);//设置成单选菜单项
		
		//设置多选颜色子菜单
		SubMenu subMenu = menu.addSubMenu("喜欢的颜色");
		subMenu.setIcon(R.drawable.fruit_orange);
		subMenu.setHeaderTitle("请选择你喜欢的颜色");
		subMenu.setHeaderIcon(R.drawable.ic_launcher);
		//添加菜单选择项
		menuItems[0] = subMenu.add(0, COLOR_RED, 0, colors[0]);
		menuItems[1] = subMenu.add(0, COLOR_GRENN, 0, colors[1]);
		menuItems[2] = subMenu.add(0, COLOR_BLUE, 0, colors[2]);
		//设置快捷键
		menuItems[2].setAlphabeticShortcut('b');
		subMenu.setGroupCheckable(0, true, false);//多选
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case GENDER_MALE:
			editText.setText("你的性别为:男");
			//System.out.println("MALE");
			//genderMenus[0].setChecked(true);
			//必须通过代码来改变勾选状态
			item.setCheckable(true);
			break;
		case GENDER_FAMALE:
			editText.setText("你的性别为:女");
			//System.out.println("FEMALE");
			//genderMenus[1].setCheckable(true);
			item.setCheckable(true);
			break;
		case COLOR_RED:
		case COLOR_GRENN:
		case COLOR_BLUE:
			if(item.isChecked()){
				item.setChecked(false);
			}else{
				item.setChecked(true);
			}
			showColor();
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public void showColor(){
		String str = "你喜欢的颜色是:";
		for(int i = 0;i< menuItems.length;i++){
			if(menuItems[i].isChecked()){
				str += colors[i];
			}
		}
		editText.setText(str);
	}

}
