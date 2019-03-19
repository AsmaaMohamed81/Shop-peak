package com.alatheer.shop_peak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SignupActivity extends AppCompatActivity {
     ImageView check,check2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        check=findViewById(R.id.check_img);
        check2=findViewById(R.id.check_img2);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
        check2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notcheck();

            }
        });
    }
    public void check() {
        check.setVisibility(View.GONE);
        check2.setVisibility(View.VISIBLE);
    }

    public void notcheck() {
        check2.setVisibility(View.GONE);
        check.setVisibility(View.VISIBLE);
    }
}
