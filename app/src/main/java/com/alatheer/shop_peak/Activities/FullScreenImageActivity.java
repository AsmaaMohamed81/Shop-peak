package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

public class FullScreenImageActivity extends AppCompatActivity {
     ImageView full_screen_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        initview();
    }

    private void initview() {
        full_screen_image=findViewById(R.id.full_image);
        Intent intent=getIntent();
         int img=intent.getIntExtra("img",1);
         full_screen_image.setImageResource(img);

        }
    }
