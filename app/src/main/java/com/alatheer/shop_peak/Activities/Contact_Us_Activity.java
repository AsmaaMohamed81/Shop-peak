package com.alatheer.shop_peak.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Contact_Us_Activity extends AppCompatActivity {
    Toolbar toolbar;
    EditText contact_name,contact_email,contact_phone,contact_subject,contact_message;
    Button send;
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
        setContentView(R.layout.activity_contact__us_);
        mSong = null ;
        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.shop_peak_FCM-MESSAGE"));
        if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String msg = getIntent().getExtras().getString(key);

            }
        }
        initview();
    }

    private void initview() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contact_name=findViewById(R.id.contact_name);
        contact_email=findViewById(R.id.contact_email);
        contact_phone=findViewById(R.id.contact_phone);
        contact_subject=findViewById(R.id.contact_subject);
        contact_message=findViewById(R.id.contact_message);
        send=findViewById(R.id.contact_send);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.press_anim);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.clearAnimation();
                send.setAnimation(animation);
                validation();
            }
        });
    }

    private void validation() {
        String Name = contact_name.getText().toString();
        String Email = contact_email.getText().toString();
        String Phone=contact_phone.getText().toString();
        String Subject=contact_subject.getText().toString();
        String Message=contact_message.getText().toString();
        if (!TextUtils.isEmpty(Name) &&
                !TextUtils.isEmpty(Email) &&
                !TextUtils.isEmpty(Phone) &&
                !TextUtils.isEmpty(Subject) &&
                !TextUtils.isEmpty(Message)) {

            Common.CloseKeyBoard(this, contact_name);
            contact_name.setError(null);
            contact_email.setError(null);
            contact_phone.setError(null);
            contact_subject.setError(null);
            contact_message.setError(null);
            Send_Contact(Name, Email,Phone,Subject,Message);


        } else {

            if (TextUtils.isEmpty(Name)){
                contact_name.setError(getString(R.string.name_req));
            }else {
                contact_name.setError(null);
            }

            if (TextUtils.isEmpty(Email)){
                contact_email.setError(getString(R.string.email_req));
            }else {
                contact_email.setError(null);
            }
            if (TextUtils.isEmpty(Phone)){
                contact_phone.setError(getString(R.string.phone_req));
            }else {
                contact_phone.setError(null);
            }
            if (TextUtils.isEmpty(Subject)){
                contact_subject.setError(getString(R.string.subject_req));
            }else{
                contact_subject.setError(null);
            }
            if (TextUtils.isEmpty(Message)){
                contact_message.setError(getString(R.string.message_req));
            }else {
                contact_message.setError(null);
            }
        }
}

    private void Send_Contact(String name, String email, String phone, String subject, String message) {
        Toast.makeText(this, "data send completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        Animatoo.animateInAndOut(this);
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
        mSong = MediaPlayer.create(Contact_Us_Activity.this,R.raw.music);
        mSong.setLooping(true);
        mSong.start();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                //mSong.pause();
                //mSong.seekTo(0);
                stopPlaying();
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
