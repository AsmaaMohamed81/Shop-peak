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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.Profile_GridAdapter;
import com.alatheer.shop_peak.Adapter.Profile_verticalAdapter;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;
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
    ImageView img_grid,img_ver,add_product,profile_image;
    TextView profile_name;
    RecyclerView menu_recycler;
    RecyclerView.LayoutManager verticalmanager;
    GridLayoutManager gridmanager;
    ProfileDatabase profileDatabase;
    Activity activity;
    Uri uri;
    Bitmap bitmap;
    String vender_name,image,id_store;
    int flag;
    int PICK_IMAGE_REQUEST;

    private ProgressBar progressBar;
    private TextView txt_no;

    private ArrayList<HomeModel> homeModelArrayList;

    Profile_verticalAdapter profile_verticalAdapter;
    Profile_GridAdapter profile_gridAdapter;

    public static ProfileFragment getInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_profile, container, false);

        initview(view);
        return view;
    }

    private void initview(final View view) {

        homeModelArrayList=new ArrayList<>();
         profileDatabase= Room.databaseBuilder(getApplicationContext(),ProfileDatabase.class,"product_db").allowMainThreadQueries().build();
         profile_name=view.findViewById(R.id.profile_name);
         profile_image=view.findViewById(R.id.profile_img);
         img_grid=view.findViewById(R.id.menu_grid);
         img_ver=view.findViewById(R.id.menu_vertical);
         add_product=view.findViewById(R.id.add_product);
         menu_recycler=view.findViewById(R.id.recycler_menu);
        progressBar = view.findViewById(R.id.progBar);
        txt_no = view.findViewById(R.id.tv_no);

        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);
        Bundle bundle=getArguments();
        vender_name=bundle.getString("name");
        image =bundle.getString("image");
        id_store=bundle.getString("id");

        Log.d("asmaa", "initview: "+id_store);

        Viewgrid();

        getStoreProduct(id_store);




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
         add_product.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 chooseimage();
             }
         });




        Picasso.with(getActivity()).load(image).into(profile_image);

         profile_name.setText(vender_name);
         Viewgrid();
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
            Toast.makeText(getActivity(), "welcom" + "dffghjlk;l", Toast.LENGTH_SHORT).show();
        }
    }

    private void getStoreProduct(String id_store) {

        Api.getService()
                .get_store_product(id_store)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {

                        if (response.isSuccessful()){

                            progressBar.setVisibility(View.GONE);

                            if (response.body().size()>0){
                                homeModelArrayList.addAll(response.body());
//                                profile_gridAdapter.notifyDataSetChanged();
//                                profile_verticalAdapter.notifyDataSetChanged();

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

    private void chooseimage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
        menu_recycler.setHasFixedSize(true);
        verticalmanager=new LinearLayoutManager(getActivity());
        menu_recycler.setLayoutManager(verticalmanager);
         profile_verticalAdapter=new Profile_verticalAdapter(homeModelArrayList,getActivity());
        menu_recycler.setAdapter(profile_verticalAdapter);

    }

    private void Viewgrid() {
        img_grid.setColorFilter(getResources().getColor(R.color.colorPrimary));
        img_ver.setColorFilter(getResources().getColor(R.color.gray));
        menu_recycler.setHasFixedSize(true);
        gridmanager=new GridLayoutManager(getActivity(),3);
        menu_recycler.setLayoutManager(gridmanager);
         profile_gridAdapter=new Profile_GridAdapter(homeModelArrayList,getActivity());
        menu_recycler.setAdapter(profile_gridAdapter);
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


}



