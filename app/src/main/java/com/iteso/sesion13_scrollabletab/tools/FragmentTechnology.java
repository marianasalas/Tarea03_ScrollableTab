package com.iteso.sesion13_scrollabletab.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.sesion13_scrollabletab.Beans.itemProduct;
import com.iteso.sesion13_scrollabletab.R;

import java.util.ArrayList;
import java.util.Iterator;

import DataBase.DataBaseHandler;
import DataBase.ItemProductControl;


public class FragmentTechnology extends android.support.v4.app.Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<itemProduct> productstech;


    public FragmentTechnology() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_technology_recycler_view);

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);

        ItemProductControl itemProductControl = new ItemProductControl();
        productstech = itemProductControl.getitemProductByCategory(0,
                DataBaseHandler.getInstance(getActivity()));

/**
        productstech= new ArrayList<itemProduct>();
        productstech.add(new itemProduct(getString(R.string.title_item1),
                getString(R.string.store_item1), getString(R.string.location_item1),
                getString(R.string.phone_item1),getString(R.string.description_item1),
                0, 0 ,0));

        productstech.add(new itemProduct(getString(R.string.title_item2),
                getString(R.string.store_item2), getString(R.string.location_item2),
                getString(R.string.phone_item2),getString(R.string.description_item2),
                1, 1,1 ));

        productstech.add(new itemProduct(getString(R.string.title_item3),
                getString(R.string.store_item3), getString(R.string.location_item3),
                getString(R.string.phone_item3),getString(R.string.description_item3),
                2, 2,2 ));
*/
        mAdapter = new AdapterProduct(getActivity(), productstech);
        recyclerView.setAdapter(mAdapter);
        itemProductControl = null;
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        itemProduct itemProduct = data.getParcelableExtra("ITEM");
        Iterator<itemProduct> iterator = productstech.iterator();
        int position = 0;
        while(iterator.hasNext()){
            itemProduct item = iterator.next();
            if(item.getCode() == itemProduct.getCode()){
                productstech.set(position, itemProduct);
                break;
            }
            position++;
        }
        mAdapter.notifyDataSetChanged();
    }


}
