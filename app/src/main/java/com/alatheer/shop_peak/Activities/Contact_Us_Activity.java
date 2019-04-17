package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;

public class Contact_Us_Activity extends AppCompatActivity {
    android.support.v7.widget.Toolbar toolbar;
    EditText contact_name,contact_email,contact_phone,contact_subject,contact_message;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact__us_);
        initview();
    }

    private void initview() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        contact_name=findViewById(R.id.contact_name);
        contact_email=findViewById(R.id.contact_email);
        contact_phone=findViewById(R.id.contact_phone);
        contact_subject=findViewById(R.id.contact_subject);
        contact_message=findViewById(R.id.contact_message);
        send=findViewById(R.id.contact_send);
        final Animation animation= AnimationUtils.loadAnimation(this,R.anim.press_anim);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send.clearAnimation();
                send.setAnimation(animation);
                validation();
            }
        });
    }

    private void validation() {
       String Name = contact_name.getText().toString();
       String Email = contact_email.getText().toString();
       String Phone=contact_phone.getText().toString();
       String Subject=contact_subject.getText().toString();
       String Message=contact_message.getText().toString();
        if (!TextUtils.isEmpty(Name) &&
                !TextUtils.isEmpty(Email) &&
                !TextUtils.isEmpty(Phone) &&
                !TextUtils.isEmpty(Subject) &&
        !TextUtils.isEmpty(Message)) {

            Common.CloseKeyBoard(this, contact_name);
            contact_name.setError(null);
            contact_email.setError(null);
            contact_phone.setError(null);
            contact_subject.setError(null);
            contact_message.setError(null);
            Send_Contact(Name, Email,Phone,Subject,Message);


        } else {

            if (TextUtils.isEmpty(Name)){
                contact_name.setError(getString(R.string.name_req));
            }else {
                contact_name.setError(null);
            }

            if (TextUtils.isEmpty(Email)){
                contact_email.setError(getString(R.string.email_req));
            }else {
                contact_email.setError(null);
            }
            if (TextUtils.isEmpty(Phone)){
                contact_phone.setError(getString(R.string.phone_req));
            }else {
                contact_phone.setError(null);
            }
            if (TextUtils.isEmpty(Subject)){
                contact_subject.setError(getString(R.string.subject_req));
            }else{
                contact_subject.setError(null);
            }
            if (TextUtils.isEmpty(Message)){
                contact_message.setError(getString(R.string.message_req));
            }else {
                contact_message.setError(null);
            }
    }
}

    private void Send_Contact(String name, String email, String phone, String subject, String message) {
        Toast.makeText(this, "data send completed", Toast.LENGTH_SHORT).show();
    }
    }
