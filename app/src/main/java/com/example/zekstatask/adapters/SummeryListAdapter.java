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
import com.example.zekstatask.databinding.ProductsummeryListBinding;
import com.example.zekstatask.models.Product;

import java.util.ArrayList;


public class SummeryListAdapter extends RecyclerView.Adapter {
    private ArrayList<Product> productArrList;
    Context context;

    public SummeryListAdapter(ArrayList<Product> productArrList, Context context) {
        this.productArrList = productArrList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProductsummeryListBinding binding = ProductsummeryListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final ViewHolder viewHolderClass = (ViewHolder) holder;

        viewHolderClass.binding.txtProductName.setText("" + productArrList.get(position).getProductName());
        viewHolderClass.binding.txtPrice.setText("Price : " + productArrList.get(position).getPrice());
        viewHolderClass.binding.txtSelectedcolors.setText(productArrList.get(position).getSelectedColor());
        String selectedBrand="";
        for(int i=0;i<productArrList.get(position).getBrand().size();i++){
            if(!productArrList.get(position).getBrand().get(i).getIsSelected().equals("Select Brand")) {
                selectedBrand = productArrList.get(position).getBrand().get(i).getName();
            }
        }
        viewHolderClass.binding.txtSelectedbrand.setText(selectedBrand);

        Glide.with(context)
                .load(productArrList.get(position).getPicture())
                .error(R.drawable.product_placeholder)
                .placeholder(R.drawable.product_placeholder)
                .into(viewHolderClass.binding.imgProduct);

        try {
            String price1 = productArrList.get(position).getPrice().replace("$", "");
            String price = price1.replace(",", "");
            String qty = productArrList.get(position).getQty();
            int qty1=1;
            if(qty!=null && !qty.equals("")) {
                 qty1 = Integer.parseInt(qty);
            }
            viewHolderClass.binding.txtqty.setText("Qty : " +qty1);
            viewHolderClass.binding.txtTotalprice.setText("$"+String.format("%.2f",(qty1 * (Double.parseDouble(price)) ))+ "");
        }catch (Exception e){
            e.printStackTrace();
        }



    }


    @Override
    public int getItemCount() {
        return productArrList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProductsummeryListBinding binding;

        public ViewHolder(ProductsummeryListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }
}
