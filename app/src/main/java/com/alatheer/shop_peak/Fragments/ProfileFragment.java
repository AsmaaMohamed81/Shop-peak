package com.alatheer.shop_peak.Fragments;

import android.os.Bundle;
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
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.ProfileModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    ImageView img_grid,img_ver;
    RecyclerView menu_recycler;
    RecyclerView.LayoutManager verticalmanager;
    GridLayoutManager gridmanager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_profile, container, false);
        initview(view);
        return view;
    }

    private void initview(final View view) {
         img_grid=view.findViewById(R.id.menu_grid);
         img_ver=view.findViewById(R.id.menu_vertical);
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

    }

    private void viewvertical() {
        img_ver.setColorFilter(getResources().getColor(R.color.colorPrimary));
        img_grid.setColorFilter(getResources().getColor(R.color.gray));
        menu_recycler.setHasFixedSize(true);
        verticalmanager=new LinearLayoutManager(getActivity());
        menu_recycler.setLayoutManager(verticalmanager);
        Profile_verticalAdapter profile_verticalAdapter=new Profile_verticalAdapter(profileModelList(),getActivity());
        menu_recycler.setAdapter(profile_verticalAdapter);

    }

    private void Viewgrid() {
        img_grid.setColorFilter(getResources().getColor(R.color.colorPrimary));
        img_ver.setColorFilter(getResources().getColor(R.color.gray));
        menu_recycler.setHasFixedSize(true);
        gridmanager=new GridLayoutManager(getActivity(),3);
        menu_recycler.setLayoutManager(gridmanager);
        Profile_GridAdapter profile_gridAdapter=new Profile_GridAdapter(profileModelList(),getActivity());
        menu_recycler.setAdapter(profile_gridAdapter);
    }

    private List<ProfileModel> profileModelList (){

        List<ProfileModel> profilelist = new ArrayList<>();

        profilelist .add(new ProfileModel(R.drawable.item2));
        profilelist .add(new ProfileModel(R.drawable.item1));
        profilelist .add(new ProfileModel(R.drawable.item3));
        profilelist .add(new ProfileModel(R.drawable.item1));
        profilelist .add(new ProfileModel(R.drawable.item2));
        profilelist .add(new ProfileModel(R.drawable.item3));

        return profilelist ;

    }

}
