package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

public class IntroActivity extends AppCompatActivity {

    SignInButton gmail_login;
    LoginButton facebook_login;

    Button btn_sign,btn_login;

    TextView sysa,skip;
    MySharedPreference mySharedPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        initView();

         mySharedPreference = new MySharedPreference(this);


        String session = mySharedPreference.getSession(this);
        if (!TextUtils.isEmpty(session)|| session!=null)
        {
            if (session.equals(Tags.session_login))
            {
                UserModel1 userModel1 = mySharedPreference.Get_UserData(this);

                if (userModel1!=null)
                {
                    Intent intent = new Intent(IntroActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

        }


    }

    private void initView() {

        gmail_login=findViewById(R.id.btn_gmail_login);
        facebook_login=findViewById(R.id.btn_facebook_login);
        btn_sign = findViewById(R.id.btn_sign);
        btn_login = findViewById(R.id.btn_login);
        sysa=findViewById(R.id.sysa);
        skip=findViewById(R.id.skip);

        skip.setPaintFlags(skip.getPaintFlags()| Paint.UNDERLINE_TEXT_FLAG);


        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IntroActivity.this, Signup_Activity.class);

                startActivity(intent);

            }
        });



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IntroActivity.this, Login_Activity.class);

                startActivity(intent);

            }
        });
        sysa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent  intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://shop-peak.com/Web/about/1"));
                startActivity(intent);

            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(IntroActivity.this, MainActivity.class);

                startActivity(intent);

            }
        });
    }
}
