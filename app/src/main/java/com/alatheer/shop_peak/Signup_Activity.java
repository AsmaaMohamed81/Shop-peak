package com.alatheer.shop_peak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Signup_Activity extends AppCompatActivity {
     ImageView check,check2;
     EditText name,email,phone,password;
     Button sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.user_name);
        password=findViewById(R.id.user_password);
        email=findViewById(R.id.user_email);
        phone=findViewById(R.id.user_phone);
        check=findViewById(R.id.check_img);
        check2=findViewById(R.id.check_img2);
        sign_up=findViewById(R.id.btn_sign);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validation();

            }
        });
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

    private void validation() {
        if(name.getText().toString().isEmpty()||password.getText().toString().isEmpty()||email.getText().toString().isEmpty()
                ||phone.getText().toString().isEmpty()){
            Toast.makeText(this,"all data field are required",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(password.getText().toString().length()<=8){
            Toast.makeText(this, "password is short", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!Patterns.PHONE.matcher(phone.getText().toString()).matches()){
            Toast.makeText(this, "phone must be number", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            Toast.makeText(this, "you must enter email", Toast.LENGTH_SHORT).show();
            return;
        }
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
