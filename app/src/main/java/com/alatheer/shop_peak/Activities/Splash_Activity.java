package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.alatheer.shop_peak.R;

public class Splash_Activity extends AppCompatActivity {
    ImageView logo;
    SharedPreferences mPrefs;
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
        },200);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPrefs = getSharedPreferences("user_data", MODE_PRIVATE);
                        String name=mPrefs.getString("name",null);
                        String password=mPrefs.getString("password",null);
                        Toast.makeText(Splash_Activity.this, "name"+password, Toast.LENGTH_SHORT).show();
                        if(name == null && password==null){
                            Intent intent=new Intent(Splash_Activity.this,Login_Activity.class);
                            startActivity(intent);
                        }else {
                            Intent intent=new Intent(Splash_Activity.this,MainActivity.class);
                            intent.putExtra("personName",name);
                            intent.putExtra("image_url",password);
                            startActivity(intent);
                        }


                    }
                },500);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }


}
