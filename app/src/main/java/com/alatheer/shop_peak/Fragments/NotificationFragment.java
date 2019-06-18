package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.Notifi_Adapter;
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
    RecyclerView notif_recycler;
    Notifi_Adapter notifi_adapter;
    Like_Adapter like_adapter;
    RecyclerView.LayoutManager notificationManager,followManager;
    MySharedPreference mprefs;
    List<NotificationModel> Llist;

    Activity activity;

    String user_id;


    private ProgressBar progressBar;
    private TextView txt_no;
    private MySharedPreference preferences;

    private UserModel1 userModel1;

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
        notif_recycler=view.findViewById(R.id.notif_recycler);


        preferences=MySharedPreference.getInstance();
        userModel1=preferences.Get_UserData(getActivity());


        if (userModel1==null){

            txt_no.setText(R.string.SH_Log);
            txt_no.setVisibility(View.VISIBLE);

        }else {

            user_id=userModel1.getId();
            Log.e("Asmaa", "initview: "+user_id );
        }

        Llist = new ArrayList<>();


        notificationManager=new LinearLayoutManager(getActivity());
        notif_recycler.setLayoutManager(notificationManager);
        notif_recycler.setHasFixedSize(true);
        notifi_adapter=new Notifi_Adapter(Llist,getActivity());
        notif_recycler.setAdapter(notifi_adapter);



        get_notification_list(user_id);



        progressBar = view.findViewById(R.id.progBar);
        txt_no = view.findViewById(R.id.tv_no);

        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);




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

    private void get_notification_list(String user_id) {


        Api.getService()
                .get_notification(user_id)
                .enqueue(new Callback<List<NotificationModel>>() {
                    @Override
                    public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                        if (response.isSuccessful()) {

                            progressBar.setVisibility(View.GONE);

                            if (response.body().size() > 0) {

                                Llist.addAll(response.body());
                                notifi_adapter.notifyDataSetChanged();
                                txt_no.setVisibility(View.GONE);

                            }else {

                                txt_no.setVisibility(View.VISIBLE);

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<NotificationModel>> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);


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
