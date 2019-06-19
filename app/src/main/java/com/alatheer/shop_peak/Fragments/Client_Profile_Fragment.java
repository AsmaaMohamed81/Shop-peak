package com.alatheer.shop_peak.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.IntroActivity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Adapter.cityAdapter;
import com.alatheer.shop_peak.Adapter.governAdapter;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.common.Common;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.squareup.picasso.Picasso;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class Client_Profile_Fragment extends Fragment {


    MySharedPreference preferences;

    UserModel1 userModel1;

    EditText user_name,adress,email,city,govern1,phone;
    ImageView edit_name,edit_loc,edit_phone,edit_city,edit_govern,edit_image;
    LinearLayout before_edit,make_edit;
    Button edit;
    int PICK_IMAGE_REQUEST = 2;
    Uri filePath;
    CircleImageView img_profile;
    ImageView done;
    String  city_id, govern_id;
    private RecyclerView recyc_govern, recyc_city;
    private LinearLayout container_city, container_govern;
    private ExpandableLayout expand_layout_city, expand_layout_govern;
    private TextView tv_title_govern, tv_title_city;
    private ArrayList<Govern> governArrayList;
    private ArrayList<City> cityArrayList;

    private cityAdapter cityAdapter;
    private com.alatheer.shop_peak.Adapter.governAdapter governAdapter;

    private Govern govern;
    private final String read_permission = Manifest.permission.READ_EXTERNAL_STORAGE;
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

        before_edit = view.findViewById(R.id.linear_before_edit);
        make_edit = view.findViewById(R.id.linear_make_edit);
        done = view.findViewById(R.id.done);
        user_name=view.findViewById(R.id.user_name);
        adress=view.findViewById(R.id.adress);
        email=view.findViewById(R.id.email);
        city=view.findViewById(R.id.city);
        govern1=view.findViewById(R.id.govern);
        phone=view.findViewById(R.id.phone);
        tv_title_govern = view.findViewById(R.id.tv_title_govern);
        tv_title_city = view.findViewById(R.id.tv_title_city);
        img_profile=view.findViewById(R.id.img_profile);
        edit = view.findViewById(R.id.edit_profile);
        preferences=MySharedPreference.getInstance();
        userModel1=preferences.Get_UserData(getActivity());

        recyc_govern = view.findViewById(R.id.recView_govern);
        recyc_city = view.findViewById(R.id.recView_city);

        container_city = view.findViewById(R.id.container_city);
        container_govern = view.findViewById(R.id.container_govern);

        expand_layout_city = view.findViewById(R.id.expand_layout_city);
        expand_layout_govern = view.findViewById(R.id.expand_layout_govern);


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
        img_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check_ReadPermission(PICK_IMAGE_REQUEST);
            }
        });

        /////////////////////////////////////

        get_govern();

        governArrayList = new ArrayList<>();
        cityArrayList = new ArrayList<>();


        recyc_govern.setLayoutManager(new LinearLayoutManager(getActivity()));

        governAdapter = new governAdapter(getActivity(),this, governArrayList);

        recyc_govern.setAdapter(governAdapter);

        governAdapter.notifyDataSetChanged();



        if (userModel1!=null){


            user_name.setText(userModel1.getFull_name());
            adress.setText(userModel1.getAddress());
            email.setText(userModel1.getEmail());
            city.setText(userModel1.getMadina());
            govern1.setText(userModel1.getMohafza());
            phone.setText(userModel1.getPhone());

            Picasso.with(getActivity()).load(userModel1.getLogo_img()).into(img_profile);
        }else {

            Toast.makeText(getActivity(), "you Shoud Sign First", Toast.LENGTH_SHORT).show();
        }
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name.setEnabled(true);
                phone.setEnabled(true);
                adress.setEnabled(true);
                done.setVisibility(View.VISIBLE);
                before_edit.setVisibility(View.GONE);
                make_edit.setVisibility(View.VISIBLE);

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_your_profile();
            }
        });


    }

    private void edit_your_profile() {
        final String name = user_name.getText().toString();
        String address = adress.getText().toString();
        String phone1 = phone.getText().toString();
        String city1 = city.getText().toString();
        String govern2 = govern1.getText().toString();
        String id = userModel1.getId();
        String email = userModel1.getEmail();
        String type = userModel1.getType();
        if (filePath==null){
            RequestBody Vfull_name = Common.getRequestBodyText(name);
            RequestBody Vmohafza = Common.getRequestBodyText(govern_id);
            RequestBody Vmadina = Common.getRequestBodyText(city_id);
            RequestBody Vaddress = Common.getRequestBodyText(address);
            RequestBody Vstore_tasnef = Common.getRequestBodyText("");
            RequestBody Vlat = Common.getRequestBodyText("");
            RequestBody Vlang = Common.getRequestBodyText("");
            RequestBody Vtype = Common.getRequestBodyText(type);
            MultipartBody.Part logo_img = Common.getMultiPart(getActivity(),Uri.parse(userModel1.getLogo_img()),"logo_img");
            final ProgressDialog dialog = Common.createProgressDialog(getActivity(),getString(R.string.waitt));
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            Api.getService().update_user(id,Vfull_name,Vmohafza,Vmadina,Vaddress,Vstore_tasnef,Vlat,Vlang,logo_img,Vtype).enqueue(new Callback<UserModel1>() {
                @Override
                public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                    if(response.isSuccessful()){
                        if(response.body().getSuccess() == 1){
                            dialog.dismiss();
                            UserModel1 userModel=response.body();
                            preferences.Create_Update_UserData(getActivity(),userModel);
                            Log.d("model",preferences.Get_UserData(getActivity()).getFull_name());
                            user_name.setText(userModel.getFull_name());
                            Picasso.with(getActivity()).load(userModel.getLogo_img()).into(img_profile);
                            phone.setText(userModel.getPhone());
                            city.setText(userModel.getCity());
                            Toast.makeText(getActivity(), "your profile updated successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), IntroActivity.class));

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
        RequestBody Vfull_name = Common.getRequestBodyText(name);
        RequestBody Vmohafza = Common.getRequestBodyText(govern2);
        RequestBody Vmadina = Common.getRequestBodyText(city1);
        RequestBody Vaddress = Common.getRequestBodyText(address);
        RequestBody Vstore_tasnef = Common.getRequestBodyText("");
        RequestBody Vlat = Common.getRequestBodyText("");
        RequestBody Vlang = Common.getRequestBodyText("");
        RequestBody Vtype = Common.getRequestBodyText(type);
        MultipartBody.Part logo_img = Common.getMultiPart(getActivity(),filePath,"logo_img");
        //RequestBody Vid = Common.getRequestBodyText(id);

        final ProgressDialog dialog = Common.createProgressDialog(getActivity(),getString(R.string.waitt));
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        Api.getService().update_user(id,Vfull_name,Vmohafza,Vmadina,Vaddress,Vstore_tasnef,Vlat,Vlang,logo_img,Vtype).enqueue(new Callback<UserModel1>() {
            @Override
            public void onResponse(Call<UserModel1> call, Response<UserModel1> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess() == 1){
                        dialog.dismiss();
                        UserModel1 userModel=response.body();
                        preferences.Create_Update_UserData(getActivity(),userModel);
                        Log.d("model",preferences.Get_UserData(getActivity()).getFull_name());
                         user_name.setText(userModel.getFull_name());
                         Picasso.with(getActivity()).load(userModel.getLogo_img()).into(img_profile);
                         city.setText(userModel.getCity());
                         Toast.makeText(getActivity(), "your profile updated successfully", Toast.LENGTH_SHORT).show();
                         startActivity(new Intent(getActivity(), IntroActivity.class));

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
    private void Check_ReadPermission(int img) {
        if (ContextCompat.checkSelfPermission(getActivity(),read_permission)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(getActivity(),new String[]{read_permission},img);
        }else
        {
            chooseImage(img);
        }
    }

    private void chooseImage(int img) {
        Intent intent ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        }else
        {
            intent = new Intent(Intent.ACTION_GET_CONTENT);

        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/*");
        startActivityForResult(intent,img);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            img_profile.setImageURI(filePath);
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

            recyc_city.setLayoutManager(new LinearLayoutManager(getActivity()));
            cityAdapter = new cityAdapter(getActivity(), cityArrayList,this);

            recyc_city.setAdapter(cityAdapter);

            tv_title_govern.setText(governArrayList.get(pos).getName());

            expand_layout_govern.toggle(true);

        }
    }


}

