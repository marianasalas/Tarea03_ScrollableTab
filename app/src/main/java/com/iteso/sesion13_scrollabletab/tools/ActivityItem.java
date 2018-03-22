package com.iteso.sesion13_scrollabletab.tools;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.iteso.sesion13_scrollabletab.Beans.Category;
import com.iteso.sesion13_scrollabletab.Beans.Store;
import com.iteso.sesion13_scrollabletab.Beans.itemProduct;
import com.iteso.sesion13_scrollabletab.R;

import java.util.ArrayList;

import DataBase.CategoryControl;
import DataBase.DataBaseHandler;
import DataBase.ItemProductControl;
import DataBase.StoreControl;

public class ActivityItem extends AppCompatActivity {
    private Spinner images, categories, stores;
    private EditText title;
    private Button save;
    private DataBaseHandler dh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        images = findViewById(R.id.activity_item_images);
        categories = findViewById(R.id.activity_item_category);
        stores = findViewById(R.id.activity_item_store);
        title = findViewById(R.id.activity_item_title);
        save = findViewById(R.id.activity_item_save);

        dh = DataBaseHandler.getInstance(ActivityItem.this);
        CategoryControl categoryControl = new CategoryControl();
        StoreControl storeControl = new StoreControl();

        ArrayList<Category> categoryArrayList = categoryControl.getCategories(dh);
        ArrayList<Store> storeArrayList = storeControl.getStores(dh);

        ArrayAdapter<Category> categoryAdapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, categoryArrayList);
        ArrayAdapter<Store> storeAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, storeArrayList);

        categoryAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        storeAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        categories.setAdapter(categoryAdapter);
        stores.setAdapter(storeAdapter);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemProductControl itemProductControl = new ItemProductControl();
                itemProduct itemProduct = new itemProduct();
                itemProduct.setStore((Store) stores.getSelectedItem());
                itemProduct.setTitle(title.getText().toString());
                itemProduct.setImage(getResources().getIdentifier(images.getSelectedItem().toString(), "drawable", getPackageName()));
                itemProduct.setCategory((Category) categories.getSelectedItem());
                itemProduct.setDescription("Description");

                itemProductControl.addItemProduct(itemProduct,dh);
                finish();
            }
        });

    }
}
