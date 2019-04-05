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
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Login_Activity extends AppCompatActivity {
    EditText edt_name, edt_password;
    Button Sign_up, log_in,facebook_login,gmail_login;
    private String userName, passWord;
    private ProgressDialog dialog;
    private CheckBox checkBox;
    private Boolean accepted = false;
    private View root;
    private Snackbar snackbar;
    private CallbackManager callbackManager;
    public GoogleSignInOptions gso;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();


    }

    private void initView() {

        checkBox = findViewById(R.id.check_box);
        Sign_up = findViewById(R.id.btn_sign);
        edt_name = findViewById(R.id.user_name);
        edt_password = findViewById(R.id.user_password);
        log_in = findViewById(R.id.btn_login);
        root=findViewById(R.id.root);
        facebook_login=findViewById(R.id.btn_facebook_login);
        //gmail_login=findViewById(R.id.btn_gmail_login);
        callbackManager=CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
             startActivity(new Intent(Login_Activity.this,MainActivity.class));
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
        /*gmail_login.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent signin=googleSignInClient.getSignInIntent();
                startActivityForResult(signin,0);
            }
        });*/

        final Animation animation=AnimationUtils.loadAnimation(this,R.anim.press_anim);
        final Animation animation2=AnimationUtils.loadAnimation(this,R.anim.fade_in);


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

        Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_up.clearAnimation();
                Sign_up.setAnimation(animation2);


                        Intent intent =new Intent(Login_Activity.this, Signup_Activity.class);
                        startActivity(intent);


            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                log_in.clearAnimation();
                log_in.setAnimation(animation);
                validation();
            }
        });
        CreateProgressDialog();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
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

        if (!TextUtils.isEmpty(userName) &&
                !TextUtils.isEmpty(passWord) &&
                accepted) {

            Common.CloseKeyBoard(this, edt_name);
            edt_name.setError(null);
            edt_password.setError(null);
            edt_password.setError(null);
            Login(userName, passWord);


        } else {

            if (TextUtils.isEmpty(userName)){
                edt_name.setError(getString(R.string.name_req));
            }else {
                edt_name.setError(null);
            }

            if (TextUtils.isEmpty(passWord)){
                edt_password.setError(getString(R.string.pass_req));
            }else {
                edt_password.setError(null);
            }


            if (!accepted){

              CreateSnackBar(getString(R.string.accept_terms));

            }


        }
    }

    private void Login(String userName, String passWord) {


        Intent intent = new Intent(Login_Activity.this, MainActivity.class);
        startActivity(intent);
    }

    public void CreateSnackBar(String msg)
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
