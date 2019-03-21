package com.alatheer.shop_peak.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;

public class Signup_Activity extends AppCompatActivity {
     ImageView check,check2;
     EditText edt_name,edt_email,edt_phone,edt_password;
    private CheckBox checkBox;
    private ProgressDialog dialog;
     Button sign_up;
    private Boolean accepted = false;
    private String userName, passWord,Phone,Email;
    private View root;
    private Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initview();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();

            }
        });

    }

    private void initview() {
        edt_name=findViewById(R.id.user_name);
        edt_password=findViewById(R.id.user_password);
        edt_email=findViewById(R.id.user_email);
        edt_phone=findViewById(R.id.user_phone);
        sign_up=findViewById(R.id.btn_sign);
        root=findViewById(R.id.root);
        checkBox=findViewById(R.id.check_box);
        final Animation animation2= AnimationUtils.loadAnimation(this,R.anim.fade_in);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    accepted = true;
                } else {
                    accepted = false;
                }
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign_up.clearAnimation();
                sign_up.setAnimation(animation2);
                validation();
            }
        });
        CreateProgressDialog();
    }

    private void CreateProgressDialog() {
        dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.setMessage(getString(R.string.waitt));
        ProgressBar bar = new ProgressBar(this);
        Drawable drawable = bar.getIndeterminateDrawable().mutate();
        drawable.setColorFilter(ContextCompat.getColor(this, R.color.colorPrimary), PorterDuff.Mode.SRC_IN);
        dialog.setIndeterminateDrawable(drawable);
    }


    private void validation() {
        userName = edt_name.getText().toString();
        passWord = edt_password.getText().toString();
        Email = edt_email.getText().toString();
        Phone = edt_phone.getText().toString();
        if (!TextUtils.isEmpty(userName) &&
                !TextUtils.isEmpty(passWord) &&
                !TextUtils.isEmpty(Email) &&
                !TextUtils.isEmpty(Phone) &&
                accepted) {

            Common.CloseKeyBoard(this, edt_name);
            edt_name.setError(null);
            edt_password.setError(null);
            edt_password.setError(null);
            Signup(userName, passWord, Email, Phone);

        } else {

            if (TextUtils.isEmpty(userName)) {
                edt_name.setError(getString(R.string.name_req));
            } else {
                edt_name.setError(null);
            }

            if (TextUtils.isEmpty(passWord)) {
                edt_password.setError(getString(R.string.pass_req));
            } else {
                edt_password.setError(null);
            }
            if (TextUtils.isEmpty(Email)) {
                edt_password.setError(getString(R.string.pass_req));
            } else {
                edt_password.setError(null);
            }
            if (TextUtils.isEmpty(Phone)) {
                edt_password.setError(getString(R.string.pass_req));
            } else {
                edt_password.setError(null);
            }


            if (!accepted) {

                CreateSnackBar(getString(R.string.accept_terms));

            }
        }
    }
    private void Signup(String userName, String passWord,String email,String phone) {
        Intent intent = new Intent(Signup_Activity.this, MainActivity.class);
        startActivity(intent);
    }

    private void CreateSnackBar(String msg)
    {
        snackbar = Common.CreateSnackBar(this,root,msg);
        snackbar.show();
    }

    public void dismissSnackBar()
    {
        if (snackbar!=null)
        {
            snackbar.dismiss();

        }
    }
}