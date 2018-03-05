package com.iteso.sesion13_scrollabletab;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iteso.sesion13_scrollabletab.Beans.itemProduct;

import java.util.ArrayList;


public class FragmentTechnology extends android.support.v4.app.Fragment {
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public FragmentTechnology() {}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        RecyclerView recyclerView = (RecyclerView)
                view.findViewById(R.id.fragment_technology_recycler_view);

        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);


        ArrayList<itemProduct> productstech = new ArrayList<>();
        productstech.add(new itemProduct(getString(R.string.title_item1),
                getString(R.string.store_item1), getString(R.string.location_item1),
                getString(R.string.phone_item1),getString(R.string.description_item1),
                0, 0 ));

        productstech.add(new itemProduct(getString(R.string.title_item2),
                getString(R.string.store_item2), getString(R.string.location_item2),
                getString(R.string.phone_item2),getString(R.string.description_item2),
                0, 0 ));

        productstech.add(new itemProduct(getString(R.string.title_item3),
                getString(R.string.store_item3), getString(R.string.location_item3),
                getString(R.string.phone_item3),getString(R.string.description_item3),
                0, 0 ));

        mAdapter = new AdapterProduct(getActivity(), productstech);
        recyclerView.setAdapter(mAdapter);
        return view;
    }

}