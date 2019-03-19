package com.alatheer.shop_peak;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;

public class Login_Activity extends AppCompatActivity {
    ImageView check,check2;
    Button Sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        check=findViewById(R.id.check_img);
        check2=findViewById(R.id.check_img2);
        Sign_up=findViewById(R.id.btn_sign);
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
        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this, Signup_Activity.class));
            }
        });
        hideKeyboard(Login_Activity.this);
    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
