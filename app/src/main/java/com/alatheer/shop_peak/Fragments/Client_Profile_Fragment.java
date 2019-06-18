package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Client_Profile_Fragment extends android.app.Fragment{


    MySharedPreference preferences;

    UserModel1 userModel1;

    EditText user_name,adress,email,city,govern,phone;
    ImageView edit_name,edit_loc,edit_phone,edit_city,edit_govern,edit_image;
    Button edit;
    int PICK_IMAGE_REQUEST = 2;
    Uri filePath;
    CircleImageView img_profile;

    @Override
    public void onAttach(Context context) {

        Paper.init(context);
        String lang = Paper.book().read("language");

        if (Paper.book().read("language").equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.AR_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        } else {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.EN_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        super.onAttach(CalligraphyContextWrapper.wrap(LanguageHelper.onAttach(context, lang)));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.client_profile, container, false);
        initview(view);
        return view;

    }

    private void initview(View view) {
        if (!isConnected()) {
            new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_warning).setTitle(getString(R.string.networkconnectionAlert))
                    .setMessage(getString(R.string.check_connection))
                    .setPositiveButton(getString(R.string.wifi), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            WifiManager wifiManager = (WifiManager) getActivity().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                            wifiManager.setWifiEnabled(true);
                        }
                    }).show();
        } else {

        }



        user_name=view.findViewById(R.id.user_name);
        adress=view.findViewById(R.id.adress);
        email=view.findViewById(R.id.email);
        city=view.findViewById(R.id.city);
        govern=view.findViewById(R.id.govern);
        phone=view.findViewById(R.id.phone);
        edit_image= view.findViewById(R.id.edit_image);
        img_profile=view.findViewById(R.id.img_profile);
        edit = view.findViewById(R.id.edit_profile);
        edit_name = view.findViewById(R.id.edit_name);
        edit_phone = view.findViewById(R.id.edit_phone);
        edit_loc = view.findViewById(R.id.edit_loc);
        edit_govern= view.findViewById(R.id.edit_govern);
        edit_city = view.findViewById(R.id.edit_city);
        preferences=MySharedPreference.getInstance();
        userModel1=preferences.Get_UserData(getActivity());

        if (userModel1!=null){


            user_name.setText(userModel1.getFull_name());
            adress.setText(userModel1.getAddress());
            email.setText(userModel1.getEmail());
            city.setText(userModel1.getMadina());
            govern.setText(userModel1.getMohafza());
            phone.setText(userModel1.getPhone());

            Picasso.with(getActivity()).load(userModel1.getLogo_img()).into(img_profile);
        }else {

            Toast.makeText(getActivity(), "you Shoud Sign First", Toast.LENGTH_SHORT).show();
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_your_profile();
            }
        });
        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name.setEnabled(true);
            }
        });
        edit_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone.setEnabled(true);
            }
        });
        edit_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        edit_city.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city.setEnabled(true);
            }
        });
        edit_govern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                govern.setEnabled(true);
            }
        });
        edit_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adress.setEnabled(true);
            }
        });
    }

    private void edit_your_profile() {
        MultipartBody.Part logo_img = Common.getMultiPart(getActivity(),filePath,"logo_img");
        final String name = user_name.getText().toString();
        String address = adress.getText().toString();
        String phone1 = phone.getText().toString();
        String city1 = city.getText().toString();
        String govern1 = govern.getText().toString();
        String id = userModel1.getId();
        String email = userModel1.getEmail();
        String type = userModel1.getType();
        Api.getService().update_user(id,name,govern1,city1,address,"","","",logo_img,type).enqueue(new Callback<UserModel1>() {
            @Override
            public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess() == 1){
                        UserModel1 userModel=response.body();

                        MySharedPreference mySharedPreference = MySharedPreference.getInstance();
                        mySharedPreference.Create_Update_UserData(getActivity(),userModel);
                        Log.d("model",mySharedPreference.Get_UserData(getActivity()).getFull_name());
                         user_name.setText(userModel.getFull_name());
                         Picasso.with(getActivity()).load(userModel.getLogo_img()).into(img_profile);
                         city.setText(userModel.getCity());

                        //Intent intent = new Intent(getActivity(), MainActivity.class);
                        //startActivity(intent);
                    }
                }

            }

            @Override
            public void onFailure(Call<UserModel1> call, Throwable t) {

            }
        });
    }


    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
        }
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            android.net.NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else
                return false;
        }
        return false;
    }
}

