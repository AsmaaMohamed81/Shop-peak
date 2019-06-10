package com.alatheer.shop_peak.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.ItemAdapter;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;

import java.util.List;


public class DescriptionFragment extends Fragment {
    List<Item> items;
    String details;
    RecyclerView description_recycler;
    RecyclerView.LayoutManager description__layoutManager;
    ItemAdapter itemAdapter;
    TextView txt_details;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_description, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        getDataFromIntent();
        description_recycler = view.findViewById(R.id.recycler_description);
        txt_details = view.findViewById(R.id.text_view);
        txt_details.setText(details);
        initrecycler();


    }

    private void initrecycler() {
        description_recycler.setHasFixedSize(true);
        description__layoutManager = new LinearLayoutManager(getContext());
        description_recycler.setLayoutManager(description__layoutManager);
        itemAdapter = new ItemAdapter(items, getContext());
        description_recycler.setAdapter(itemAdapter);
    }

    public void getDataFromIntent() {
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        items = (List<Item>) extras.getSerializable("itemlist");
        details = getActivity().getIntent().getStringExtra("details");


    }

}
