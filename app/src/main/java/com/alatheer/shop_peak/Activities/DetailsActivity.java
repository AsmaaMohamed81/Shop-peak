package com.alatheer.shop_peak.Activities;

import android.animation.Animator;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.util.CircleAnimationUtil;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    ImageView details_img,back_image,plus_circle,minus_circle,shopping_cart;
    TextView details_title,details_des,counter,cart_num,tv_not_budget;
    CheckBox c_red,c_blue,c_black;
    Button details_price,addcart,editcart;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    ViewPager viewPager;
    CustomSwipeAdapter customSwipeAdapter;
    FloatingActionButton fab_favorite;
    RatingBar ratingBar;
    boolean flag=true;
    boolean red=false;
    boolean blue=false;
    boolean black=false;
    int count;
    long id;
    EditText order_num;
    BasketModel basketModel;
    String[] image;
    String first_item;
    String price;
    String des;
    String title;
    String gender;
    String first_item_String;
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
        //details_des=findViewById(R.id.details_des);
        details_price = findViewById(R.id.details_price);
        fab_favorite = findViewById(R.id.fab_favorite);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"productdb").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(),Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
        getDataFromIntent();

        tv_not_budget = findViewById(R.id.tv_not_budget);



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
                    makeFlyAnimation(viewPager);
                    id=Integer.parseInt(order_num.getText().toString());
                    basketModel=new BasketModel((int) id,title,counter.getText().toString(),gender,price,des,red,blue,black,first_item);
                    myAppDatabase.dao().addproduct(basketModel);

                    if (myAppDatabase.dao().getdata().size() > 0) {
                        tv_not_budget.setText(String.valueOf(myAppDatabase.dao().getdata().size()));
                    } else {
                        tv_not_budget.setText("0");

                    }
                }catch (Exception e){
                    Toast.makeText(DetailsActivity.this, "order number took before", Toast.LENGTH_SHORT).show();
                }



            }
        });

        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailsActivity.this,Basket_Activity.class);
                startActivity(intent);
                Animatoo.animateDiagonal(DetailsActivity.this);
            }
        });
        fab_favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int id2=Integer.parseInt(order_num.getText().toString());
                basketModel=new BasketModel(id2,title,counter.getText().toString(),gender,price,des,red,blue,black,first_item);
                if (flag) {
                     id =favorite_database.dao_favorite().add_favorite(basketModel);
                    Toast.makeText(DetailsActivity.this, "id"+id, Toast.LENGTH_SHORT).show();
                    Log.e("add_to_favorite","true");
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_sold));
                    flag = false;
                } else if (!flag) {
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
                    BasketModel basketModel2=new BasketModel();
                    basketModel2.setId((int) id);
                    favorite_database.dao_favorite().delete_favorite(basketModel2);
                    Log.e("delete_from_favorite","true");
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

        if (myAppDatabase.dao().getdata().size() > 0) {
            tv_not_budget.setText(String.valueOf(myAppDatabase.dao().getdata().size()));
        } else {
            tv_not_budget.setText("0");

        }
    }

    public void getDataFromIntent(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        image = extras.getStringArray("homeimage");
        first_item=image[0];
         //first_item_String = first_item.toString();
        title = intent.getStringExtra("title");
        des = intent.getStringExtra("des");
        price = intent.getStringExtra("price");
        gender = intent.getStringExtra("gender");
        details_price.setText(price);
        details_title.setText(title);
        viewPager=findViewById(R.id.viewpager);
        customSwipeAdapter=new CustomSwipeAdapter(image,this);
        viewPager.setAdapter(customSwipeAdapter);
    }

    private void makeFlyAnimation(ViewPager targetView) {

        FrameLayout destView = (FrameLayout) findViewById(R.id.frame_cart);

        new CircleAnimationUtil().attachActivity(this).setTargetView(targetView).setMoveDuration(1000).setDestView(destView).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

//                CreateUserNotSignInAlertDialog(DetailsFoodActivity.this,getString(R.string.go_cart));
//                Toast.makeText(DetailsFoodActivity.this, "تم اضافه الاكله للسله بنجاح ...", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).startAnimation();


    }


}
