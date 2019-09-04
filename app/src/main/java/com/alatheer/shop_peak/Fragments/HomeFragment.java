package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.Search_Activity;
import com.alatheer.shop_peak.Activities.Seller_Search_Activity;
import com.alatheer.shop_peak.Adapter.HomeAdapter;
import com.alatheer.shop_peak.Adapter.OfferAdapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.OfferModel1;
import com.alatheer.shop_peak.Model.SellerSearch;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import com.alatheer.shop_peak.Local.HomeDatabase;


public class HomeFragment extends android.app.Fragment {
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    RecyclerView.LayoutManager layoutManager, layoutManager2;
    HomeAdapter homeAdapter;
    RadioGroup radioGroup;
    OfferAdapter offerAdapter;
    RadioButton product_radio,seller_radio;
    EditText search;
    List<HomeModel> homelist;
    List<OfferModel1> offerlist;

    MySharedPreference mySharedPreference;

    UserModel1 userModel1;

    private ProgressBar progressBar;
    private TextView txt_no;
    //HomeDatabase homeDatabase;

    String user_id;

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
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        //homeDatabase = Room.databaseBuilder(getApplicationContext(), HomeDatabase.class, "home_db").allowMainThreadQueries().build();
        initView(v);
        return v;
    }
    private void initView(View v) {


        mySharedPreference=MySharedPreference.getInstance();

        userModel1=mySharedPreference.Get_UserData(getActivity());

        homelist=new ArrayList<>();
        offerlist=new ArrayList<>();
        search = v.findViewById(R.id.txt_search);
        radioGroup = v.findViewById(R.id.radiogroup);
        product_radio = v.findViewById(R.id.product_radio);
        seller_radio = v.findViewById(R.id.seller_radio);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_SEARCH){
                    final String name = search.getText().toString();
                    if(product_radio.isChecked()){
                        Api.getService().search_Home(name).enqueue(new Callback<List<HomeModel>>() {
                            @Override
                            public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                                Intent intent = new Intent(getActivity(),Search_Activity.class);
                                Log.v("ggg",response.message());
                                intent.putExtra("list",(Serializable)response.body());
                                intent.putExtra("sanf_name",name);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<List<HomeModel>> call, Throwable t) {
                                Log.v("jjj",t.getMessage());
                            }
                        });
                        return true;
                    }else {
                         Api.getService().get_seller(name).enqueue(new Callback<List<SellerSearch>>() {
                             @Override
                             public void onResponse(Call<List<SellerSearch>> call, Response<List<SellerSearch>> response) {
                                 Intent intent = new Intent(getActivity(), Seller_Search_Activity.class);
                                 Log.v("ggg",response.message());
                                 intent.putExtra("list",(Serializable)response.body());
                                 intent.putExtra("sanf_name",name);
                                 startActivity(intent);
                             }

                             @Override
                             public void onFailure(Call<List<SellerSearch>> call, Throwable t) {

                             }
                         });                 }

                }
                return false;
            }
        });


        recyclerView2 = v.findViewById(R.id.recycler_home);
        progressBar = v.findViewById(R.id.progBar);
        txt_no = v.findViewById(R.id.tv_no);





        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent), PorterDuff.Mode.SRC_IN);


        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        homeAdapter = new HomeAdapter(homelist, getActivity());
        recyclerView2.setAdapter(homeAdapter);

        //service = Api.getRetrofit().create(Service.class);

        //final String title=search.getText().toString();
        setHasOptionsMenu(true);
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

        get_all_product_list();
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

        if (userModel1!=null) {
             user_id = userModel1.getId();
        }else {
            user_id="0";
        }
                 Api
                .getService()
                .get_all_products(user_id).enqueue(new Callback<List<HomeModel>>() {
            @Override
            public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {


                if (response.isSuccessful()) {

                    progressBar.setVisibility(View.GONE);

                    if (response.body().size() > 0) {


                        txt_no.setVisibility(View.GONE);
                        homelist.addAll(response.body());
                        homeAdapter.notifyDataSetChanged();



                    }else {

                        txt_no.setVisibility(View.VISIBLE);

                    }

                }
                else {

                    ResponseBody body = (ResponseBody) response.body();

                        body.close();

                }


            }

            @Override
            public void onFailure(Call<List<HomeModel>> call, Throwable t) {
                Log.v("lllll", t.getMessage());
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Check You Internet", Toast.LENGTH_SHORT).show();

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
