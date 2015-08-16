package com.cloudy.androidapi.bean;
/*
 * fruitbean实现序列化
 */
import java.io.Serializable;

public class FruitBean implements Serializable{

	/*
	 * 用于补全提示信息前的小图片集合
	 */
	private static final long serialVersionUID = 1L;
	//给出相应的标签title,并附带它的icon
	public String title;
	public int iconId;
	
	//重写Object类的toString()方法,如果不写则返回的是16进制的hashcode
	@Override
	public String toString() {
		
		return title;
		
	}
	
}
