package com.iteso.sesion13_scrollabletab;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.iteso.sesion13_scrollabletab.Beans.itemProduct;

public class ActivityProduct extends AppCompatActivity {
EditText title, store,location, phone;
ImageView image;
itemProduct itemProduct, productItem;
Button save, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        title = (EditText) findViewById(R.id.activity_product_title);
        store = (EditText) findViewById(R.id.activity_product_store);
        location = (EditText) findViewById(R.id.activity_product_location);
        image = (ImageView) findViewById(R.id.activity_product_image);
        save = findViewById(R.id.activity_product_save);
        cancel = findViewById(R.id.activity_product_cancel);
        phone = findViewById(R.id.activity_product_phone);


        if(getIntent().getExtras() != null) {
            itemProduct = getIntent().getParcelableExtra("ITEM");
            if (itemProduct != null) {
                title.setText(itemProduct.getTitle());
                store.setText(itemProduct.getStore());
                location.setText(itemProduct.getLocation());
                phone.setText(itemProduct.getPhone());
                switch (itemProduct.getImage()) {
                    case 0:
                        image.setImageResource(R.drawable.mac);
                        break;
                    case 1:
                        image.setImageResource(R.drawable.alienware);
                        break;
                    case 2:
                        image.setImageResource(R.drawable.lanix);
                        break;
                }
            }
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productItem = new itemProduct();
                productItem.setTitle(title.getText().toString());
                productItem.setStore(store.getText().toString());
                productItem.setLocation(location.getText().toString());
                productItem.setPhone(phone.getText().toString());
                productItem.setCode(itemProduct.getCode());
                productItem.setImage(itemProduct.getImage());

                Intent intent = new Intent();
                intent.putExtra("ITEM", productItem);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
