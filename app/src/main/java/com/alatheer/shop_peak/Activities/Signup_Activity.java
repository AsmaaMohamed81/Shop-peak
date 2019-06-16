package com.alatheer.shop_peak.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.cityAdapter;
import com.alatheer.shop_peak.Adapter.governAdapter;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_Activity extends AppCompatActivity {
    EditText edt_name2, edt_email, edt_phone, edt_password, edt_address,user_confirm_password;
    private CheckBox checkBox;
    private ProgressDialog dialog;
    Button sign_up;
    private Boolean accepted = false;
    private String userName, passWord, Phone, Email, address, city_id, govern_id,confirm_password;
    private View root;
    private Snackbar snackbar;

    private RecyclerView recyc_govern, recyc_city;
    private LinearLayout container_city, container_govern;
    private ExpandableLayout expand_layout_city, expand_layout_govern;

    private ArrayList<Govern> governArrayList;
    private ArrayList<City> cityArrayList;

    private cityAdapter cityAdapter;
    private governAdapter governAdapter;

    private Govern govern;

    private TextView tv_title_govern, tv_title_city,syasa;


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
        edt_address = findViewById(R.id.user_address);
        sign_up = findViewById(R.id.btn_sign);
        root = findViewById(R.id.root);
        checkBox = findViewById(R.id.check_box);
        user_confirm_password=findViewById(R.id.user_confirm_password);
        syasa=findViewById(R.id.sysa);


/////#FFFFFF/////////////////
        tv_title_govern = findViewById(R.id.tv_title_govern);
        tv_title_city = findViewById(R.id.tv_title_city);

        recyc_govern = findViewById(R.id.recView_govern);
        recyc_city = findViewById(R.id.recView_city);

        container_city = findViewById(R.id.container_city);
        container_govern = findViewById(R.id.container_govern);

        expand_layout_city = findViewById(R.id.expand_layout_city);
        expand_layout_govern = findViewById(R.id.expand_layout_govern);


        container_govern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_layout_govern.toggle(true);

            }
        });

        container_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expand_layout_city.toggle(true);

            }
        });


        /////////////////////////////////////

        get_govern();

        governArrayList = new ArrayList<>();
        cityArrayList = new ArrayList<>();


        recyc_govern.setLayoutManager(new LinearLayoutManager(this));

        governAdapter = new governAdapter(this, governArrayList);

        recyc_govern.setAdapter(governAdapter);

        governAdapter.notifyDataSetChanged();


////////////////////////////////////////
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


        syasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://shop-peak.com/Web/about/1"));
                startActivity(intent);
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
        confirm_password=user_confirm_password.getText().toString();
        Email = edt_email.getText().toString();
        Phone = edt_phone.getText().toString();
        address = edt_address.getText().toString();

        if (!TextUtils.isEmpty(userName) &&
                !TextUtils.isEmpty(passWord) &&
                !TextUtils.isEmpty(Email) &&
                !TextUtils.isEmpty(Phone) &&
                !TextUtils.isEmpty(address) &&
                passWord.length() >= 8 &&
                confirm_password.equals(passWord)&&
                android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches() &&
                accepted &&
                city_id != null &&
                govern_id != null) {

            Common.CloseKeyBoard(this, edt_name2);
            edt_name2.setError(null);
            edt_password.setError(null);
            edt_email.setError(null);
            edt_phone.setError(null);
            edt_address.setError(null);

//            Signup(userName, passWord, Email, Phone);

            SignUp(userName, passWord, Email, Phone, address, accepted, city_id, govern_id);

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
            if (!confirm_password.equals(passWord)) {
                edt_password.setError(getString(R.string.confirm_req));
                user_confirm_password.setError(getString(R.string.confirm_req));

            } else {
                edt_password.setError(null);
                user_confirm_password.setError(null);

            }
            if (TextUtils.isEmpty(Email)) {
                edt_email.setError(getString(R.string.pass_req));
            } else {
                edt_email.setError(null);
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                edt_email.setError(getString(R.string.email_patt));
            } else {
                edt_email.setError(null);
            }
            if (TextUtils.isEmpty(Phone)) {
                edt_phone.setError(getString(R.string.phone_req));
            } else {
                edt_phone.setError(null);
            }

            if (TextUtils.isEmpty(address)) {
                edt_address.setError(getString(R.string.address_req));
            } else {
                edt_address.setError(null);
            }

            if (!accepted) {


                CreateSnackBar(getString(R.string.accept_terms));

            } else {

                dismissSnackBar();
            }
            if (govern_id == null) {
                Toast.makeText(this, "choose Govern first", Toast.LENGTH_SHORT).show();
            }
            if (city_id == null) {
                Toast.makeText(this, "choose city first", Toast.LENGTH_SHORT).show();
            }


        }

    }

    private void SignUp(String userName, String passWord, String email, String phone, String address, Boolean accepted, String city_id, String govern_id) {

        int agree = 0;

        if (accepted) {

            agree = 1;
        }

        final ProgressDialog dialog = Common.createProgressDialog(this,getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Api.getService()
                .register(userName, email, phone, govern_id, city_id, address, passWord)
                .enqueue(new Callback<UserModel1>() {
                    @Override
                    public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {


                        if (response.isSuccessful()){


                            dialog.dismiss();

                            if (response.body().getSuccess()==1){


                                UserModel1 userModel=response.body();

                                MySharedPreference mySharedPreference = MySharedPreference.getInstance();

                                mySharedPreference.Create_Update_UserData(Signup_Activity.this,userModel);

                                Log.d("model",mySharedPreference.Get_UserData(Signup_Activity.this).getFull_name());


                                Intent intent = new Intent(Signup_Activity.this, MainActivity.class);
                                startActivity(intent);


                            }



                        }
                    }

                    @Override
                    public void onFailure(Call<UserModel1> call, Throwable t) {

                        dialog.dismiss();

                    }
                });


    }

    private void Signup(String userName, String passWord, String email, String phone) {
        UserModel userModel = new UserModel(userName, "https://www.wpclipart.com/buildings/shop.png", email);
        MySharedPreference mprefs = new MySharedPreference(this);
//        mprefs.Create_Update_UserData(Signup_Activity.this, userModel);

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


    private void get_govern() {

        Api.getService()
                .getGovern()
                .enqueue(new Callback<List<Govern>>() {
                    @Override
                    public void onResponse(Call<List<Govern>> call, Response<List<Govern>> response) {

                        if (response.isSuccessful()) {

                            governArrayList.addAll(response.body());
                            governAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Govern>> call, Throwable t) {

                    }
                });


    }

    public void pos_city(int pos) {


        tv_title_city.setText(cityArrayList.get(pos).getName());
        city_id = cityArrayList.get(pos).getId();
        expand_layout_city.toggle(true);

    }

    public void pos_govern(int pos) {


        govern = governArrayList.get(pos);
        govern_id = govern.getId();


        if (govern.getCity().size() > 0) {
            cityArrayList = govern.getCity();

            recyc_city.setLayoutManager(new LinearLayoutManager(this));
            cityAdapter = new cityAdapter(this, cityArrayList);

            recyc_city.setAdapter(cityAdapter);

            tv_title_govern.setText(governArrayList.get(pos).getName());

            expand_layout_govern.toggle(true);

        }
    }
}
