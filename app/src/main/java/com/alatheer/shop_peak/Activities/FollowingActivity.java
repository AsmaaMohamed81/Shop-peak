package com.alatheer.shop_peak.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.alatheer.shop_peak.Adapter.FollowersAdapter;
import com.alatheer.shop_peak.Model.Follow_Vender;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class FollowingActivity extends AppCompatActivity {
    RecyclerView recycler;
    Toolbar toolbar;
    FollowersAdapter followersAdapter;
    private List<Follow_Vender> userModel1ArrayList;

    String store_id,type,user_id_fk;

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
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        getDataFromIntent();
        initview();

    }

    private void initview() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.following));
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycler = findViewById(R.id.notification_recycler);
        userModel1ArrayList=new ArrayList<>();
        initrecycler();


    }

    private void getDataFromIntent() {

        Intent intent=getIntent();

        if (intent!=null){

            type=intent.getStringExtra("type");
            user_id_fk= intent.getStringExtra("user_id_fk");

        }
    }

    private void initrecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setHasFixedSize(true);
        followersAdapter = new FollowersAdapter(userModel1ArrayList, this);
        recycler.setAdapter(followersAdapter);

        getfollow(user_id_fk,type);
    }

    private void getfollow(String user_id_fk,String type) {

        Api.getService()
                .get_follow(user_id_fk,type)
                .enqueue(new Callback<List<Follow_Vender>>() {
                    @Override
                    public void onResponse(Call<List<Follow_Vender>> call, Response<List<Follow_Vender>> response) {
                        if (response.isSuccessful()){

                            if (response.body().size()>0){

                                userModel1ArrayList.addAll(response.body());
                                followersAdapter.notifyDataSetChanged();

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Follow_Vender>> call, Throwable t) {

                    }
                });
    }

}
