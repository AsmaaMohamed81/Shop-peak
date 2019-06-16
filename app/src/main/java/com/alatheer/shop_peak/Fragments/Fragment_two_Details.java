package com.alatheer.shop_peak.Fragments;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.CustomSwipeAdapter;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;

import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Fragment_two_Details extends Fragment {
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
    boolean red;
    boolean blue;
    boolean black;
    int count;
    int id_intent;
    String image_intent_String;
    String count_intent;
    String title_intent;
    String gender_intent;
    String price_intent;
    String des_intent;
    String image_intent;
    boolean red_intent;
    boolean blue_intent;
    boolean black_intent;


    @Override
    public void onAttach(Context context) {

        Paper.init(context);
        String lang = Paper.book().read("language");

        if (Paper.book().read("language").equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.AR_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        } else {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.EN_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        super.onAttach(CalligraphyContextWrapper.wrap(LanguageHelper.onAttach(context, lang)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_two__details, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        viewPager = view.findViewById(R.id.viewpager);
        back_image = view.findViewById(R.id.back_image);
        details_img = view.findViewById(R.id.details_image);
        ratingBar = view.findViewById(R.id.ratbar2);
        plus_circle = view.findViewById(R.id.add_circle);
        minus_circle = view.findViewById(R.id.remove_circle);
        counter = view.findViewById(R.id.counter);
        shopping_cart = view.findViewById(R.id.shopping_cart);
        cart_num = view.findViewById(R.id.cart_num);
        addcart = view.findViewById(R.id.add_cart);
        c_red = view.findViewById(R.id.checkbox_red);
        c_blue = view.findViewById(R.id.checkbox_blue);
        c_black = view.findViewById(R.id.checkbox_black);
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "productdb").allowMainThreadQueries().build();
        getDatafromIntent();
        /*back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

                //basketModel = new BasketModel(id_intent, title_intent, counter.getText().toString(), gender_intent, price_intent, des_intent, red, blue, black, image_intent);
                //myAppDatabase.dao().editproduct(basketModel);
            }
        });
        /*shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Basket_Activity.class);
                startActivity(intent);
            }
        });*/
    }

    public void getDatafromIntent() {
        Intent intent = getActivity().getIntent();
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
        red_intent = intent.getBooleanExtra("red", false);
        blue_intent = intent.getBooleanExtra("blue", false);
        black_intent = intent.getBooleanExtra("black", false);
        String[] images = {image_intent, image_intent};
        customSwipeAdapter = new CustomSwipeAdapter(images, getActivity());
        viewPager.setAdapter(customSwipeAdapter);
        counter.setText(count_intent);


        if (red_intent == true) {
            c_red.setButtonDrawable(R.drawable.ic_check);
            red = true;
        } else {
            c_red.setButtonDrawable(R.drawable.ic_check_gray);
            red = false;
        }
        if (blue_intent == true) {
            c_blue.setButtonDrawable(R.drawable.ic_check);
            blue = true;
        } else {
            c_blue.setButtonDrawable(R.drawable.ic_check_gray);
            blue = false;
        }
        if (black_intent == true) {
            c_black.setButtonDrawable(R.drawable.ic_check);
            black = true;
        } else {
            c_black.setButtonDrawable(R.drawable.ic_check_gray);
            black = false;
        }
    }
}
