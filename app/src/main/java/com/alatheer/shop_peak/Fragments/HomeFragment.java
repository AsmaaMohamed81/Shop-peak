package com.alatheer.shop_peak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Adapter.Image2Adapter;
import com.alatheer.shop_peak.Adapter.ImageAdapter;
import com.alatheer.shop_peak.Model.Image2Model;
import com.alatheer.shop_peak.Model.ImageModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView,recyclerView2;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    List<ImageModel> imageModels;
    List<Image2Model>image2Models;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = v.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);
        ImageModel imageModel1 = new ImageModel();
        imageModel1.setImage(R.drawable.logo);
        ImageModel imageModel2 = new ImageModel();
        imageModel2.setImage(R.drawable.logo);
        ImageModel imageModel3 = new ImageModel();
        imageModel3.setImage(R.drawable.logo);
        ImageModel imageModel4 = new ImageModel();
        imageModel4.setImage(R.drawable.logo);
        ImageModel imageModel5 = new ImageModel();
        imageModel5.setImage(R.drawable.logo);
        ImageModel imageModel6 = new ImageModel();
        imageModel6.setImage(R.drawable.logo);
        ImageModel imageModel7 = new ImageModel();
        imageModel7.setImage(R.drawable.logo);
        imageModels = new ArrayList<>();
        imageModels.add(imageModel1);
        imageModels.add(imageModel2);
        imageModels.add(imageModel3);
        imageModels.add(imageModel4);
        imageModels.add(imageModel5);
        imageModels.add(imageModel6);
        imageModels.add(imageModel7);
        ImageAdapter imageAdapter = new ImageAdapter(imageModels, getActivity());
        recyclerView.setAdapter(imageAdapter);
        recyclerView2=v.findViewById(R.id.recycler2);
        recyclerView2.setHasFixedSize(true);
        layoutManager2=new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        Image2Model image2Model = new Image2Model();
        image2Model.setImage2(R.drawable.logo);
        Image2Model image2Model2 = new Image2Model();
        image2Model2.setImage2(R.drawable.logo);
        Image2Model image2Model3 = new Image2Model();
        image2Model3.setImage2(R.drawable.logo);
        image2Models=new ArrayList<>();
        image2Models.add(image2Model);
        image2Models.add(image2Model2);
        image2Models.add(image2Model3);
        Image2Adapter image2Adapter = new Image2Adapter(image2Models, getActivity());
        recyclerView2.setAdapter(image2Adapter);
        return v;
    }

}
