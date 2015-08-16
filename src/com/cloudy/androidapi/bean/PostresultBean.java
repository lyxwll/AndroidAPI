package com.cloudy.androidapi.bean;

import java.io.Serializable;

public class PostresultBean implements Serializable{
	
	public static final long serialVersionUID = 1L;
	
	public int error;
	public String result;
	public String operation;
	
	@Override
	public String toString() {
		return "PostResultBean [error=" + error +",result=" + result + ",operation=" + operation + "]";
	}

}
