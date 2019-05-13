package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class Vender_Signup_Activity extends AppCompatActivity {
    EditText shop_name, shop_email, governate, city, address, category;
    Button add_logo, signup;
    private String Name, Email, Governate, City, Address, Category;
    int PICK_IMAGE_REQUEST = 1 ;
    Uri filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vender__signup_);
        initview();
    }

    private void initview() {
        shop_name = findViewById(R.id.shop_name);
        shop_email = findViewById(R.id.shop_email);
        governate = findViewById(R.id.governate);
        city = findViewById(R.id.city);
        address = findViewById(R.id.address);
        category = findViewById(R.id.category);
        add_logo = findViewById(R.id.add_logo);
        signup = findViewById(R.id.btn_sign);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.press_anim);
        Common.CloseKeyBoard(this, shop_name);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup.clearAnimation();
                signup.setAnimation(animation);
                validation();
            }
        });
        add_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                Toast.makeText(Vender_Signup_Activity.this, "image added successfully", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void validation() {
        Name = shop_name.getText().toString();
        Email = shop_email.getText().toString();
        Governate = governate.getText().toString();
        City = governate.getText().toString();
        Address = address.getText().toString();
        Category = category.getText().toString();
        if (!TextUtils.isEmpty(Name) &&
                !TextUtils.isEmpty(Email) &&
                !TextUtils.isEmpty(Governate) &&
                !TextUtils.isEmpty(City) &&
                !TextUtils.isEmpty(Address) &&
                !TextUtils.isEmpty(Category)) {

            Common.CloseKeyBoard(this, shop_email);
            shop_name.setError(null);
            shop_email.setError(null);
            address.setError(null);
            city.setError(null);
            governate.setError(null);
            Signup(Name, Email, Governate, City, Address, Category);

        } else {
            if (TextUtils.isEmpty(Name)) {
                shop_name.setError(getString(R.string.shopname_req));
            } else {
                shop_name.setError(null);
            }

            if (TextUtils.isEmpty(Email)) {
                shop_email.setError(getString(R.string.email_req));
            } else {
                shop_email.setError(null);
            }

            if (TextUtils.isEmpty(Governate)) {
                governate.setError(getString(R.string.governate_req));
            } else {
                governate.setError(null);
            }
            if (TextUtils.isEmpty(City)) {
                city.setError(getString(R.string.city_req));
            } else {
                city.setError(null);
            }
            if (TextUtils.isEmpty(Address)) {
                address.setError(getString(R.string.address_req));
            } else {
                address.setError(null);
            }
            if (TextUtils.isEmpty(Category)) {
                category.setError(getString(R.string.category_req));
            } else {
                category.setError(null);
            }

        }

    }

    private void Signup(String name, String email, String governate, String city, String address, String category) {
        Intent intent = new Intent(Vender_Signup_Activity.this, MainActivity.class);
        startActivity(intent);
    }
}