package com.alatheer.shop_peak.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

import com.alatheer.shop_peak.R;

public class Notification_Message extends AppCompatActivity {
    TextView message;
    MediaPlayer mSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification__message);
        //LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.shop_peak_FCM-MESSAGE"));
        message = findViewById(R.id.notification_message);
        String msg = getIntent().getStringExtra("message");
        message.setText(msg);
        mSong = MediaPlayer.create(Notification_Message.this,R.raw.shop);
        mSong.start();
        /*if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                if(key.equals("message"))
                    message.setText(getIntent().getExtras().getString(key));

            }
        }
    }
    private BroadcastReceiver mhandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
           String msg = intent.getStringExtra("message");
           message.setText(msg);
           showNotificationInADialog(msg);
        }
    };
    private void showNotificationInADialog(String message) {

        // show a dialog with the provided title and message
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }*/
    }
}