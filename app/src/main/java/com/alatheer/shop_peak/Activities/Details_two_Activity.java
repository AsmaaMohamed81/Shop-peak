package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;

public class Details_two_Activity extends AppCompatActivity {
    ImageView details_img, back_image, plus_circle, minus_circle, shopping_cart;
    TextView details_title, details_des, counter, cart_num;
    CheckBox c_red, c_blue, c_black;
    Button details_price, addcart;
    MyAppDatabase myAppDatabase;
    ImageView img_title;
    BasketModel basketModel;
    CustomSwipeAdapter customSwipeAdapter;
    FloatingActionButton fab_favorite;
    RatingBar ratingBar;
    boolean red = false;
    boolean blue = false;
    boolean black = false;
    int count;
     int id_intent;
     String count_intent ;
     String title_intent ;
     int image_intent ;
     boolean red_intent ;
     boolean blue_intent ;
     boolean black_intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_two_);
        initview();
    }

    private void initview() {
        img_title = findViewById(R.id.viewpager);
        back_image = findViewById(R.id.back_image);
        details_img = findViewById(R.id.details_image);
        ratingBar = findViewById(R.id.ratbar2);
        details_title = findViewById(R.id.details_title);
        plus_circle = findViewById(R.id.add_circle);
        minus_circle = findViewById(R.id.remove_circle);
        counter = findViewById(R.id.counter);
        shopping_cart = findViewById(R.id.shopping_cart);
        cart_num = findViewById(R.id.cart_num);
        addcart = findViewById(R.id.add_cart);
        c_red = findViewById(R.id.checkbox_red);
        c_blue = findViewById(R.id.checkbox_blue);
        c_black = findViewById(R.id.checkbox_black);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"productdb").allowMainThreadQueries().build();
        getDatafromIntent();
        plus_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(counter.getText().toString());
                count++;
                counter.setText(count++ + "");
            }
        });
        c_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c_red.isChecked()){
                    c_red.setButtonDrawable(R.drawable.ic_check);
                    red = true;
                } else {
                    c_red.setButtonDrawable(R.drawable.ic_check_gray);
                    red = false;
                }

            }
        });
        c_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c_blue.isChecked()){
                    c_blue.setButtonDrawable(R.drawable.ic_check);
                    blue = true;
                } else {
                    c_blue.setButtonDrawable(R.drawable.ic_check_gray);
                    blue = false;
                }

            }
        });
        c_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(c_black.isChecked()){
                    c_black.setButtonDrawable(R.drawable.ic_check);
                    black = true;
                } else {
                    c_black.setButtonDrawable(R.drawable.ic_check_gray);
                    black = false;
                }

            }
        });
        minus_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(counter.getText().toString());
                if (count != 0) {
                    count--;
                    counter.setText(count-- + "");

                }
            }
        });
        addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                basketModel= new BasketModel(id_intent, title_intent,counter.getText().toString(), red, blue, black,image_intent);
                myAppDatabase.dao().editproduct(basketModel);
            }
        });
        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details_two_Activity.this, Basket_Activity.class);
                startActivity(intent);
            }
        });
    }
   public void getDatafromIntent(){
       Intent intent = getIntent();
       id_intent = intent.getIntExtra("id", 0);
       count_intent = intent.getStringExtra("counter");
       title_intent = intent.getStringExtra("title");
       image_intent = intent.getIntExtra("img", 0);
       red_intent = intent.getBooleanExtra("red", false);
       blue_intent = intent.getBooleanExtra("blue", false);
       black_intent = intent.getBooleanExtra("black", false);
       img_title.setImageResource(image_intent);
       counter.setText(count_intent);
       details_title.setText(title_intent);
       if (red_intent == true) {
           c_red.setButtonDrawable(R.drawable.ic_check);
       } else {
           c_red.setButtonDrawable(R.drawable.ic_check_gray);
       }
       if (blue_intent == true) {
           c_blue.setButtonDrawable(R.drawable.ic_check);
       } else {
           c_blue.setButtonDrawable(R.drawable.ic_check_gray);
       }
       if (black_intent == true) {
           c_black.setButtonDrawable(R.drawable.ic_check);
       } else {
           c_black.setButtonDrawable(R.drawable.ic_check_gray);
       }
    }
}

