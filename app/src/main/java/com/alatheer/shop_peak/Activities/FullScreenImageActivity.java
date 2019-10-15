package com.alatheer.shop_peak.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.alatheer.shop_peak.Fragments.ProfileFragment;
import com.alatheer.shop_peak.Model.SellerSearch;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FullScreenImageActivity extends AppCompatActivity {
     FrameLayout full_screen_image;
     Toolbar toolbar;
     SellerSearch sellerSearch;
     ProfileFragment profileFragment;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        getDataIntent();
        initview();
    }

    private void getDataIntent() {
        sellerSearch = (SellerSearch) getIntent().getSerializableExtra("obj");
    }

    private void initview() {
        full_screen_image=findViewById(R.id.fragment_container);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(sellerSearch.getFull_name());
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (profileFragment==null)
        {
            profileFragment = profileFragment.getInstance();
        }

        Log.d("asmaa", "profilePos: "+sellerSearch);
        Bundle bundle =new Bundle();
        bundle.putString("name",sellerSearch.getFull_name());
        bundle.putString("image",sellerSearch.getStore_img());
        bundle.putString("id",sellerSearch.getId());
        bundle.putString("type","1");

        profileFragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,profileFragment).commit();
         //full_screen_image.setImageResource(img);

        }
    }
