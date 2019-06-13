package com.alatheer.shop_peak.Activities;

import android.animation.Animator;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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

import com.alatheer.shop_peak.Adapter.ColorAdapter;
import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Adapter.PassData;
import com.alatheer.shop_peak.Fragments.Fragment_Details;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.OrderItemList;
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
    FrameLayout frameLayout;
    RatingBar ratingBar;
    boolean flag=true;
    boolean red=false;
    boolean blue=false;
    boolean black=false;
    String []colors;
    int count;
    long id;
    EditText order_num;
    BasketModel basketModel;
    String[] image;
    List<Item> items;
    String first_item;
    String price;
    String details;
    String title;
    String gender;
    String first_item_String;
    List<OrderItemList>listorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initview();
    }

    private void initview() {
        back_image = findViewById(R.id.back_image);
        //details_img = findViewById(R.id.details_image);
        //ratingBar = findViewById(R.id.ratbar2);
        details_title = findViewById(R.id.details_title);
        listorder =new ArrayList<>();

        //plus_circle = findViewById(R.id.add_circle);
        //minus_circle = findViewById(R.id.remove_circle);
        //counter = findViewById(R.id.counter);
        shopping_cart = findViewById(R.id.shopping_cart);
        //cart_num = findViewById(R.id.cart_num);
        addcart = findViewById(R.id.add_cart);
        //c_red=findViewById(R.id.checkbox_red);
        //c_blue=findViewById(R.id.checkbox_blue);
        //c_black=findViewById(R.id.checkbox_black);
        //order_num=findViewById(R.id.order_num);
        //details_des=findViewById(R.id.details_des);
        //details_price = findViewById(R.id.details_price);
        fab_favorite = findViewById(R.id.fab_favorite);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"order_db").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(),Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
        getDataFromIntent();
        details_title.setText(title);
        tv_not_budget = findViewById(R.id.tv_not_budget);
        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Description"));
        tabLayout.addTab(tabLayout.newTab().setText("Rating"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter pagerAdapter = new com.alatheer.shop_peak.Adapter.PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
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
                Intent intent=new Intent(DetailsActivity.this,Basket_Activity.class);
                startActivity(intent);
                Animatoo.animateDiagonal(DetailsActivity.this);

            }
        });

        if (myAppDatabase.dao().getdata().size() > 0) {
            tv_not_budget.setText(String.valueOf(myAppDatabase.dao().getdata().size()));
        } else {
            tv_not_budget.setText("0");

        }

    }
    public void init_color_recycler(){

    }

    public void getDataFromIntent(){
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        image = extras.getStringArray("homeimage");
        items = (List<Item>) extras.getSerializable("itemlist");
         //first_item_String = first_item.toString();
        title = intent.getStringExtra("title");
        details = intent.getStringExtra("details");
        price = intent.getStringExtra("price");
        gender = intent.getStringExtra("gender");
        colors = intent.getStringArrayExtra("color");
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("des", title);
        bundle.putString("price", title);
        bundle.putString("gender", title);
        bundle.putStringArray("homeimage", image);
        bundle.putSerializable("itemlist", (Serializable) items);
        Fragment_Details fragment_details = new Fragment_Details();
        fragment_details.setArguments(bundle);

        //details_price.setText(price);
        //details_title.setText(title);
        //viewPager=findViewById(R.id.viewpager);
        //customSwipeAdapter=new CustomSwipeAdapter(image,this);
        //viewPager.setAdapter(customSwipeAdapter);
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


    /*@Override
    public void getBasketModel(OrderItemList basketModel) {
        makeFlyAnimation(viewPager);
        myAppDatabase.dao().addproduct(basketModel);
        if (myAppDatabase.dao().getdata().size() > 0) {
            tv_not_budget.setText(String.valueOf(myAppDatabase.dao().getdata().size()));
        } else {
            tv_not_budget.setText("0");

        }

    }*/

    public void send(List<OrderItemList> orders_list) {

    }
}
