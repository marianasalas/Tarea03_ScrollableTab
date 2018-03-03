package com.iteso.sesion13_scrollabletab.Beans;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.iteso.sesion13_scrollabletab.ActivityMain;
import com.iteso.sesion13_scrollabletab.AdapterProduct;
import com.iteso.sesion13_scrollabletab.R;

import org.w3c.dom.Text;

/**
 * Created by Mariana Salas on 26/02/2018.
 */

public class itemProduct {
    private String title, store, location, phone;
    private Drawable image, thumbnail;



    public itemProduct(String title, String store, String location, String phone, Drawable image, Drawable thumbnail) {
        this.title = title;
        this.store = store;
        this.image = image;
        this.thumbnail = thumbnail;
        this.location = location;
        this.phone = phone;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public Drawable getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Drawable thumbnail) {
        this.thumbnail = thumbnail;
    }


    @Override
    public String toString() {
        return "itemProduct{" +
                "title='" + title + '\'' +
                ", store='" + store + '\'' +
                ", location='" + location + '\'' +
                ", phone='" + phone + '\'' +
                ", description= 'Lorem Ipsum....'"+
                '}';
    }

}
