package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.alatheer.shop_peak.Adapter.BasketAdapter;
import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;

import java.util.List;

public class Basket_Activity extends AppCompatActivity {
    RecyclerView recyclerView_basket;
    Button edit;
    RecyclerView.LayoutManager basket_manager;
    BasketAdapter basketAdapter;
    MyAppDatabase myAppDatabase;
    BasketModel basketModel;
    List<BasketModel>basketModelList;
    ViewPager viewPager;
    CustomSwipeAdapter customSwipeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_);
        initview();
    }

    private void initview() {
        recyclerView_basket=findViewById(R.id.basket_recycler);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"productdb").allowMainThreadQueries().build();
        initRecyclerview();
        edit=findViewById(R.id.btn_editcart);
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
        startActivity(intent);
    }


}
