package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

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
    Button btn_add_rate;
    RatingBar ratingBar;
    RecyclerView.LayoutManager layoutManager;
    RatingAdapter ratingAdapter;
    List<RatingModel> ratingModelList;
    String product_id;
    int user_id;
    int stars;
    long id;
    int id2;
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
        btn_add_rate = view.findViewById(R.id.add_rate);
        ratingBar = view.findViewById(R.id.ratbar);
        getdatafromintent();
        Log.v("usser_id", user_id + "");
        initrecycler();
        btn_add_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_Rate();
            }
        });
        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = ratingBar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    stars = (int) starsf + 1;
                    ratingBar.setRating(stars);
                    Toast.makeText(getContext(), stars + "", Toast.LENGTH_SHORT).show();
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }
                return true;
            }
        });

    }

    private void Add_Rate() {
        Log.v("usser_id", user_id + "");
        Api.getService().make_rate(id2, stars, user_id).enqueue(new Callback<RatingModel>() {
            @Override
            public void onResponse(Call<RatingModel> call, Response<RatingModel> response) {
                Toast.makeText(getContext(), "data added", Toast.LENGTH_LONG).show();
                RatingModel ratingModel = response.body();
                Log.v("aaaaa", response.message());
                initrecycler();
            }

            @Override
            public void onFailure(Call<RatingModel> call, Throwable t) {
                Log.v("aaaaa", t.getMessage());
            }
        });
    }

    private void initrecycler() {
        ratingrecycler.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        ratingrecycler.setLayoutManager(layoutManager);
        Api.getService().get_all_rating(id).enqueue(new Callback<List<RatingModel>>() {
            @Override
            public void onResponse(Call<List<RatingModel>> call, Response<List<RatingModel>> response) {
                ratingModelList = response.body();
                Log.v("ffff", response.message());
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
        id2 = Integer.parseInt(product_id);
        user_id = getActivity().getIntent().getIntExtra("user_id", 0);
    }
}
