package com.alatheer.shop_peak.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.OnlineModel;
import com.alatheer.shop_peak.Model.RatingModel;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.squareup.picasso.Picasso;

public class Delivery_Profile extends AppCompatActivity {
    CircleImageView delivery_image;
    EditText user_name;
    Button online;
    MySharedPreference mprefs;
    UserModel1 userModel1;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery__profile);
        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.shop_peak_FCM-MESSAGE"));
        mSong = null;

        if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String msg = getIntent().getExtras().getString(key);

            }
        }
        initview();
    }
    private void initview() {
        delivery_image = findViewById(R.id.img_profile);
        user_name = findViewById(R.id.user_name);
        online = findViewById(R.id.online);
        mprefs = MySharedPreference.getInstance();
        userModel1 = mprefs.Get_UserData(this);
        String active = userModel1.getActive();
        Log.e("llll",active);
        if(active.equals("1")){
            online.setBackgroundResource(R.drawable.btn_accept);
            online.setText("Online");

        }else{
            online.setBackgroundResource(R.drawable.btn_cancel);
            online.setText("Offline");
            mprefs.Create_Update_UserData(this,userModel1);
        }


        String Logo_img = userModel1.getLogo_img();
        if (Logo_img.equals("https://shop-peak.com/uploads/images/")) {
            delivery_image.setImageResource(R.mipmap.icon_round);

        } else {
            Picasso.with(this).load(Logo_img).into(delivery_image);
        }
        user_name.setText(userModel1.getFull_name());
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                make_online();
            }
        });
    }

    private void make_online() {
        Api.getService().online_offline(userModel1.getId()).enqueue(new Callback<OnlineModel>() {
            @Override
            public void onResponse(Call<OnlineModel> call, Response<OnlineModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSucces() == 1) {
                        online.setBackgroundResource(R.drawable.btn_accept);
                        online.setText("Online");
                        userModel1.setActive("1");
                        mprefs.Create_Update_UserData(Delivery_Profile.this,userModel1);
                    } else {
                        online.setBackgroundResource(R.drawable.btn_cancel);
                        online.setText("Offline");
                        userModel1.setActive("0");
                        mprefs.Create_Update_UserData(Delivery_Profile.this,userModel1);
                    }
                }
            }

            @Override
            public void onFailure(Call<OnlineModel> call, Throwable t) {

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
        stopPlaying();
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(message);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        mSong = MediaPlayer.create(Delivery_Profile.this,R.raw.music);
        mSong.setLooping(true);
        mSong.start();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                stopPlaying();
                //mSong.pause();
                //mSong.seekTo(0);
            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }

    private void stopPlaying() {
        if(mSong != null){
            mSong.stop();
            mSong.release();
            mSong = null;
        }
    }
}
