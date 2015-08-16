package com.cloudy.androidapi.xml;

import android.os.Parcel;
import android.os.Parcelable;

public class PersonBean implements Parcelable{
	
	public int id;
	public String name;
	public int age;
	
	public PersonBean() {

	}
	
	public PersonBean(int id,String name,int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public PersonBean(Parcel in) {
		this.id = in.readInt();
		this.name = in.readString();
		this.age = in.readInt();
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(id);
		dest.writeString(name);
		dest.writeInt(age);
		
	}
	
	public static final Parcelable.Creator<PersonBean> CREATOR = new Creator<PersonBean>() {
		
		@Override
		public PersonBean[] newArray(int size) {
			return new PersonBean[size];
		}
		
		@Override
		public PersonBean createFromParcel(Parcel source) {
			return new PersonBean();
		}
	};
	

}
