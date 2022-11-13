package com.example.zekstatask.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.zekstatask.adapters.ProductListAdapter;
import com.example.zekstatask.databinding.ActivityMainBinding;
import com.example.zekstatask.models.Product;
import com.example.zekstatask.viewmodels.ProductViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private ActivityMainBinding binding;
    public  ArrayList<Product> productList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValid()) {
                    Intent i = new Intent(MainActivity.this,SummeryActivity.class);
                    i.putExtra("data",productList);
                    startActivity(i);
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        productList.clear();
        setAdapter();
    }

    private void setAdapter(){
        productViewModel.getProductArrayList().observe(this, productArrayList -> {
            System.out.println("myJson : " + new Gson().toJson(productArrayList));
            if (productArrayList != null && !productArrayList.isEmpty()) {

                productList = productArrayList;

                binding.txtNoData.setVisibility(View.GONE);
                binding.rvProductList.setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                binding.rvProductList.setLayoutManager(linearLayoutManager);

                ProductListAdapter adapter = new ProductListAdapter(productArrayList, MainActivity.this);
                binding.rvProductList.setAdapter(adapter);
            } else {
                binding.txtNoData.setVisibility(View.VISIBLE);
                binding.rvProductList.setVisibility(View.GONE);
            }

        });
        ///
    }


    private boolean isValid()
    {
        boolean allValidate = true;

        if (productList != null && !productList.isEmpty()) {
            for (int i = 0; i < productList.size(); i++) {

                if (productList.get(i).getSelectedColor().equals("")) {
                    Toast.makeText(this, "Select color of this " + productList.get(i).getProductName() + " Product", Toast.LENGTH_SHORT).show();
                    allValidate = false;
                    break;
                }

                boolean forBreak = true;
                int brandListSize = productList.get(i).getBrand().size();
                int cnt=0;
                for(int j = 0; j< brandListSize;j++ ) {
                    if (productList.get(i).getBrand().get(j).getIsSelected().equals("Select Brand")){
                        cnt++;
                        if(brandListSize==cnt) {
                            Toast.makeText(this, "Select brand of this " + productList.get(i).getProductName() + " Product", Toast.LENGTH_SHORT).show();
                            allValidate = false;
                            forBreak = false;
                        }
                    }
                }
                if(!forBreak){
                    break;
                }
                if (productList.get(i).getQty().equals("")) {
                    Toast.makeText(this, "Enter quantity of this " + productList.get(i).getProductName() + " Product", Toast.LENGTH_SHORT).show();
                    allValidate = false;
                    break;
                }
            }

            return allValidate;
        } else {
            return allValidate;
        }

    }


}