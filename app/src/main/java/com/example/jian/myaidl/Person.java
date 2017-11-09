package com.example.jian.myaidl;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by 29560 on 2017/11/6.
 */

public class Person implements Parcelable {

    private String mName;
    private String mTelNumber;
    private int mAge;

    public  Person() {
        // TODO Auto-generated constructor stub
    }

    public Person(Parcel pl) {
        mName=pl.readString();
        mTelNumber=pl.readString();
        mAge=pl.readInt();
    }



    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getTelNumber() {
        return mTelNumber;
    }

    public void setTelNumber(String telNumber) {
        this.mTelNumber = telNumber;
    }

    public int getAge() {
        return mAge;
    }

    public void setAge(int age) {
        this.mAge = age;
    }

    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        // TODO Auto-generated method stub
        dest.writeString(mName);
        dest.writeString(mTelNumber);
        dest.writeInt(mAge);
    }

    public static final Parcelable.Creator<Person> CREATOR=new Parcelable.Creator<Person>() {

        @Override
        public Person[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Person[size];
        }

        @Override
        public Person createFromParcel(Parcel source) {
            // TODO Auto-generated method stub
            return new Person(source);
        }
    };

}

