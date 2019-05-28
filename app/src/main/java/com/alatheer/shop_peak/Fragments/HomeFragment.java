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
        addproduct();
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
        offerAdapter = new OfferAdapter(offerModelList(), getActivity());
        recyclerView.setAdapter(offerAdapter);

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

    public List<OfferModel> offerModelList() {

        List<OfferModel> offerlist = new ArrayList<>();

        offerlist.add(new OfferModel("https://gloimg.zafcdn.com/zaful/pdm-product-pic/Clothing/2018/12/21/goods-img/1547683385158389308.jpg",
                "https://gloimg.zafcdn.com/zaful/pdm-product-pic/Clothing/2018/12/21/goods-img/1547683385158389308.jpg", "dress", "a beautiful blue  address for girls ", "$25.99", "XL", "female", "POLO", R.drawable.vender_image1));
        offerlist.add(new OfferModel("https://www.kuiu.com/dw/image/v2/AAYP_PRD/on/demandware.static/-/Sites-master-catalog/default/dw7d8ead9b/images/hi-res/Outerwear/RubiconHoodedJacket/Gunmetal/RubiconHooded_GM_FR02_1425x1825.jpg?sw=270&sh=330&sm=fit",
                "https://www.kuiu.com/dw/image/v2/AAYP_PRD/on/demandware.static/-/Sites-master-catalog/default/dw7d8ead9b/images/hi-res/Outerwear/RubiconHoodedJacket/Gunmetal/RubiconHooded_GM_FR02_1425x1825.jpg?sw=270&sh=330&sm=fit", "jacket", "a comfartable black jacket for boys", "$30.00 ", "L", "male", "TownTeam", R.drawable.vender_image2));
        offerlist.add(new OfferModel("https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1524509473/cole-haan-DRESSSNEAK0418.jpg?itok=goYT20F6",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1524509473/cole-haan-DRESSSNEAK0418.jpg?itok=goYT20F6", "shoes", "a comfartable blue sportive shoes for playing football", "$20.00", "L", "male", "Nike", R.drawable.vender_image3));
        offerlist.add(new OfferModel("https://c.76.my/Malaysia/nian-jeep-cotton-jacket-men-coat-men-jacket-j8028-aeioumall-1603-08-aeioumall@1.jpg",
                "https://c.76.my/Malaysia/nian-jeep-cotton-jacket-men-coat-men-jacket-j8028-aeioumall-1603-08-aeioumall@1.jpg", "jacket", "a comfartable black jacket for boys", "$30.00 ", "XXL", "male", "Puma", R.drawable.vender_image4));
        offerlist.add(new OfferModel("https://di2ponv0v5otw.cloudfront.net/posts/2018/11/10/5be723f9819e9005c39a3991/s_5be72424409c152de27b26e8.jpg",
                "https://di2ponv0v5otw.cloudfront.net/posts/2018/11/10/5be723f9819e9005c39a3991/s_5be72424409c152de27b26e8.jpg", "dress", "a beautiful blue  address for girls ", "$25.99", "XXL", "female", "Addidas", R.drawable.vender_image5));
        offerlist.add(new OfferModel("https://www.cartersoshkosh.ca/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw851223ce/productimages/OF160441.jpg?sw=2000",
                "https://www.cartersoshkosh.ca/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw851223ce/productimages/OF160441.jpg?sw=2000", "shoes", "a comfartable blue sportive shoes for playing football", "$20.00", "M", "female", "POLO", R.drawable.vender_image1));
        offerlist.add(new OfferModel("https://gloimg.zafcdn.com/zaful/pdm-product-pic/Clothing/2018/12/21/goods-img/1547683385158389308.jpg",
                "https://gloimg.zafcdn.com/zaful/pdm-product-pic/Clothing/2018/12/21/goods-img/1547683385158389308.jpg", "dress", "a beautiful blue  address for girls ", "$25.99", "XL", "female", "POLO", R.drawable.vender_image1));
        offerlist.add(new OfferModel("https://www.kuiu.com/dw/image/v2/AAYP_PRD/on/demandware.static/-/Sites-master-catalog/default/dw7d8ead9b/images/hi-res/Outerwear/RubiconHoodedJacket/Gunmetal/RubiconHooded_GM_FR02_1425x1825.jpg?sw=270&sh=330&sm=fit",
                "https://www.kuiu.com/dw/image/v2/AAYP_PRD/on/demandware.static/-/Sites-master-catalog/default/dw7d8ead9b/images/hi-res/Outerwear/RubiconHoodedJacket/Gunmetal/RubiconHooded_GM_FR02_1425x1825.jpg?sw=270&sh=330&sm=fit", "jacket", "a comfartable black jacket for boys", "$30.00 ", "L", "male", "TownTeam", R.drawable.vender_image2));
        offerlist.add(new OfferModel("https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1524509473/cole-haan-DRESSSNEAK0418.jpg?itok=goYT20F6",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1524509473/cole-haan-DRESSSNEAK0418.jpg?itok=goYT20F6", "shoes", "a comfartable blue sportive shoes for playing football", "$20.00", "L", "male", "Nike", R.drawable.vender_image3));
        offerlist.add(new OfferModel("https://c.76.my/Malaysia/nian-jeep-cotton-jacket-men-coat-men-jacket-j8028-aeioumall-1603-08-aeioumall@1.jpg",
                "https://c.76.my/Malaysia/nian-jeep-cotton-jacket-men-coat-men-jacket-j8028-aeioumall-1603-08-aeioumall@1.jpg", "jacket", "a comfartable black jacket for boys", "$30.00 ", "XXL", "male", "Puma", R.drawable.vender_image4));
        offerlist.add(new OfferModel("https://di2ponv0v5otw.cloudfront.net/posts/2018/11/10/5be723f9819e9005c39a3991/s_5be72424409c152de27b26e8.jpg",
                "https://di2ponv0v5otw.cloudfront.net/posts/2018/11/10/5be723f9819e9005c39a3991/s_5be72424409c152de27b26e8.jpg", "dress", "a beautiful blue  address for girls ", "$25.99", "XXL", "female", "Addidas", R.drawable.vender_image5));
        offerlist.add(new OfferModel("https://www.cartersoshkosh.ca/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw851223ce/productimages/OF160441.jpg?sw=2000",
                "https://www.cartersoshkosh.ca/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw851223ce/productimages/OF160441.jpg?sw=2000", "shoes", "a comfartable blue sportive shoes for playing football", "$20.00", "M", "female", "POLO", R.drawable.vender_image1));
        return offerlist;
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
    public void addproduct() {
        Call<List<HomeModel>> call = Api.getService().get_all_products();
        call.enqueue(new Callback<List<HomeModel>>() {
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
