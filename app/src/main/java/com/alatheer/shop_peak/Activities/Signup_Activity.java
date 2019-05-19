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

import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class Signup_Activity extends AppCompatActivity {
    EditText edt_name2, edt_email, edt_phone, edt_password;
    private CheckBox checkBox;
    private ProgressDialog dialog;
    Button sign_up;
    private Boolean accepted = false;
    private String userName, passWord, Phone, Email;
    private View root;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initview();

    }

    private void initview() {
        edt_name2 = findViewById(R.id.user_name);
        edt_password = findViewById(R.id.user_password);
        edt_email = findViewById(R.id.user_email);
        edt_phone = findViewById(R.id.user_phone);
        sign_up = findViewById(R.id.btn_sign);
        root = findViewById(R.id.root);
        checkBox = findViewById(R.id.check_box);
        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.press_anim);
        Common.CloseKeyBoard(this, edt_name2);
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
                sign_up.setAnimation(animation);
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
        userName = edt_name2.getText().toString();
        passWord = edt_password.getText().toString();
        Email = edt_email.getText().toString();
        Phone = edt_phone.getText().toString();
        if (!TextUtils.isEmpty(userName) &&
                !TextUtils.isEmpty(passWord) &&
                !TextUtils.isEmpty(Email) &&
                !TextUtils.isEmpty(Phone) &&
                passWord.length() >= 8 &&
                android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches() &&
                accepted) {

            Common.CloseKeyBoard(this, edt_name2);
            edt_name2.setError(null);
            edt_password.setError(null);
            edt_email.setError(null);
            edt_phone.setError(null);
            Signup(userName, passWord, Email, Phone);

        } else {
            if (TextUtils.isEmpty(userName)) {
                edt_name2.setError(getString(R.string.name_req));
            } else {
                edt_name2.setError(null);
            }

            if (TextUtils.isEmpty(passWord)) {
                edt_password.setError(getString(R.string.pass_req));
            } else {
                edt_password.setError(null);
            }
            if (passWord.length() < 8) {
                edt_password.setError(getString(R.string.pass_len));
            } else {
                edt_password.setError(null);
            }
            if (TextUtils.isEmpty(Email)) {
                edt_password.setError(getString(R.string.pass_req));
            } else {
                edt_password.setError(null);
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                edt_email.setError(getString(R.string.email_patt));
            } else {
                edt_email.setError(null);
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

    private void Signup(String userName, String passWord, String email, String phone) {
        UserModel userModel = new UserModel(userName, "https://www.wpclipart.com/buildings/shop.png", email);
        MySharedPreference mprefs = new MySharedPreference(this);
        mprefs.Create_Update_UserData(Signup_Activity.this, userModel);

        Intent intent = new Intent(Signup_Activity.this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSwipeRight(Signup_Activity.this);
    }

    private void CreateSnackBar(String msg) {
        snackbar = Common.CreateSnackBar(this, root, msg);
        snackbar.show();
    }

    public void dismissSnackBar() {
        if (snackbar != null) {
            snackbar.dismiss();

        }
    }

}
