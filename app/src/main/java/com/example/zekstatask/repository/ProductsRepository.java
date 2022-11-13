package com.example.zekstatask.repository;

import android.app.Application;


import androidx.lifecycle.MutableLiveData;

import com.example.zekstatask.models.Brand;
import com.example.zekstatask.models.Product;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ProductsRepository {
    private Application application;

    public ProductsRepository(Application application) {
        this.application = application;
    }

    private MutableLiveData<ArrayList<Product>> productMutableLiveData = new MutableLiveData<ArrayList<Product>>();

    //Product List
    public MutableLiveData<ArrayList<Product>> getProductList() {
        try {
            String json = null;
            try {
                InputStream is = application.getAssets().open("Products.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
            Product product = null;
            ArrayList<Product> arrayList = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                product = new Product();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                product.setProductName(jsonObject.getString("productName"));
                product.setID(jsonObject.getString(("_id")));
                product.setPrice(jsonObject.getString(("price")));
                product.setPicture(jsonObject.getString(("picture")));
                ArrayList<String> colorArr = new ArrayList<>();
                for (int j = 0; j < jsonObject.getJSONArray(("colors")).length(); j++) {
                    String color = jsonObject.getJSONArray(("colors")).getString(j);
                    colorArr.add(color);
                }
                ArrayList<Brand> brandArr = new ArrayList<>();
                for (int j = 0; j < jsonObject.getJSONArray(("brands")).length(); j++) {
                    JSONObject brandJsonObj = jsonObject.getJSONArray(("brands")).getJSONObject(j);
                    String ID = brandJsonObj.getString("id");
                    String name = brandJsonObj.getString("name");
                    brandArr.add(new Brand(ID, name,"Select Brand"));
                }
                product.setBrand(brandArr);
                product.setColors(colorArr);
                arrayList.add(product);
            }
            productMutableLiveData.setValue(arrayList);

        } catch (Exception e) {
            productMutableLiveData.setValue(null);
            e.printStackTrace();
        }
        return productMutableLiveData;
    }
}
