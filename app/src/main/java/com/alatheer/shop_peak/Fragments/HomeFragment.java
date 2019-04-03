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
    RecyclerView recyclerView,recyclerView2,recyclerView3;
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

    public List<OfferModel> offerModelList (){

        List<OfferModel>  offerlist = new ArrayList<>();

        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item3));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item3));
        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item3));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item3));


        return offerlist;
    }

    public List<HomeModel> homeModelList (){

        List<HomeModel>  homelist = new ArrayList<>();

        homelist.add(new HomeModel(new int[]{R.drawable.item3,R.drawable.item2,R.drawable.item1},"dress","a beautiful blue  address for girls ","$25.99"));
        homelist.add(new HomeModel(new int[]{R.drawable.item2,R.drawable.item3},"jacket","a comfartable black jacket for boys","$30.00 "));
        homelist.add(new HomeModel(new int[]{R.drawable.item3,R.drawable.item1},"shoes","a comfartable blue sportive shoes for playing football","$20.00"));
        homelist.add(new HomeModel(new int[]{R.drawable.item3,R.drawable.item2},"jacket","a comfartable black jacket for boys","$30.00 "));
        homelist.add(new HomeModel(new int[]{R.drawable.item1,R.drawable.item3},"dress","a beautiful blue  address for girls ","$25.99"));
        homelist.add(new HomeModel(new int[]{R.drawable.item1,R.drawable.item2},"shoes","a comfartable blue sportive shoes for playing football","$20.00"));
        return homelist;
    }

}
