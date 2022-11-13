package com.example.zekstatask.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.zekstatask.R;
import com.example.zekstatask.databinding.ProductListBinding;
import com.example.zekstatask.models.Brand;
import com.example.zekstatask.models.Product;

import java.util.ArrayList;


public class ProductListAdapter extends RecyclerView.Adapter {
    private ArrayList<Product> productArrList;
    Context context;

    public ProductListAdapter(ArrayList<Product> productArrList, Context context) {
        this.productArrList = productArrList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductListBinding binding = ProductListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final ViewHolder viewHolderClass = (ViewHolder) holder;

        viewHolderClass.binding.txtProductName.setText("" + productArrList.get(position).getProductName());
        viewHolderClass.binding.txtPrice.setText("Price : " + productArrList.get(position).getPrice());
        Glide.with(context)
                .load(productArrList.get(position).getPicture())
                .error(R.drawable.product_placeholder)
                .placeholder(R.drawable.product_placeholder)
                .into(viewHolderClass.binding.imgProduct);

        ArrayList<String> bradName = new ArrayList<>();
        if(!productArrList.get(position).getBrand().get(0).getIsSelected().equals("")) {
            productArrList.get(position).getBrand().add(0, new Brand("-1", "Select Brand", "Select Brand"));
        }
        for (int i = 0; i < productArrList.get(position).getBrand().size(); i++)
        {
            bradName.add(productArrList.get(position).getBrand().get(i).getName());
        }

        ArrayAdapter brandAdapter = new ArrayAdapter(context, android.R.layout.simple_spinner_item, bradName);
        viewHolderClass.binding.spBrand.setAdapter(brandAdapter);

        viewHolderClass.binding.spBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int selPos, long l)
            {

                if (selPos!=0)
                {
                    productArrList.get(position).getBrand().get(selPos).setIsSelected(productArrList.get(position).getBrand().get(selPos).getName());
                    System.out.println("SelectString : "+ productArrList.get(position).getBrand().get(selPos).getIsSelected());
                }
                System.out.println("SelectString_1 : "+productArrList.get(position).getBrand().get(selPos).getName());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {

            }
        });

        viewHolderClass.binding.rdGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.rdRed:
                        productArrList.get(position).setSelectedColor("Red");
                        break;
                    case R.id.rdBlue:
                        productArrList.get(position).setSelectedColor("Blue");
                        break;
                    case R.id.rdGreen:
                        productArrList.get(position).setSelectedColor("Green");
                        break;

                }
            }
        });

        for (int i = 0; i < productArrList.get(position).getColors().size(); i++) {
            switch (productArrList.get(position).getColors().get(i).toLowerCase()) {
                case "red":
                    viewHolderClass.binding.rdRed.setVisibility(View.VISIBLE);
                    break;
                case "green":
                    viewHolderClass.binding.rdGreen.setVisibility(View.VISIBLE);
                    break;
                case "blue":
                    viewHolderClass.binding.rdBlue.setVisibility(View.VISIBLE);
                    break;
            }
        }
        viewHolderClass.binding.etQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                productArrList.get(position).setQty(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    @Override
    public int getItemCount() {
        return productArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProductListBinding binding;

        public ViewHolder(ProductListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
