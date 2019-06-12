package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alatheer.shop_peak.Adapter.AllOfferAdapter;
import com.alatheer.shop_peak.Adapter.HomeAdapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Offer_Activity extends AppCompatActivity {

    RecyclerView recycler_offer;

    AllOfferAdapter allOfferAdapter;

    List<HomeModel> homeModelList;

    String offer_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_);
        getDataIntent();
        initview();
    }

    private void getDataIntent() {

        Intent intent=getIntent();

        if (intent!=null){
            offer_id=intent.getStringExtra("offer_id");

        }
    }

    private void initview() {
        recycler_offer=findViewById(R.id.recycler_offer);
        homeModelList=new ArrayList<>();

        initrecycle();
        get_offer_products(offer_id);

    }

    private void get_offer_products(String offer_id) {

        Api.getService()
                .get_offer_products(offer_id)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size()>0){

                                homeModelList.addAll(response.body());
                                allOfferAdapter.notifyDataSetChanged();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

                    }
                });
    }

    private void initrecycle() {
        recycler_offer.setLayoutManager(new LinearLayoutManager(this));
        recycler_offer.setHasFixedSize(true);
        allOfferAdapter =new AllOfferAdapter(homeModelList,this);
        recycler_offer.setAdapter(allOfferAdapter);
    }
}
