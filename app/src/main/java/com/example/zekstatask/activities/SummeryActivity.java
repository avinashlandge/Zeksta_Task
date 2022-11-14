package com.example.zekstatask.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.zekstatask.adapters.ProductListAdapter;
import com.example.zekstatask.adapters.SummeryListAdapter;
import com.example.zekstatask.databinding.ActivityMainBinding;
import com.example.zekstatask.databinding.ActivitySummeryBinding;
import com.example.zekstatask.models.Product;
import com.example.zekstatask.viewmodels.ProductViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;


public class SummeryActivity extends AppCompatActivity {

    private ActivitySummeryBinding binding;
    public ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = ActivitySummeryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        productList = getIntent().getParcelableArrayListExtra("data");

        if (productList != null && !productList.isEmpty())
        {

            binding.txtNoData.setVisibility(View.GONE);
            binding.rvProductList.setVisibility(View.VISIBLE);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            binding.rvProductList.setLayoutManager(linearLayoutManager);

            SummeryListAdapter adapter = new SummeryListAdapter(productList, SummeryActivity.this);
            binding.rvProductList.setAdapter(adapter);


        }
        else
        {
            binding.txtNoData.setVisibility(View.VISIBLE);
            binding.rvProductList.setVisibility(View.GONE);
        }

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}