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
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.BasketAdapter;
import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.BasketModel2;
import com.alatheer.shop_peak.Model.BasketModel3;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
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
    Button add_lat_lon;
    MySharedPreference mPrefs;
    UserModel1 userModel1;
    private String lat,lon;
    int price,count_of_item;
    String sum;
    int total;
    TextView txt_total;
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
        txt_total = findViewById(R.id.txt_total);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"myorders_db").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(),Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
        initRecyclerview();
        mPrefs =MySharedPreference.getInstance();
        userModel1 = mPrefs.Get_UserData(Basket_Activity.this);
        final String name = userModel1.getFull_name();
        final String USER_ID = userModel1.getId();
        final String address = userModel1.getAddress();
        final String phone = userModel1.getPhone();
        final String type = userModel1.getType();
        image_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // BasketModel2 basketModel2 =new BasketModel2(type,basketModelList,USER_ID,name,address
                //,lat,lon,phone);
                if(basketModelList.size()>0){
                    Intent intent = new Intent(Basket_Activity.this,MapsActivity.class);
                    intent.putExtra("type",type);
                    intent.putExtra("user_id",USER_ID);
                    intent.putExtra("name",name);
                    intent.putExtra("address",address);
                    intent.putExtra("flag",1);
                    intent.putExtra("list",(Serializable) basketModelList);
                    intent.putExtra("phone",phone);
                    startActivity(intent);
                }else {
                    Toast.makeText(Basket_Activity.this,"there is no product in basket",Toast.LENGTH_LONG).show();
                }


                //basketModel2.withOrderItemList(myAppDatabase.dao().getdata());
                /*Api.getService().add_to_basket(basketModel2).enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                       Log.v("llll",response.message());
                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {
                    Log.v("eeee",t.getMessage());
                    }
                });*/
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
        //intent.putExtra("id",basketModelList.get(pos).getId());
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
        myAppDatabase.dao().Delete_Item(Integer.parseInt(basketModelList.get(position).sanfIdFk));
        price = Integer.parseInt(basketModelList.get(position).sanfPrice);
        count_of_item = Integer.parseInt(basketModelList.get(position).sanfAmount);
        total = total-(price*count_of_item);
        txt_total.setText("TOTAL : "+ total+"");
        finish();
        startActivity(getIntent());

    }

    public void sendBasketData(int position) {
        price = Integer.parseInt(basketModelList.get(position).sanfPrice);
        count_of_item = Integer.parseInt(basketModelList.get(position).sanfAmount);
        total = total + price * count_of_item;
        txt_total.setText("TOTAL : "+ total+"");
    }
}
