package com.alatheer.shop_peak.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Adapter.Favourit_Adapter;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Favorite_Fragment extends Fragment {
    RecyclerView recyclerView_favorite;
    RecyclerView.LayoutManager basket_manager;
    Favourit_Adapter favoriteAdapter;
    MyAppDatabase myAppDatabase;
    List<HomeModel> favouritModelList;
    Activity activity;


    MySharedPreference preferences;

    UserModel1  userModel1;

    String usr_id;

    private ProgressBar progressBar;
    private TextView txt_no;

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
        View view = inflater.inflate(R.layout.fragment_favorite_, container, false);
        //activity = getActivity();
        //((MainActivity)activity).setOnBackPressedListener(new OnBackPressedListener((FragmentActivity) activity) );
        initview(view);
        return view;
    }

    private void initview(View view) {
        if (!isConnected()) {
            new AlertDialog.Builder(getActivity()).setIcon(R.drawable.ic_warning).setTitle(getString(R.string.networkconnectionAlert))
                    .setMessage(getString(R.string.check_connection))
                    .setPositiveButton(getString(R.string.close), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getActivity().finish();
                        }
                    }).show();
        } else {
        }



        preferences=MySharedPreference.getInstance();
        userModel1=preferences.Get_UserData(getActivity());

        recyclerView_favorite = view.findViewById(R.id.basket_recycler);
        progressBar = view.findViewById(R.id.progBar);
        txt_no = view.findViewById(R.id.tv_no);

        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);


        if (userModel1==null){

            txt_no.setText(R.string.SH_Log);
            txt_no.setVisibility(View.VISIBLE);

        }


        initRecyclerview();

    }

    private void initRecyclerview() {

        favouritModelList=new ArrayList<>();
        getFavourtWEb();
        recyclerView_favorite.setHasFixedSize(true);
        basket_manager = new LinearLayoutManager(getActivity());
        recyclerView_favorite.setLayoutManager(basket_manager);
        favoriteAdapter = new Favourit_Adapter( favouritModelList, getActivity(),this);
        recyclerView_favorite.setAdapter(favoriteAdapter);
    }

    private void getFavourtWEb() {

        if (userModel1!=null) {
             usr_id = userModel1.getId();
        }
        Api.getService()
                .get_all_favourite(usr_id)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                        if (response.isSuccessful()) {

                            progressBar.setVisibility(View.GONE);

                            if (response.body().size() > 0) {

                                favouritModelList.addAll(response.body());
                                favoriteAdapter.notifyDataSetChanged();
                                txt_no.setVisibility(View.GONE);

                            }else {

                                txt_no.setVisibility(View.VISIBLE);

                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

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

    public void pos(int pos, String id) {


        favouritModelList.remove(pos);
        favoriteAdapter.notifyItemRemoved(pos);

        String usr_id=userModel1.getId();


        Api.getService()
                .delet_to_favourite(usr_id,id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                        favoriteAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                    }
                });

    }

}



