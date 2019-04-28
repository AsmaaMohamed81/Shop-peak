package com.alatheer.shop_peak.Fragments;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alatheer.shop_peak.Adapter.Profile_GridAdapter;
import com.alatheer.shop_peak.Adapter.Profile_verticalAdapter;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.ProfileModel;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;


public class ProfileFragment extends android.app.Fragment {
    ImageView img_grid,img_ver,add_product;
    RecyclerView menu_recycler;
    RecyclerView.LayoutManager verticalmanager;
    GridLayoutManager gridmanager;
    ProfileDatabase profileDatabase;
    Uri uri;
    Bitmap bitmap;
    int flag;
    int PICK_IMAGE_REQUEST;
    public static ProfileFragment getInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        initview(view);
        return view;
    }

    private void initview(final View view) {
        profileDatabase= Room.databaseBuilder(getApplicationContext(),ProfileDatabase.class,"product_db").allowMainThreadQueries().build();
         img_grid=view.findViewById(R.id.menu_grid);
         img_ver=view.findViewById(R.id.menu_vertical);
         add_product=view.findViewById(R.id.add_product);
         menu_recycler=view.findViewById(R.id.recycler_menu);
         Viewgrid();
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
        Profile_verticalAdapter profile_verticalAdapter=new Profile_verticalAdapter(profileDatabase.dao().get_profile_data(),getActivity());
        menu_recycler.setAdapter(profile_verticalAdapter);

    }

    private void Viewgrid() {
        img_grid.setColorFilter(getResources().getColor(R.color.colorPrimary));
        img_ver.setColorFilter(getResources().getColor(R.color.gray));
        menu_recycler.setHasFixedSize(true);
        gridmanager=new GridLayoutManager(getActivity(),3);
        menu_recycler.setLayoutManager(gridmanager);
        Profile_GridAdapter profile_gridAdapter=new Profile_GridAdapter(profileDatabase.dao().get_profile_data(),getActivity());
        menu_recycler.setAdapter(profile_gridAdapter);
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



