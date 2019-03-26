package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class Client_ProfileFragment extends Fragment {
    CircleImageView client_image;
    ImageView edit_profile;
    TextView client_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_client__profile, container, false);
        initview(view);
        return view;
    }

    private void initview(View v) {


    }
}
