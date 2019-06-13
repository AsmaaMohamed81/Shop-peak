package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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

import com.alatheer.shop_peak.Adapter.Notification_Adapter;
import com.alatheer.shop_peak.Model.NotificationModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;


public class NotificationFragment extends android.app.Fragment {
    RecyclerView notification_recycler;
    Notification_Adapter notification_adapter;
    RecyclerView.LayoutManager notificationManager;
    List<NotificationModel>list;
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
        notification_recycler=view.findViewById(R.id.notification_recycler);
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
        notification_recycler.setHasFixedSize(true);
        notification_adapter=new Notification_Adapter(notificationModelList(),getActivity());
        notification_recycler.setAdapter(notification_adapter);
        notificationManager=new LinearLayoutManager(getActivity());
        notification_recycler.setLayoutManager(notificationManager);

    }
    private List<NotificationModel>notificationModelList(){
        list=new ArrayList<>();
        list.add(new NotificationModel("aaaaaa","bbbbbbbbb"));
        list.add(new NotificationModel("cccccc","ddddddddd"));
        list.add(new NotificationModel("eeeeee","fffffffff"));
        list.add(new NotificationModel("gggggg","hhhhhhhhh"));
        return list;
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
