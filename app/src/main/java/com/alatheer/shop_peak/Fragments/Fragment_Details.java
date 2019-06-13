package com.alatheer.shop_peak.Fragments;

import android.animation.Animator;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Adapter.ColorAdapter;
import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Adapter.PassData;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.alatheer.shop_peak.util.CircleAnimationUtil;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;


public class Fragment_Details extends Fragment {
    ImageView details_img, back_image, plus_circle, minus_circle, shopping_cart;
    TextView details_title, details_des, counter, cart_num, tv_not_budget,txt_before_discount;
    CheckBox c_red, c_blue, c_black;
    Button details_price, addcart, editcart;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    ViewPager viewPager;
    List<Item> items;
    String[]colors;
    CustomSwipeAdapter customSwipeAdapter;
    FloatingActionButton fab_favorite,fab_shopping;
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
    String rating;
    String sanf_name;
    String price_before_dis;
    FrameLayout destView;
    String first_item_String;
    PassData passData;
    String sanf_id,store_id,sanf_image;
    List<OrderItemList> orders_List;
    RecyclerView color_recycler;
    RecyclerView.LayoutManager layoutManager;
    ColorAdapter colorAdapter ;

    private MySharedPreference mySharedPreference;

    private UserModel1 userModel1;
    private String User_id;
    private String like;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__details, container, false);
        initview(view);
        return view;
    }
    private void initview(View view) {

        mySharedPreference=MySharedPreference.getInstance();
        userModel1=mySharedPreference.Get_UserData(getActivity());

        if (userModel1!=null){

            User_id=userModel1.getId();
        }



        back_image = view.findViewById(R.id.back_image);
        details_img = view.findViewById(R.id.details_image);
        ratingBar = view.findViewById(R.id.ratbar2);
        viewPager = view.findViewById(R.id.viewpager);
        color_recycler = view.findViewById(R.id.recycler_color);
        details_title = view.findViewById(R.id.details_title);
        plus_circle = view.findViewById(R.id.add_circle);
        minus_circle = view.findViewById(R.id.remove_circle);
        counter = view.findViewById(R.id.counter);
        shopping_cart = view.findViewById(R.id.shopping_cart);
        //fab_shopping = view.findViewById(R.id.fab_shopping_cart);
        cart_num = view.findViewById(R.id.cart_num);
        addcart = view.findViewById(R.id.add_cart);
        txt_before_discount = view.findViewById(R.id.txt_price_before_discount);
        //c_red = view.findViewById(R.id.checkbox_red);
        //_blue = view.findViewById(R.id.checkbox_blue);
        //c_black = view.findViewById(R.id.checkbox_black);
        destView = (FrameLayout) view.findViewById(R.id.frame_cart);
        order_num = view.findViewById(R.id.order_num);
        //details_des=findViewById(R.id.details_des);
        details_price = view.findViewById(R.id.details_price);
        fab_favorite = view.findViewById(R.id.fab_favorite);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "myorders_db").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(), Favorite_Database.class, "favoritedb").allowMainThreadQueries().build();
        getDataFromIntent();


        txt_before_discount.setPaintFlags(txt_before_discount.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        txt_before_discount.setText(price_before_dis);
        Log.v("color",colors.length+"");
        init_color_recycler();

        tv_not_budget = view.findViewById(R.id.tv_not_budget);

        details_price.setText(price + "" + "LE");
        customSwipeAdapter = new CustomSwipeAdapter(image, getActivity());
        viewPager.setAdapter(customSwipeAdapter);
       /* back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });*/

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
                    OrderItemList orderItemList = new OrderItemList();
                    orderItemList.withSanfAmount(counter.getText().toString());
                    orderItemList.withSanfIdFk(sanf_id);
                    orderItemList.withSanfPrice(price);
                    orderItemList.withStoreIdFk(store_id);
                    orderItemList.setSanfImage(image[0]);
                    orderItemList.setSanfTitle(sanf_name);
                    myAppDatabase.dao().addproduct(orderItemList);
                    Toast.makeText(getActivity(), "data added successfully", Toast.LENGTH_SHORT).show();


                } catch (Exception e) {
                    OrderItemList orderItemList = new OrderItemList();
                    orderItemList.withSanfAmount(counter.getText().toString());
                    orderItemList.withSanfIdFk(sanf_id);
                    orderItemList.withSanfPrice(price);
                    orderItemList.withStoreIdFk(store_id);
                    orderItemList.setSanfImage(image[0]);
                    orderItemList.setSanfTitle(sanf_name);
                    myAppDatabase.dao().editproduct(orderItemList);
                    Log.v("aaaaa", e.getMessage());

                }


            }
        });
        shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Basket_Activity.class);
                startActivity(intent);
                Animatoo.animateDiagonal(getActivity());
            }
        });

        /*if (myAppDatabase.dao().getdata().size() > 0) {
            tv_not_budget.setText(String.valueOf(myAppDatabase.dao().getdata().size()));
        } else {
            tv_not_budget.setText("0");

        }*/



        fab_favorite.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                int id2 = Integer.parseInt(order_num.getText().toString());


                basketModel = new BasketModel(id2, title, counter.getText().toString(), gender, price, des, red, blue, black, first_item);

                final ProgressDialog dialog = Common.createProgressDialog(getActivity(),getString(R.string.waitt));
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();


                if (flag) {

                    Api.getService()
                            .add_to_favourite(User_id,sanf_id)
                            .enqueue(new Callback<RatingModel2>() {
                                @Override
                                public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                                    if (response.isSuccessful()){
                                        dialog.dismiss();

                                        if (response.body().getSuccess() == 1) {

                                            Toast.makeText(getActivity(), R.string.addfav, Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                }

                                @Override
                                public void onFailure(Call<RatingModel2> call, Throwable t) {

                                    dialog.dismiss();
                                }
                            });

                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_sold));
                    flag = false;
                } else if (!flag) {


                    Api.getService()
                            .delet_to_favourite(User_id,sanf_id)
                            .enqueue(new Callback<RatingModel2>() {
                                @Override
                                public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                                    if (response.isSuccessful()){
                                        dialog.dismiss();

                                        if (response.body().getSuccess() == 1) {

                                            Toast.makeText(getActivity(), R.string.deletfav, Toast.LENGTH_SHORT).show();
                                        }


                                    }
                                }

                                @Override
                                public void onFailure(Call<RatingModel2> call, Throwable t) {
                                    dialog.dismiss();

                                }
                            });


                    fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));




                    flag = true;
                }
            }
        });
        ratingBar.setRating(Float.parseFloat(rating));



        if (like.equals("1")){
        fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_sold));
        flag = false;
        }else {

            fab_favorite.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite));
            flag = true;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PassData) {
            passData = (PassData) context;
        }
    }
    public  void  init_color_recycler(){
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        color_recycler.setHasFixedSize(true);
        color_recycler.setLayoutManager(layoutManager);
        ColorAdapter colorAdapter =new ColorAdapter(getActivity(),colors);
        color_recycler.setAdapter(colorAdapter);
    }

    public void getDataFromIntent() {
        Intent intent = getActivity().getIntent();
        Bundle extras = intent.getExtras();
        image = extras.getStringArray("homeimage");
        items = (List<Item>) extras.getSerializable("itemlist");
        colors = intent.getStringArrayExtra("color");
        //first_item_String = first_item.toString();
        title = intent.getStringExtra("title");
        des = intent.getStringExtra("des");
        price = intent.getStringExtra("price");
        gender = intent.getStringExtra("gender");
        rating = intent.getStringExtra("rate");
        price =intent.getStringExtra("price");
        price_before_dis = intent.getStringExtra("price_before_dis");
        sanf_id = intent.getStringExtra("id");
        store_id =intent.getStringExtra("store_id");
        like =intent.getStringExtra("like");


        // Bundle bundle = new Bundle();
        //bundle.putString("title",title);
        //bundle.putString("des",title);
        //bundle.putString("price",title);
        //bundle.putString("gender",title);

        //details_price.setText(price);
        //details_title.setText(title);

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
