package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Adapter.RatingAdapter;
import com.alatheer.shop_peak.Model.RatingModel;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RatingFragment extends Fragment {
    RecyclerView ratingrecycler;
    RecyclerView.LayoutManager layoutManager;
    RatingAdapter ratingAdapter;
    List<RatingModel> ratingModelList;
    String product_id;
    long id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        ratingrecycler = view.findViewById(R.id.recycler_rating);
        getdatafromintent();
        initrecycler();

    }

    private void initrecycler() {
        ratingrecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        ratingrecycler.setLayoutManager(layoutManager);
        Api.getService().get_all_rating(id).enqueue(new Callback<List<RatingModel>>() {
            @Override
            public void onResponse(Call<List<RatingModel>> call, Response<List<RatingModel>> response) {
                ratingModelList = response.body();
                ratingAdapter = new RatingAdapter(getContext(), ratingModelList);
                ratingrecycler.setAdapter(ratingAdapter);
            }

            @Override
            public void onFailure(Call<List<RatingModel>> call, Throwable t) {

            }
        });
    }

    public void getdatafromintent() {
        product_id = getActivity().getIntent().getStringExtra("id");
        id = Long.parseLong(product_id);
    }
}
