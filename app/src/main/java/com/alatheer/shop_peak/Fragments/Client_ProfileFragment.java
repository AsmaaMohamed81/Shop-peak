package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.FullScreenImageActivity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Adapter.OnBackPressedListener;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;


public class Client_ProfileFragment extends android.app.Fragment{
    CircleImageView client_image;
    CardView cardView;
    TextView client_name,client_email;
    Button edit;
    int PICK_IMAGE_REQUEST;
    Uri filePath;
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_client__profile, container, false);
        // activity = getActivity();
        //((MainActivity)activity).setOnBackPressedListener(new OnBackPressedListener((FragmentActivity) activity) );
        initview(view);
        return view;
    }


    private void initview(View v) {
        client_image=v.findViewById(R.id.client_img);
        client_name=v.findViewById(R.id.client_name);
        cardView=v.findViewById(R.id.cardview);
        client_email=v.findViewById(R.id.client_email);
        edit=v.findViewById(R.id.btn_edit);
        client_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                client_name.setText("mohamed");
                client_email.setText("mmmmmmm@gmail.com");
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            Picasso.with(getActivity()).load(filePath).into(client_image);
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                //client_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
