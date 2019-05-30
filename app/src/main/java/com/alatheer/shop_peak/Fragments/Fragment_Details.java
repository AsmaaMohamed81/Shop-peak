package com.alatheer.shop_peak.Fragments;

import android.animation.Animator;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.Basket_Activity;
import com.alatheer.shop_peak.Activities.DetailsActivity;
import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Adapter.PassData;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.util.CircleAnimationUtil;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Fragment_Details extends Fragment {
    ImageView details_img, back_image, plus_circle, minus_circle, shopping_cart;
    TextView details_title, details_des, counter, cart_num, tv_not_budget;
    CheckBox c_red, c_blue, c_black;
    Button details_price, addcart, editcart;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    ViewPager viewPager;
    List<Item> items;
    CustomSwipeAdapter customSwipeAdapter;
    FloatingActionButton fab_favorite;
    RatingBar ratingBar;
    boolean flag = true;
    boolean red = false;
    boolean blue = false;
    boolean black = false;
    int count;
    int id;
    EditText order_num;
    BasketModel basketModel;
    String[] image;
    String first_item;
    String price;
    String des;
    String title;
    String gender;
    FrameLayout destView;
    String first_item_String;
    PassData passData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__details, container, false);
        initview(view);
        return view;
    }
    private void initview(View view) {
        back_image = view.findViewById(R.id.back_image);
        details_img = view.findViewById(R.id.details_image);
        ratingBar = view.findViewById(R.id.ratbar2);
        viewPager = view.findViewById(R.id.viewpager);
        details_title = view.findViewById(R.id.details_title);
        plus_circle = view.findViewById(R.id.add_circle);
        minus_circle = view.findViewById(R.id.remove_circle);
        counter = view.findViewById(R.id.counter);
        shopping_cart = view.findViewById(R.id.shopping_cart);
        cart_num = view.findViewById(R.id.cart_num);
        addcart = view.findViewById(R.id.add_cart);
        c_red = view.findViewById(R.id.checkbox_red);
        c_blue = view.findViewById(R.id.checkbox_blue);
        c_black = view.findViewById(R.id.checkbox_black);
        destView = (FrameLayout) view.findViewById(R.id.frame_cart);
        order_num = view.findViewById(R.id.order_num);
        //details_des=findViewById(R.id.details_des);
        details_price = view.findViewById(R.id.details_price);
        fab_favorite = view.findViewById(R.id.fab_favorite);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "productdb").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(), Favorite_Database.class, "favoritedb").allowMainThreadQueries().build();
        getDataFromIntent();

        tv_not_budget = view.findViewById(R.id.tv_not_budget);



       /* back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });*/
        c_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (c_red.isChecked()) {
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
                if (c_blue.isChecked()) {
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
                if (c_black.isChecked()) {
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
                    //makeFlyAnimation(viewPager);
                    id = Integer.parseInt(order_num.getText().toString());
                    basketModel = new BasketModel(id, title, counter.getText().toString(), gender, price, des, red, blue, black, first_item);
                    myAppDatabase.dao().addproduct(basketModel);
                    //passData.getBasketModel(basketModel);
                    /*if (myAppDatabase.dao().getdata().size() > 0) {
                        tv_not_budget.setText(String.valueOf(myAppDatabase.dao().getdata().size()));
                    } else {
                        tv_not_budget.setText("0");

                    }*/
                } catch (Exception e) {
                    Log.v("aaaaa", e.getMessage());
                }


            }
        });

        /*shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Basket_Activity.class);
                startActivity(intent);
                Animatoo.animateDiagonal(getActivity());
            }
        });*/
        fab_favorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int id2 = Integer.parseInt(order_num.getText().toString());
                basketModel = new BasketModel(id2, title, counter.getText().toString(), gender, price, des, red, blue, black, first_item);
                if (flag) {
                    id = (int) favorite_database.dao_favorite().add_favorite(basketModel);
                    Toast.makeText(getActivity(), "id" + id, Toast.LENGTH_SHORT).show();
                    Log.e("add_to_favorite", "true");
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_sold));
                    flag = false;
                } else if (!flag) {
                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
                    BasketModel basketModel2 = new BasketModel();
                    basketModel2.setId((int) id);
                    favorite_database.dao_favorite().delete_favorite(basketModel2);
                    Log.e("delete_from_favorite", "true");
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
                    int stars = (int) starsf + 1;
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
            }
        });

        /*if (myAppDatabase.dao().getdata().size() > 0) {
            tv_not_budget.setText(String.valueOf(myAppDatabase.dao().getdata().size()));
        } else {
            tv_not_budget.setText("0");

        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PassData) {
            passData = (PassData) context;
        }
    }

    public void getDataFromIntent() {
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        image = extras.getStringArray("homeimage");
        items = (List<Item>) extras.getSerializable("itemlist");
        //first_item_String = first_item.toString();
        title = intent.getStringExtra("title");
        des = intent.getStringExtra("des");
        price = intent.getStringExtra("price");
        gender = intent.getStringExtra("gender");
        // Bundle bundle = new Bundle();
        //bundle.putString("title",title);
        //bundle.putString("des",title);
        //bundle.putString("price",title);
        //bundle.putString("gender",title);

        //details_price.setText(price);
        //details_title.setText(title);
        customSwipeAdapter = new CustomSwipeAdapter(image, getActivity());
        viewPager.setAdapter(customSwipeAdapter);
    }

    private void makeFlyAnimation(ViewPager targetView) {
        new CircleAnimationUtil().attachActivity(getActivity()).setTargetView(targetView).setMoveDuration(1000).setDestView(destView).setAnimationListener(new Animator.AnimatorListener() {
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
