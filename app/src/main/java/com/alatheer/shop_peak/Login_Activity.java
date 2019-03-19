package com.alatheer.shop_peak;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity {
    ImageView check,check2;
    EditText name,password;
    Button Sign_up,log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        check=findViewById(R.id.check_img);
        check2=findViewById(R.id.check_img2);
        Sign_up=findViewById(R.id.btn_sign);
        name=findViewById(R.id.user_name);
        password=findViewById(R.id.user_password);
        log_in=findViewById(R.id.btn_login);
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
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();
            }
        });
        hideKeyboard(Login_Activity.this);
    }

    private void validation() {
        if(name.getText().toString().isEmpty()||password.getText().toString().isEmpty()){
            Toast.makeText(this, "all data field are required", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(password.getText().toString().length()<=8){
            Toast.makeText(this, "password is short", Toast.LENGTH_SHORT).show();
            return;
        }
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
