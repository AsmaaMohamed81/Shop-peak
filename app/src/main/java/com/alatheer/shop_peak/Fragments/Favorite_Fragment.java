package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Adapter.FavoriteAdapter;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Favorite_Fragment extends android.app.Fragment {
    RecyclerView recyclerView_favorite;
    RecyclerView.LayoutManager basket_manager;
    FavoriteAdapter favoriteAdapter;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    List<BasketModel> basketModelList;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_, container, false);
        //activity = getActivity();
        //((MainActivity)activity).setOnBackPressedListener(new OnBackPressedListener((FragmentActivity) activity) );
        initview(view);
        return view;
    }

    private void initview(View view) {
        recyclerView_favorite = view.findViewById(R.id.basket_recycler);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "productdb").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(), Favorite_Database.class, "favoritedb").allowMainThreadQueries().build();
        initRecyclerview();
    }

    private void initRecyclerview() {
        basketModelList = favorite_database.dao_favorite().getdata();
        recyclerView_favorite.setHasFixedSize(true);
        basket_manager = new LinearLayoutManager(getActivity());
        recyclerView_favorite.setLayoutManager(basket_manager);
        favoriteAdapter = new FavoriteAdapter(getActivity(), basketModelList, this);
        recyclerView_favorite.setAdapter(favoriteAdapter);
    }

    public void senddate(int position) {
        favorite_database.dao_favorite().Delete_Item(basketModelList.get(position).getId());
        initRecyclerview();
    }
}



