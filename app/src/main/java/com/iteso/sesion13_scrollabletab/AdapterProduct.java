package com.iteso.sesion13_scrollabletab;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.sesion13_scrollabletab.Beans.itemProduct;

import java.util.ArrayList;

/**
 * Created by Mariana Salas on 26/02/2018.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    ArrayList<itemProduct> products;
    private Context context;

    public AdapterProduct(ArrayList<itemProduct> products, Context context){
        this.products = products;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView miTitle, miStore, miLocation, miPhone;
        public ImageView miImage, miThumbnail;
        public Button detail;

        public ViewHolder(View v){
            super(v);
            miTitle = v.findViewById(R.id.item_product_title);
            miStore = v.findViewById(R.id.item_product_store);
            miLocation = v.findViewById(R.id.item_product_location);
            miPhone = v.findViewById(R.id.item_product_phone);
            miImage = v.findViewById(R.id.item_product_image);
            miThumbnail = v.findViewById(R.id.item_product_thumbnail);
            detail = v.findViewById(R.id.item_product_detail);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void
    onBindViewHolder(final ViewHolder holder, final int position) {
        holder.miTitle.setText(products.get(position).getTitle());
        holder.miStore.setText(products.get(position).getStore());
        holder.miLocation.setText(products.get(position).getLocation());
        holder.miPhone.setText(products.get(position).getPhone());
        holder.miImage.setImageDrawable(products.get(position).getImage());
        holder.miThumbnail.setImageDrawable(products.get(position).getThumbnail());

        holder.miPhone.setOnClickListener(new View.OnClickListener() {
                                              @Override
                                              public void onClick(View view) {
                                                  Intent call = new Intent (Intent.ACTION_DIAL, Uri.parse("tel: "+
                                                          products.get(position).getPhone()));
                                                  context.startActivity(call);
                                              }




    });

        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,products.get(position).toString(),Toast.LENGTH_LONG).show();
            }
        });

        holder.miImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,products.get(position).toString(),Toast.LENGTH_LONG).show();
            }
        });
        
    }
    public int getItemCount(){
        return products.size();
    }






}
