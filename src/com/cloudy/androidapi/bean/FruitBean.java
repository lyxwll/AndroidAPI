package com.cloudy.androidapi.bean;
/*
 * fruitbeanʵ�����л�
 */
import java.io.Serializable;

public class FruitBean implements Serializable{

	/*
	 * ���ڲ�ȫ��ʾ��Ϣǰ��СͼƬ����
	 */
	private static final long serialVersionUID = 1L;
	//������Ӧ�ı�ǩtitle,����������icon
	public String title;
	public int iconId;
	
	//��дObject���toString()����,�����д�򷵻ص���16���Ƶ�hashcode
	@Override
	public String toString() {
		
		return title;
		
	}
	
}
