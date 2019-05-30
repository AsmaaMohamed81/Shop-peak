package com.alatheer.shop_peak.Fragments;

import android.app.SearchManager;
import android.app.Service;
import android.arch.persistence.room.Room;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceFragment;
import android.renderscript.Short4;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.Search_Activity;
import com.alatheer.shop_peak.Adapter.HomeAdapter;

import com.alatheer.shop_peak.Adapter.OfferAdapter;
//import com.alatheer.shop_peak.Local.HomeDatabase;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.OfferModel;
import com.alatheer.shop_peak.Model.OfferModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;


public class HomeFragment extends android.app.Fragment {
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    RecyclerView.LayoutManager layoutManager, layoutManager2;
    HomeAdapter homeAdapter;
    OfferAdapter offerAdapter;
    EditText search;
    List<HomeModel> homelist;
    List<OfferModel1> offerlist;
    //HomeDatabase homeDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //homeDatabase = Room.databaseBuilder(getApplicationContext(), HomeDatabase.class, "home_db").allowMainThreadQueries().build();
        initView(v);
        return v;
    }
    private void initView(View v) {
        search = v.findViewById(R.id.txt_search);
        recyclerView2 = v.findViewById(R.id.recycler_home);
        //service = Api.getRetrofit().create(Service.class);

        //final String title=search.getText().toString();
        setHasOptionsMenu(true);
        get_all_product_list();
        get_offers_list();
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
        // ((AppCompatActivity)getActivity()).setSupportActionBar(search);
        //((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        /////offer
        recyclerView = v.findViewById(R.id.recycler_offer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true);
        recyclerView.setLayoutManager(layoutManager);


        ////home



       /* search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                homeAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Intent i = new Intent(getActivity(), Search_Activity.class);
                    i.putExtra("title", search.getText().toString());
                    startActivity(i);
                    Animatoo.animateSlideRight(getActivity());
                    return true;
                }
                return false;
            }
        });*/
    }

    public void get_offers_list() {
        Api.getService().get_all_offers().enqueue(new Callback<List<OfferModel1>>() {
            @Override
            public void onResponse(Call<List<OfferModel1>> call, Response<List<OfferModel1>> response) {
                offerlist = response.body();
                offerAdapter = new OfferAdapter(offerlist, getActivity());
                recyclerView.setAdapter(offerAdapter);
            }

            @Override
            public void onFailure(Call<List<OfferModel1>> call, Throwable t) {

            }
        });
    }


    /*public List<HomeModel> homeModelList (){

        homelist = new ArrayList<>();
        homelist.add();
        homelist.add());
        homelist.add();
        homelist.add();
        homelist.add();
        homelist.add();
        return homelist;
    }*/
    public void get_all_product_list() {
        Api.getService().get_all_products().enqueue(new Callback<List<HomeModel>>() {
            @Override
            public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                homelist = response.body();
                recyclerView2.setHasFixedSize(true);
                layoutManager2 = new LinearLayoutManager(getActivity());
                recyclerView2.setLayoutManager(layoutManager2);
                homeAdapter = new HomeAdapter(homelist, getActivity());
                recyclerView2.setAdapter(homeAdapter);

            }

            @Override
            public void onFailure(Call<List<HomeModel>> call, Throwable t) {
                Log.v("lllll", t.getMessage());
            }
        });
    }

   /* @Override
    public void onCreateOptionsMenu(Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu,menu);
        MenuItem search_item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) search_item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                homeAdapter.getFilter().filter(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }*/
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
