package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.BasketAdapter;
import com.alatheer.shop_peak.Adapter.FavoriteAdapter;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;

import java.util.List;

public class Favorite_Activity extends AppCompatActivity {
    /*ImageView image_title;
    TextView text_title;
    RecyclerView recyclerView_favorite;
    RecyclerView.LayoutManager basket_manager;
    FavoriteAdapter favoriteAdapter;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    List<BasketModel> basketModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_);
        initview();
    }
    private void initview() {
        recyclerView_favorite=findViewById(R.id.basket_recycler);
        text_title=findViewById(R.id.basket_title);
        text_title.setText("Favorite");
        image_title=findViewById(R.id.back_image);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"productdb").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(),Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
        image_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initRecyclerview();
    }

    private void initRecyclerview() {
        basketModelList=favorite_database.dao_favorite().getdata();
        recyclerView_favorite.setHasFixedSize(true);
        basket_manager=new LinearLayoutManager(this);
        recyclerView_favorite.setLayoutManager(basket_manager);
        favoriteAdapter=new FavoriteAdapter(this,basketModelList);
        recyclerView_favorite.setAdapter(favoriteAdapter);
    }*/

}
