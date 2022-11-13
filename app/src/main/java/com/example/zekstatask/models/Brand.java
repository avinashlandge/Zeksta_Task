package com.example.zekstatask.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

public class Brand extends BaseObservable implements Parcelable {
    @SerializedName("id")
    private String ID;
    @SerializedName("name")
    private String Name;
    @SerializedName("isSelected")
    private String isSelected = "Select Brand";

    public Brand(String ID, String name, String isSelected) {
        this.ID = ID;
        Name = name;
        this.isSelected = isSelected;
    }

    protected Brand(Parcel in) {
        ID = in.readString();
        Name = in.readString();
        isSelected = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Name);
        dest.writeString(isSelected);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Brand> CREATOR = new Creator<Brand>() {
        @Override
        public Brand createFromParcel(Parcel in) {
            return new Brand(in);
        }

        @Override
        public Brand[] newArray(int size) {
            return new Brand[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(String isSelected) {
        this.isSelected = isSelected;
    }
}
