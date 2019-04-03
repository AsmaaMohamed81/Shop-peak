package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ImageView details_img,back_image,add_circle,remove_circle,shopping_cart;
    TextView details_title,details_des,counter,cart_num;
    Button details_price,addcart;
    ViewPager viewPager;
    CustomSwipeAdapter customSwipeAdapter;
    FloatingActionButton fab_favorite;
    RatingBar ratingBar;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initview();
    }

    private void initview() {
        back_image = findViewById(R.id.back_image);
        details_img = findViewById(R.id.details_image);
        ratingBar = findViewById(R.id.ratbar2);
        details_title = findViewById(R.id.details_title);
        add_circle = findViewById(R.id.add_circle);
        remove_circle = findViewById(R.id.remove_circle);
        counter = findViewById(R.id.counter);
        shopping_cart = findViewById(R.id.shopping_cart);
        cart_num = findViewById(R.id.cart_num);
        addcart = findViewById(R.id.add_cart);
        //details_des=findViewById(R.id.details_des);
        details_price = findViewById(R.id.details_price);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        int[] image = extras.getIntArray("homeimage");
        viewPager=findViewById(R.id.viewpager);
        customSwipeAdapter=new CustomSwipeAdapter(image,this);
        viewPager.setAdapter(customSwipeAdapter);
        String title = intent.getStringExtra("title");
        String des = intent.getStringExtra("des");
        String price = intent.getStringExtra("price");
        details_title.setText(title);
        fab_favorite = findViewById(R.id.fab_favorite);
        details_price.setText(price);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                finish();
            }
        });
        add_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(counter.getText().toString());
                count++;
                counter.setText(count++ + "");
            }
        });
        remove_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(counter.getText().toString());
                if (count != 0) {
                    count--;
                    counter.setText(count-- + "");

                }
            }
        });
       /* addcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count_cart = Integer.parseInt(cart_num.getText().toString());
                count_cart++;
                cart_num.setText(count_cart++ + "");
                cart_num.setVisibility(View.VISIBLE);
            }
        });*/
        fab_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_sold));
                    flag = false;
                } else if (!flag) {
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
                    flag = true;
                }
            }
        });
        ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = ratingBar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    int stars = (int)starsf + 1;
                    ratingBar.setRating(stars);
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }
                return true;
            }});
            }


}
