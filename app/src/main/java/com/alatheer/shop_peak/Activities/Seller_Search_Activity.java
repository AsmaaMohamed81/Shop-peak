package com.alatheer.shop_peak.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alatheer.shop_peak.Adapter.FollowersAdapter;
import com.alatheer.shop_peak.Adapter.Seller_Search_Adapter;
import com.alatheer.shop_peak.Fragments.HomeFragment;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.SellerSearch;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Seller_Search_Activity extends AppCompatActivity {
    RecyclerView recycler;
    Seller_Search_Adapter seller_search_adapter;
    private List<SellerSearch> SellerSearchList;
    HomeFragment homeFragment;
    Toolbar toolbar;
    String title;

    String store_id;

    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        String lang = Paper.book().read("language");
        if (Paper.book().read("language").equals("ar"))
        {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.AR_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        }else
        {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.EN_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }

        super.attachBaseContext(CalligraphyContextWrapper.wrap(LanguageHelper.onAttach(newBase, lang)));



    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        getDataFromIntent();
        initview();

    }

    private void initview() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getDataFromIntent();
        recycler = findViewById(R.id.notification_recycler);
        initrecycler();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void getDataFromIntent() {

        Intent intent=getIntent();

        if (intent!=null){

            homeFragment=new HomeFragment();
            title = intent.getStringExtra("sanf_name");
            SellerSearchList = (List<SellerSearch>) intent.getExtras().getSerializable("list");

        }
    }

    private void initrecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        seller_search_adapter = new Seller_Search_Adapter(SellerSearchList, this);
        recycler.setAdapter(seller_search_adapter);

       // get_storefollow(store_id);
    }


}
