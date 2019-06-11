package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.FollowersAdapter;
import com.alatheer.shop_peak.Adapter.ItemAdapter;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FollowersActivity extends AppCompatActivity {
    RecyclerView recycler;
    FollowersAdapter followersAdapter;
    private List<UserModel1> userModel1ArrayList;

    String store_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_notification);
        getDataFromIntent();
        initview();

    }

    private void initview() {
        getDataFromIntent();
        recycler = findViewById(R.id.notification_recycler);

        userModel1ArrayList=new ArrayList<>();
        initrecycler();


    }

    private void getDataFromIntent() {

        Intent intent=getIntent();

        if (intent!=null){

            store_id=intent.getStringExtra("id_store");

        }
    }

    private void initrecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        followersAdapter = new FollowersAdapter(userModel1ArrayList, this);
        recycler.setAdapter(followersAdapter);

        get_storefollow(store_id);
    }

    private void get_storefollow(String id_store) {

        Api.getService()
                .get_storefollow(id_store)
                .enqueue(new Callback<List<UserModel1>>() {
                    @Override
                    public void onResponse(Call<List<UserModel1>> call, Response<List<UserModel1>> response) {
                        if (response.isSuccessful()){

                            if (response.body().size()>0){

                                userModel1ArrayList.addAll(response.body());
                                followersAdapter.notifyDataSetChanged();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserModel1>> call, Throwable t) {

                    }
                });
    }

}
