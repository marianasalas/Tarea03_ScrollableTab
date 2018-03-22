package com.iteso.sesion13_scrollabletab.Beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mariana Salas on 20/03/2018.
 */

public class Category implements Parcelable {
    private int id;
    private String name;

    protected Category(Parcel in) {
        id = in.readInt();
        name = in.readString();
    }

    public Category() {
        this.id = 0;
        this.name = "";
    }

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
    }
}
