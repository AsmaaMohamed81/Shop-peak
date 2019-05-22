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

import java.util.ArrayList;
import java.util.List;


public class RatingFragment extends Fragment {
    RecyclerView ratinglist;
    RecyclerView.LayoutManager layoutManager;
    RatingAdapter ratingAdapter;
    List<RatingModel> ratingModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_rating, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        ratinglist = view.findViewById(R.id.recycler_rating);
        layoutManager = new LinearLayoutManager(getContext());
        ratinglist.setLayoutManager(layoutManager);
        ratingAdapter = new RatingAdapter(getActivity(), get_rating_list());
        ratinglist.setAdapter(ratingAdapter);

    }

    private List<RatingModel> get_rating_list() {
        ratingModelList = new ArrayList<>();
        ratingModelList.add(new RatingModel("mohamed hamada", 3));
        ratingModelList.add(new RatingModel("ahmed hamada", 4));
        ratingModelList.add(new RatingModel("mohamed mostafa", 2));
        ratingModelList.add(new RatingModel("ali ", 5));
        return ratingModelList;
    }
}
