package com.alatheer.shop_peak;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash_Activity extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();
    }

    private void initView() {

        logo=findViewById(R.id.logo);


         final Animation animation= AnimationUtils.loadAnimation(this,R.anim.slide_in_left);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logo.setVisibility(View.VISIBLE);
                logo.startAnimation(animation);
            }
        },300);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Intent intent=new Intent(Splash_Activity.this,Login_Activity.class);
                        startActivity(intent);
                    }
                },200);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
