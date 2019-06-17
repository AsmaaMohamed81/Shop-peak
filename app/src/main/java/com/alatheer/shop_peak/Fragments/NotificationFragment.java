package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.Follows_Adapter;
import com.alatheer.shop_peak.Adapter.Like_Adapter;
import com.alatheer.shop_peak.Model.Follow;
import com.alatheer.shop_peak.Model.Like;
import com.alatheer.shop_peak.Model.NotificationModel;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends android.app.Fragment {
    RecyclerView follow_recycler;
    RecyclerView like_recycler;
    Follows_Adapter follows_adapter;
    Like_Adapter like_adapter;
    RecyclerView.LayoutManager notificationManager;
    MySharedPreference mprefs;
    List<Follow>followList;
    List<Like>likeList;
    Activity activity;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        //activity = getActivity();
        //((MainActivity)activity).setOnBackPressedListener(new OnBackPressedListener((FragmentActivity) activity) );
        initview(view);
        return view;
    }

    private void initview(View view) {
        follow_recycler=view.findViewById(R.id.follow_recycler);
        like_recycler = view.findViewById(R.id.like_recycler);
        notificationManager=new LinearLayoutManager(getActivity());
        like_recycler.setLayoutManager(notificationManager);
        follow_recycler.setLayoutManager(notificationManager);
        mprefs =MySharedPreference.getInstance();
        like_recycler.setHasFixedSize(true);
        follow_recycler.setHasFixedSize(true);
        get_notification_list();
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

    private void get_notification_list() {
        UserModel1 userModel1 =mprefs.Get_UserData(getActivity());
        String user_id = userModel1.getId();
        Api.getService().get_notification(user_id).enqueue(new Callback<NotificationModel>() {
            @Override
            public void onResponse(Call<NotificationModel> call, Response<NotificationModel> response) {
                if(response.isSuccessful()){
                    followList = response.body().follows;
                    likeList = response.body().like;
                    like_adapter = new Like_Adapter(likeList,getActivity());
                    like_recycler.setAdapter(like_adapter);
                    follows_adapter = new Follows_Adapter(followList,getActivity());
                    follow_recycler.setAdapter(follows_adapter);

                }
            }

            @Override
            public void onFailure(Call<NotificationModel> call, Throwable t) {

            }
        });

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
