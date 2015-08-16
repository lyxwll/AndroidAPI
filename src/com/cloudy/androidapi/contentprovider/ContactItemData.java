package com.cloudy.androidapi.contentprovider;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactItemData implements Parcelable{

	String name;
	String number;
	boolean check;
	
	public ContactItemData() {
		
	}
	
	public ContactItemData(String name,String number,boolean check){
		this.name = name;
		this.number = number;
		this.check = check;
	}
	
	public ContactItemData(Parcel in){
		this.name = in.readString();
		this.number = in.readString();
		int isCheck = in.readInt();
		if(isCheck == 1){
			this.check = true;
		}else{
			this.check = false;
		}
	}
	
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(number);
		if(check == true){
			dest.writeInt(1);
		}else{
			dest.writeInt(0);
		}
		
	}
	
	public static final Creator<ContactItemData> creator = new Creator<ContactItemData>() {

		@Override
		public ContactItemData[] newArray(int size) {
			return new ContactItemData[size];
		}
		
		@Override
		public ContactItemData createFromParcel(Parcel source) {
			return new ContactItemData(source);
		}

	};
	

}
