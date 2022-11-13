package com.example.zekstatask.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.zekstatask.models.Product;
import com.example.zekstatask.repository.ProductsRepository;

import java.util.ArrayList;

public class ProductViewModel extends AndroidViewModel {
    private ProductsRepository productsRepository;
    public ProductViewModel(@NonNull Application application) {
        super(application);
        productsRepository = new ProductsRepository(application);
    }

    public LiveData<ArrayList<Product>> getProductArrayList(){
        return productsRepository.getProductList();
    }
}
