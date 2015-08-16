package com.cloudy.androidapi.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable{
	private String name;
	private int age;
	private String id;
	
	public Person() {
		
	}
	
	public Person(String name,int age,String id){
		this.name = name;
		this.age = age;
		this.id = id;
	}
	
	public Person(Parcel read){
		System.out.println("constructor");
		name = read.readString();
		age = read.readInt();
		id = read.readString();
		
	}
	
	public void print(){
		System.out.println("name="+name+",age="+age+",id="+id);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	//将数据写到序列化里
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeString(name);
		dest.writeInt(age);
		dest.writeString(id);
		System.out.println("Writer");
	}
	
	public static final Parcelable.Creator<Person> CREATOR = new Creator<Person>(){

		@Override
		public Person createFromParcel(Parcel source) {
			System.out.println("creator");
			return new Person(source);
		}

		@Override
		public Person[] newArray(int size) {
			//传一组数据时调用此方法
			return new Person[size];
		}
	};
}
