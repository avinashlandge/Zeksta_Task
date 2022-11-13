package com.example.zekstatask.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Product extends BaseObservable implements Parcelable {

    @SerializedName("_id")
    private String ID;
    @SerializedName("picture")
    private String Picture;
    @SerializedName("price")
    private String Price;
    @SerializedName("Qty")
    private String Qty="";
    @SerializedName("productName")
    private String ProductName;
    @SerializedName("selectedColor")
    private String selectedColor = "";
    @SerializedName("colors")
    private ArrayList<String> Colors;
    @SerializedName("brands")
    private ArrayList<Brand> Brand;

    public Product() {
    }

    protected Product(Parcel in) {
        ID = in.readString();
        Picture = in.readString();
        Price = in.readString();
        Qty = in.readString();
        ProductName = in.readString();
        selectedColor = in.readString();
        Colors = in.createStringArrayList();
        Brand = in.createTypedArrayList(com.example.zekstatask.models.Brand.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(Picture);
        dest.writeString(Price);
        dest.writeString(Qty);
        dest.writeString(ProductName);
        dest.writeString(selectedColor);
        dest.writeStringList(Colors);
        dest.writeTypedList(Brand);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getQty() {
        return Qty;
    }

    public void setQty(String qty) {
        Qty = qty;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(String selectedColor) {
        this.selectedColor = selectedColor;
    }

    public ArrayList<String> getColors() {
        return Colors;
    }

    public void setColors(ArrayList<String> colors) {
        Colors = colors;
    }

    public ArrayList<com.example.zekstatask.models.Brand> getBrand() {
        return Brand;
    }

    public void setBrand(ArrayList<com.example.zekstatask.models.Brand> brand) {
        Brand = brand;
    }
}
