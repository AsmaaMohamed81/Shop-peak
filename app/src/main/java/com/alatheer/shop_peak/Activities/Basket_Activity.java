package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.BasketAdapter;
import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.BasketModel2;
import com.alatheer.shop_peak.Model.BasketModel3;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Basket_Activity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView image_title;
    TextView text_title;
    RecyclerView recyclerView_basket;
    RecyclerView.LayoutManager basket_manager;
    BasketAdapter basketAdapter;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    List<OrderItemList>basketModelList;
    Button add;
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
        add = findViewById(R.id.add);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"order_db").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(),Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
        initRecyclerview();
        image_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BasketModel3 basketModel3 =new BasketModel3("31","kkkk","31.000","30.012",
                        "01065242773","mohamed hamada");
                //basketModel3.setUserId("31");
                //basketModel3.withAddress("شارع الاستاد");
               // basketModel3.withClientName("mohamed hamada");
                //basketModel3.withPhone("01065242773");
                //basketModel3.withUserLang("31");
                //basketModel3.withUserLat("31");
                //basketModel2.withOrderItemList(myAppDatabase.dao().getdata());
                Api.getService().add_to_basket(basketModel3).enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                       Log.v("llll",response.message());
                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {
                    Log.v("eeee",t.getMessage());
                    }
                });
            }
        });

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
        intent.putExtra("counter",basketModelList.get(pos).sanfAmount);
        intent.putExtra("title",basketModelList.get(pos).sanfIdFk);
        //intent.putExtra("red",basketModelList.get(pos).isRed_flag());
        //intent.putExtra("blue",basketModelList.get(pos).isBlue_flag());
        //intent.putExtra("black",basketModelList.get(pos).isBlack_flag());
        //intent.putExtra("img",basketModelList.get(pos).getImg());
        intent.putExtra("price", (basketModelList.get(pos).sanfPrice));
        //intent.putExtra("des",basketModelList.get(pos).getDescription());
        //intent.putExtra("gender",basketModelList.get(pos).getGender());
        startActivity(intent);
        Animatoo.animateDiagonal(Basket_Activity.this);
    }


    public void senddata2(int position) {
        myAppDatabase.dao().Delete_Item(basketModelList.get(position).getId());
        initRecyclerview();
    }
}
