package com.cloudy.androidapi.activity;

import com.cloudy.androidapi.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.EditText;

public class SingleOrMultiMenueActivity extends Activity{
	//�Ա�˵���ʶ
	final int GENDER_MALE = 0x12;
	final int GENDER_FAMALE = 0x13;
	//��ɫ�˵���ʶ
	final int COLOR_RED = 0x14;
	final int COLOR_GRENN = 0x15;
	final int COLOR_BLUE = 0x16;
	//�Ա�˵�����
	MenuItem[] genderMenus = new MenuItem[2];
	//��ɫ�˵�������
	MenuItem[] menuItems = new MenuItem[3];
	//��ɫ����
	String[] colors = {"��ɫ","��ɫ","��ɫ"};
	
	EditText editText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_layout);
		
		editText = (EditText) findViewById(R.id.edit_menu);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//���������һ��
		System.out.println("==============>>>>");
		//����Ա�ѡ���Ӳ˵�
		SubMenu genderMenu = menu.addSubMenu("�Ա�");
		genderMenu.setHeaderTitle("��ѡ���Ա�");
		genderMenu.setHeaderIcon(R.drawable.fruit_peach);
		genderMenu.setIcon(R.drawable.fruit_pear);
		
		genderMenus[0] = genderMenu.add(0, GENDER_MALE, 0, "��").setChecked(true);
		genderMenus[1] = genderMenu.add(0, GENDER_FAMALE, 0, "Ů");
		
		genderMenu.setGroupCheckable(0, true, true);//���óɵ�ѡ�˵���
		
		//���ö�ѡ��ɫ�Ӳ˵�
		SubMenu subMenu = menu.addSubMenu("ϲ������ɫ");
		subMenu.setIcon(R.drawable.fruit_orange);
		subMenu.setHeaderTitle("��ѡ����ϲ������ɫ");
		subMenu.setHeaderIcon(R.drawable.ic_launcher);
		//��Ӳ˵�ѡ����
		menuItems[0] = subMenu.add(0, COLOR_RED, 0, colors[0]);
		menuItems[1] = subMenu.add(0, COLOR_GRENN, 0, colors[1]);
		menuItems[2] = subMenu.add(0, COLOR_BLUE, 0, colors[2]);
		//���ÿ�ݼ�
		menuItems[2].setAlphabeticShortcut('b');
		subMenu.setGroupCheckable(0, true, false);//��ѡ
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case GENDER_MALE:
			editText.setText("����Ա�Ϊ:��");
			//System.out.println("MALE");
			//genderMenus[0].setChecked(true);
			//����ͨ���������ı乴ѡ״̬
			item.setCheckable(true);
			break;
		case GENDER_FAMALE:
			editText.setText("����Ա�Ϊ:Ů");
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
		String str = "��ϲ������ɫ��:";
		for(int i = 0;i< menuItems.length;i++){
			if(menuItems[i].isChecked()){
				str += colors[i];
			}
		}
		editText.setText(str);
	}

}
