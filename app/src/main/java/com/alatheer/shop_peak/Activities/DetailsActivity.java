package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ImageView details_img,back_image,plus_circle,minus_circle,shopping_cart;
    TextView details_title,details_des,counter,cart_num;
    CheckBox c_red,c_blue,c_black;
    Button details_price,addcart,editcart;
    MyAppDatabase myAppDatabase;
    ViewPager viewPager;
    CustomSwipeAdapter customSwipeAdapter;
    FloatingActionButton fab_favorite;
    RatingBar ratingBar;
    boolean flag=true;
    boolean red=false;
    boolean blue=false;
    boolean black=false;
    String title;
    int count;
    int id;
    EditText order_num;
    BasketModel basketModel;
    BasketModel basketModel2;
    BasketModel basketModel3;
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
        editcart=findViewById(R.id.edit_cart);
        details_title = findViewById(R.id.details_title);
        plus_circle = findViewById(R.id.add_circle);
        minus_circle = findViewById(R.id.remove_circle);
        counter = findViewById(R.id.counter);
        shopping_cart = findViewById(R.id.shopping_cart);
        cart_num = findViewById(R.id.cart_num);
        addcart = findViewById(R.id.add_cart);
        c_red=findViewById(R.id.checkbox_red);
        c_blue=findViewById(R.id.checkbox_blue);
        c_black=findViewById(R.id.checkbox_black);
        order_num=findViewById(R.id.order_num);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"productdb").allowMainThreadQueries().build();
        //details_des=findViewById(R.id.details_des);
        details_price = findViewById(R.id.details_price);
        Intent intent = getIntent();
        Bundle extras = getIntent().getExtras();
        final int[] image = extras.getIntArray("homeimage");
        final int first_item=image[0];
        viewPager=findViewById(R.id.viewpager);
        customSwipeAdapter=new CustomSwipeAdapter(image,this);
        viewPager.setAdapter(customSwipeAdapter);
        title = intent.getStringExtra("title");
        String des = intent.getStringExtra("des");
        String price = intent.getStringExtra("price");
        details_title.setText(title);
        fab_favorite = findViewById(R.id.fab_favorite);
        details_price.setText(price);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        plus_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = Integer.parseInt(counter.getText().toString());
                count++;
                counter.setText(count++ + "");
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
                    try {
                        id=Integer.parseInt(order_num.getText().toString());
                        basketModel=new BasketModel(id,title,counter.getText().toString(),red,blue,black,first_item);
                        myAppDatabase.dao().addproduct(basketModel);
                    }catch (Exception e){
                        Toast.makeText(DetailsActivity.this, "order number took before", Toast.LENGTH_SHORT).show();
                    }
                }
        });
        editcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(DetailsActivity.this, "data updated successfully", Toast.LENGTH_LONG).show();
                    id=Integer.parseInt(order_num.getText().toString());
                    basketModel = new BasketModel(id,title, counter.getText().toString(), red, blue, black, first_item);
                     Log.e("ID",basketModel.getId()+"");
                     myAppDatabase.dao().editproduct(basketModel);

            }
        });
        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailsActivity.this,Basket_Activity.class);
                 startActivity(intent);
            }
        });
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
