package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.BasketAdapter;
import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;

import java.util.List;

public class Basket_Activity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView image_title;
    TextView text_title;
    RecyclerView recyclerView_basket;
    RecyclerView.LayoutManager basket_manager;
    BasketAdapter basketAdapter;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    List<BasketModel>basketModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_);
        initview();
    }

    private void initview() {
        recyclerView_basket=findViewById(R.id.basket_recycler);
        text_title=findViewById(R.id.basket_title);
        text_title.setText("Basket");
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
     public void initRecyclerview(){
         basketModelList=myAppDatabase.dao().getdata();
         recyclerView_basket.setHasFixedSize(true);
         basket_manager=new LinearLayoutManager(this);
         recyclerView_basket.setLayoutManager(basket_manager);
         basketAdapter=new BasketAdapter(this,basketModelList);
         recyclerView_basket.setAdapter(basketAdapter);
     }

    public void senddata(final int pos) {
        Intent intent=new Intent(Basket_Activity.this, Details_two_Activity.class);
        intent.putExtra("id",basketModelList.get(pos).getId());
        intent.putExtra("counter",basketModelList.get(pos).getNum_of_cart());
        intent.putExtra("title",basketModelList.get(pos).getTitle());
        intent.putExtra("red",basketModelList.get(pos).isRed_flag());
        intent.putExtra("blue",basketModelList.get(pos).isBlue_flag());
        intent.putExtra("black",basketModelList.get(pos).isBlack_flag());
        intent.putExtra("img",basketModelList.get(pos).getImg());
        intent.putExtra("price",basketModelList.get(pos).getPrice());
        intent.putExtra("des",basketModelList.get(pos).getDescription());
        intent.putExtra("gender",basketModelList.get(pos).getGender());
        startActivity(intent);
    }


    public void senddata2(int position) {
        myAppDatabase.dao().Delete_Item(basketModelList.get(position).getId());
        initRecyclerview();
    }
}
