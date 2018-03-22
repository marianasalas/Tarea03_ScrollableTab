package com.iteso.sesion13_scrollabletab.Beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mariana Salas on 26/02/2018.
 */

public class itemProduct implements Parcelable{
    private String title, description;
    private Integer image;
    private int code;
    private Category category;
    private Store store;

    public itemProduct(){
        this.title = "";
        this.description = "";
        this.image = 0;
        this.code = 0;
        this.category = null;
        this.store = null;
    }

    public itemProduct(String title, String description, Integer image, int code, Category category, Store store) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.code = code;
        this.category = category;
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "itemProduct{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                ", code=" + code +
                ", category=" + category +
                ", store=" + store +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeValue(this.image);
        dest.writeInt(this.code);
        dest.writeParcelable(this.category, flags);
        dest.writeParcelable(this.store, flags);
    }

    protected itemProduct(Parcel in) {
        this.title = in.readString();
        this.description = in.readString();
        this.image = (Integer) in.readValue(Integer.class.getClassLoader());
        this.code = in.readInt();
        this.category = in.readParcelable(Category.class.getClassLoader());
        this.store = in.readParcelable(Store.class.getClassLoader());
    }

    public static final Creator<itemProduct> CREATOR = new Creator<itemProduct>() {
        @Override
        public itemProduct createFromParcel(Parcel source) {
            return new itemProduct(source);
        }

        @Override
        public itemProduct[] newArray(int size) {
            return new itemProduct[size];
        }
    };
}
