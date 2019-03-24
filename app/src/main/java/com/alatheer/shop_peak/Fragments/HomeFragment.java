package com.alatheer.shop_peak.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Adapter.HomeAdapter;

import com.alatheer.shop_peak.Adapter.OfferAdapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.OfferModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView recyclerView,recyclerView2;
    RecyclerView.LayoutManager layoutManager,layoutManager2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {



        /////offer
        recyclerView = v.findViewById(R.id.recycler_offer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);
        OfferAdapter offerAdapter = new OfferAdapter(offerModelList(), getActivity());
        recyclerView.setAdapter(offerAdapter);

        ////home

        recyclerView2=v.findViewById(R.id.recycler_home);
        recyclerView2.setHasFixedSize(true);
        layoutManager2=new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        HomeAdapter homeAdapter = new HomeAdapter(homeModelList(), getActivity());
        recyclerView2.setAdapter(homeAdapter);


    }

    private List<OfferModel> offerModelList (){

        List<OfferModel>  offerlist = new ArrayList<>();

        offerlist.add(new OfferModel(R.drawable.logo));
        offerlist.add(new OfferModel(R.drawable.logo));
        offerlist.add(new OfferModel(R.drawable.logo));
        offerlist.add(new OfferModel(R.drawable.logo));
        offerlist.add(new OfferModel(R.drawable.logo));
        offerlist.add(new OfferModel(R.drawable.logo));


        return offerlist;
    }

    private List<HomeModel> homeModelList (){

        List<HomeModel>  homelist = new ArrayList<>();

        homelist.add(new HomeModel(R.drawable.logo));
        homelist.add(new HomeModel(R.drawable.logo));
        homelist.add(new HomeModel(R.drawable.logo));
        homelist.add(new HomeModel(R.drawable.logo));
        homelist.add(new HomeModel(R.drawable.logo));
        homelist.add(new HomeModel(R.drawable.logo));


        return homelist;
    }

}
