package com.alatheer.shop_peak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Adapter.Image2Adapter;
import com.alatheer.shop_peak.Model.Image2Model;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView recyclerViewl;
    RecyclerView.LayoutManager layoutManager;
    List<Image2Model> imageModels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewl=v.findViewById(R.id.recycler);
        recyclerViewl.setHasFixedSize(true);
        layoutManager =new LinearLayoutManager(getActivity());
        recyclerViewl.setLayoutManager(layoutManager);
        Image2Model imageModel1=new Image2Model();
        imageModel1.setImage2(R.drawable.logo);
        Image2Model imageModel2=new Image2Model();
        imageModel2.setImage2(R.drawable.logo);
        Image2Model imageModel3=new Image2Model();
        imageModel3.setImage2(R.drawable.logo);
        imageModels=new ArrayList<>();
        imageModels.add(imageModel1);
        imageModels.add(imageModel2);
        imageModels.add(imageModel3);
        Image2Adapter imageAdapter=new Image2Adapter(imageModels,getActivity());
        recyclerViewl.setAdapter(imageAdapter);
        return v;
    }


}
