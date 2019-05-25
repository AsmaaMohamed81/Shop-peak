package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
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
import com.alatheer.shop_peak.Adapter.PagerAdapterTwo;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Details_two_Activity extends AppCompatActivity {
    ImageView details_img, back_image, plus_circle, minus_circle, shopping_cart;
    TextView details_title, details_des, counter, cart_num;
    ViewPager viewPager;
    CheckBox c_red, c_blue, c_black;
    Button details_price, addcart;
    MyAppDatabase myAppDatabase;
    ImageView img_title;
    BasketModel basketModel;
    CustomSwipeAdapter customSwipeAdapter;
    FloatingActionButton fab_favorite;
    RatingBar ratingBar;
    boolean red ;
    boolean blue ;
    boolean black ;
    int count;
    int id_intent;
    String image_intent_String;
    String count_intent ;
    String title_intent ;
    String gender_intent;
    String price_intent ;
    String des_intent;
    String image_intent ;
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
        details_title = findViewById(R.id.details_title);
        shopping_cart = findViewById(R.id.shopping_cart);
        back_image = findViewById(R.id.back_image);
        getDatafromIntent();
        details_title.setText(title_intent);
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Description"));
        tabLayout.addTab(tabLayout.newTab().setText("Rating"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapterTwo pagerAdapter = new com.alatheer.shop_peak.Adapter.PagerAdapterTwo(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Details_two_Activity.this, Basket_Activity.class);
                startActivity(intent);
                Animatoo.animateDiagonal(Details_two_Activity.this);
            }
        });
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void getDatafromIntent() {
       Intent intent = getIntent();
       Bundle extras = intent.getExtras();
       id_intent = intent.getIntExtra("id", 0);
       count_intent = intent.getStringExtra("counter");
       title_intent = intent.getStringExtra("title");
        image_intent = intent.getStringExtra("img");
        gender_intent = intent.getStringExtra("gender");
        price_intent = intent.getStringExtra("price");
        des_intent = intent.getStringExtra("des");
       //image_intent_String=image_intent.toString();
       //Uri uri=Uri.parse(image_intent_String);
       //File file =new File(uri.getPath());
       //File[]path=new File[]{file,file,file};
    }
}

