package com.alatheer.shop_peak.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
    MediaPlayer mSong;
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
        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.shop_peak_FCM-MESSAGE"));
        mSong = MediaPlayer.create(FollowingActivity.this,R.raw.music);

        if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String msg = getIntent().getExtras().getString(key);

            }
        }
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
    private BroadcastReceiver mhandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("message");
            //message.setText(msg);

            showNotificationInADialog(msg);
        }
    };
    private void showNotificationInADialog(final String message) {
        final android.app.AlertDialog gps_dialog = new android.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .create();

        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(message);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        mSong.start();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                mSong.pause();
                mSong.seekTo(0);
            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }

}
