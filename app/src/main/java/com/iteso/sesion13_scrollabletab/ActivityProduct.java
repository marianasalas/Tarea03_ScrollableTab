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
EditText title, store,location;
ImageView image;
itemProduct itemProduct;
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

        if(getIntent().getExtras() != null) {
            itemProduct = getIntent().getParcelableExtra("ITEM");
            if (itemProduct != null) {
                title.setText(itemProduct.getTitle());
                store.setText(itemProduct.getStore());
                location.setText(itemProduct.getLocation());
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
                itemProduct.setTitle(title.getText().toString());
                itemProduct.setStore(store.getText().toString());
                itemProduct.setLocation(location.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("ITEM", itemProduct);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
