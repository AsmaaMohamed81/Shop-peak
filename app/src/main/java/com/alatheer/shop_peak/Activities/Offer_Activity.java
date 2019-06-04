package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

public class Offer_Activity extends AppCompatActivity {
    TextView title;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_);
        initview();
    }

    private void initview() {
        title = findViewById(R.id.offer_title);
        image = findViewById(R.id.offer_image);
        Intent i =getIntent();
        String offer_title = i.getStringExtra("title");
        String offer_image = i.getStringExtra("image");
        title.setText(offer_title);
        Picasso.with(this).load(offer_image).into(image);
    }
}
