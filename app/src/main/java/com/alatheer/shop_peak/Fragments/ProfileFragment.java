package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.FollowersActivity;
import com.alatheer.shop_peak.Activities.Login_Activity;
import com.alatheer.shop_peak.Activities.MyFollowersActivity;
import com.alatheer.shop_peak.Adapter.Profile_verticalAdapter;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;


public class ProfileFragment extends android.app.Fragment {
    ImageView img_grid, img_ver, add_product, profile_image;
    TextView profile_name;
    RecyclerView menu_recycler;
    RecyclerView.LayoutManager verticalmanager;
    GridLayoutManager gridmanager;
    ProfileDatabase profileDatabase;
    Activity activity;
    Uri uri;
    Bitmap bitmap;
    String vender_name, image, id_store;
    int flag;
    int PICK_IMAGE_REQUEST;

    private ProgressBar progressBar;
    private TextView txt_no, num_products, followers, myfollow;

    private ArrayList<HomeModel> homeModelArrayList;

    Profile_verticalAdapter profile_verticalAdapter;

    MySharedPreference mySharedPreference;
    UserModel1 userModel1;
    String img, id, name;
    private ArrayList<UserModel1> userModel1ArrayList, Listmyfollow;


    String type = "1";

    Button follow;

    boolean flagButton = false;

    public static ProfileFragment getInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        initview(view);
        return view;
    }

    private void initview(final View view) {

        mySharedPreference = MySharedPreference.getInstance();
        userModel1 = mySharedPreference.Get_UserData(getActivity());

        userModel1ArrayList = new ArrayList<>();
        Listmyfollow = new ArrayList<>();


        if (userModel1 != null) {
            img = userModel1.getLogo_img();
            id = userModel1.getId();
            name = userModel1.getFull_name();

        }

        homeModelArrayList = new ArrayList<>();
        profileDatabase = Room.databaseBuilder(getApplicationContext(), ProfileDatabase.class, "product_db").allowMainThreadQueries().build();
        profile_name = view.findViewById(R.id.profile_name);
        profile_image = view.findViewById(R.id.profile_img);
        img_grid = view.findViewById(R.id.menu_grid);
        img_ver = view.findViewById(R.id.menu_vertical);
//         add_product=view.findViewById(R.id.add_product);
        menu_recycler = view.findViewById(R.id.recycler_menu);
        num_products = view.findViewById(R.id.num_products);

        followers = view.findViewById(R.id.followers);
        follow = view.findViewById(R.id.follow);
        follow.setVisibility(view.VISIBLE);
        myfollow = view.findViewById(R.id.myfollow);

        progressBar = view.findViewById(R.id.progBar);
        txt_no = view.findViewById(R.id.tv_no);

        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);


        getIntent();
        checkfollow(id_store, id);

        Log.d("asmaa", "initview: " + id_store);


        img_grid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Viewgrid();

            }
        });
        img_ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewvertical();

            }
        });


        Picasso.with(getActivity()).load(image).into(profile_image);
        profile_name.setText(vender_name);
        getStoreProduct(id_store);
        Viewgrid();

        get_storefollow(id_store);


        followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), FollowersActivity.class);
                intent.putExtra("id_store", id_store);
                startActivity(intent);

            }
        });
        myfollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MyFollowersActivity.class);
                intent.putExtra("id_store", id_store);
                startActivity(intent);

            }
        });


        follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (userModel1 == null) {

                    CreateGpsDialog();

                } else {

                    if (flagButton == true) {

                        deleteflow(id_store, id);


                    } else {
                        makefollow(id_store, id);

                    }
                }
            }
        });


        get_my_flow(id_store);

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
    }

    private void updateUI() {

        if (flagButton == true) {


            follow.setText(R.string.unfollow);
            follow.setBackgroundResource(R.drawable.btn_field2);
        } else {


            follow.setText(R.string.follow);
            follow.setBackgroundResource(R.drawable.edite_profile);
        }
    }

    private void makefollow(String id_store, String id) {

        Api.getService()
                .make_follow(id_store, id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                        if (response.isSuccessful()) {

                            if (response.body().getSuccess() == 1) {

                                flagButton = true;
                                updateUI();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                    }
                });
    }

    private void deleteflow(String id_store, String id) {

        Api.getService()
                .delete_flow(id_store, id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                        if (response.isSuccessful()) {

                            if (response.body().getSuccess() == 1) {

                                flagButton = false;
                                updateUI();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                    }
                });
    }


    private void get_my_flow(String id_store) {
        Api.getService()
                .get_my_flow(id_store)
                .enqueue(new Callback<List<UserModel1>>() {
                    @Override
                    public void onResponse(Call<List<UserModel1>> call, Response<List<UserModel1>> response) {
                        if (response.isSuccessful()) {

                            if (response.body().size() > 0) {

                                Listmyfollow.addAll(response.body());
                                myfollow.setText(Integer.toString(response.body().size()));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserModel1>> call, Throwable t) {

                    }
                });
    }

    private void checkfollow(String id_store, String id) {

        Api.getService()
                .get_user_folow(id_store, id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                        if (response.isSuccessful()) {

                            if (response.body().getSuccess() == 1) {

                                flagButton = true;
                                updateUI();

                            } else {

                                flagButton = false;
                                updateUI();


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                    }
                });
    }

    private void get_storefollow(String id_store) {

        Api.getService()
                .get_storefollow(id_store)
                .enqueue(new Callback<List<UserModel1>>() {
                    @Override
                    public void onResponse(Call<List<UserModel1>> call, Response<List<UserModel1>> response) {
                        if (response.isSuccessful()) {

                            if (response.body().size() > 0) {

                                userModel1ArrayList.addAll(response.body());
                                followers.setText(Integer.toString(response.body().size()));

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<UserModel1>> call, Throwable t) {

                    }
                });
    }


    private void getIntent() {

        Bundle bundle = getArguments();


        if (bundle != null) {

            vender_name = bundle.getString("name");
            image = bundle.getString("image");
            id_store = bundle.getString("id");
        }

    }

    private void getStoreProduct(String id_store) {

        Api.getService()
                .get_store_product(id_store)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {

                        if (response.isSuccessful()) {

                            progressBar.setVisibility(View.GONE);

                            if (response.body().size() > 0) {
                                homeModelArrayList.addAll(response.body());
//                                profile_gridAdapter.notifyDataSetChanged();
//                                profile_verticalAdapter.notifyDataSetChanged();

                                num_products.setText(Integer.toString(response.body().size()));

                                Viewgrid();
                                txt_no.setVisibility(View.GONE);


                            }

                            txt_no.setVisibility(View.VISIBLE);


                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

                        progressBar.setVisibility(View.GONE);

                    }
                });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void viewvertical() {
        img_ver.setColorFilter(getResources().getColor(R.color.colorPrimary));
        img_grid.setColorFilter(getResources().getColor(R.color.gray));


        verticalmanager = new LinearLayoutManager(getActivity());
        menu_recycler.setHasFixedSize(true);
        menu_recycler.setLayoutManager(verticalmanager);
        profile_verticalAdapter = new Profile_verticalAdapter(homeModelArrayList, getActivity());
        menu_recycler.setAdapter(profile_verticalAdapter);

    }

    private void Viewgrid() {
        img_grid.setColorFilter(getResources().getColor(R.color.colorPrimary));
        img_ver.setColorFilter(getResources().getColor(R.color.gray));


        verticalmanager = new GridLayoutManager(getActivity(), 3);
        menu_recycler.setHasFixedSize(true);
        menu_recycler.setLayoutManager(verticalmanager);
        profile_verticalAdapter = new Profile_verticalAdapter(homeModelArrayList, getActivity());
        menu_recycler.setAdapter(profile_verticalAdapter);
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

   /* private List<ProfileModel> profileModelList (){
        List<ProfileModel> profilelist = new ArrayList<>();
        profilelist .add(new ProfileModel(R.drawable.item2));
        profilelist .add(new ProfileModel(R.drawable.item1));
        profilelist .add(new ProfileModel(R.drawable.item3));
        profilelist .add(new ProfileModel(R.drawable.item1));
        profilelist .add(new ProfileModel(R.drawable.item2));
        profilelist .add(new ProfileModel(R.drawable.item3));
        //ProfileModel profileModel=new ProfileModel();
        //Uri image=profileModel.getUpload_image();

        return profilelist ;*/

    private void CreateGpsDialog() {

        final android.app.AlertDialog gps_dialog = new android.app.AlertDialog.Builder(getActivity())
                .setCancelable(false)
                .create();

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(R.string.SH_Log);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                Intent intent = new Intent(getActivity(), Login_Activity.class);
                startActivity(intent);

            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }

}



