package com.alatheer.shop_peak.Fragments;

import android.app.SearchManager;
import android.arch.persistence.room.Room;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceFragment;
import android.renderscript.Short4;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.Search_Activity;
import com.alatheer.shop_peak.Adapter.HomeAdapter;

import com.alatheer.shop_peak.Adapter.OfferAdapter;
import com.alatheer.shop_peak.Local.HomeDatabase;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.OfferModel;
import com.alatheer.shop_peak.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.facebook.FacebookSdk.getApplicationContext;


public class HomeFragment extends android.app.Fragment {
    RecyclerView recyclerView, recyclerView2, recyclerView3;
    RecyclerView.LayoutManager layoutManager, layoutManager2;
    HomeAdapter homeAdapter;
    OfferAdapter offerAdapter;
    EditText search;
    List<HomeModel> homelist;
    HomeDatabase homeDatabase;
    String name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        homeDatabase = Room.databaseBuilder(getApplicationContext(), HomeDatabase.class, "home_db").allowMainThreadQueries().build();
        initView(v);
        return v;
    }
    private void initView(View v) {
        search = v.findViewById(R.id.txt_search);
        //final String title=search.getText().toString();
        setHasOptionsMenu(true);
        addproduct();
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
        recyclerView2 = v.findViewById(R.id.recycler_home);
        recyclerView2.setHasFixedSize(true);
        layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
        homeAdapter = new HomeAdapter(homelist, getActivity());
        recyclerView2.setAdapter(homeAdapter);
        search.addTextChangedListener(new TextWatcher() {
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
                    return true;
                }
                return false;
            }
        });
    }

    public List<OfferModel> offerModelList() {

        List<OfferModel> offerlist = new ArrayList<>();

        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item3));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item3));
        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item3));
        offerlist.add(new OfferModel(R.drawable.item1));
        offerlist.add(new OfferModel(R.drawable.item2));
        offerlist.add(new OfferModel(R.drawable.item3));
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
    public List<HomeModel> addproduct(){
        HomeModel homeModel_one = new HomeModel("https://gloimg.zafcdn.com/zaful/pdm-product-pic/Clothing/2018/12/21/goods-img/1547683385158389308.jpg",
                "https://gloimg.zafcdn.com/zaful/pdm-product-pic/Clothing/2018/12/21/goods-img/1547683385158389308.jpg", "dress", "a beautiful blue  address for girls ", "$25.99", "XL", "female", "POLO", R.drawable.vender_image1);
        HomeModel homeModel_two = new HomeModel("https://www.kuiu.com/dw/image/v2/AAYP_PRD/on/demandware.static/-/Sites-master-catalog/default/dw7d8ead9b/images/hi-res/Outerwear/RubiconHoodedJacket/Gunmetal/RubiconHooded_GM_FR02_1425x1825.jpg?sw=270&sh=330&sm=fit",
                "https://www.kuiu.com/dw/image/v2/AAYP_PRD/on/demandware.static/-/Sites-master-catalog/default/dw7d8ead9b/images/hi-res/Outerwear/RubiconHoodedJacket/Gunmetal/RubiconHooded_GM_FR02_1425x1825.jpg?sw=270&sh=330&sm=fit","jacket","a comfartable black jacket for boys","$30.00 ","L","male","TownTeam",R.drawable.vender_image2);
        HomeModel homeModel_three = new HomeModel("https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1524509473/cole-haan-DRESSSNEAK0418.jpg?itok=goYT20F6",
                "https://cdn-image.travelandleisure.com/sites/default/files/styles/1600x1000/public/1524509473/cole-haan-DRESSSNEAK0418.jpg?itok=goYT20F6","shoes","a comfartable blue sportive shoes for playing football","$20.00","L","male","Nike",R.drawable.vender_image3);
        HomeModel homeModel_four = new HomeModel("https://c.76.my/Malaysia/nian-jeep-cotton-jacket-men-coat-men-jacket-j8028-aeioumall-1603-08-aeioumall@1.jpg",
                "https://c.76.my/Malaysia/nian-jeep-cotton-jacket-men-coat-men-jacket-j8028-aeioumall-1603-08-aeioumall@1.jpg","jacket","a comfartable black jacket for boys","$30.00 ","XXL","male","Puma",R.drawable.vender_image4);
        HomeModel homeModel_five = new HomeModel("https://di2ponv0v5otw.cloudfront.net/posts/2018/11/10/5be723f9819e9005c39a3991/s_5be72424409c152de27b26e8.jpg",
                "https://di2ponv0v5otw.cloudfront.net/posts/2018/11/10/5be723f9819e9005c39a3991/s_5be72424409c152de27b26e8.jpg","dress","a beautiful blue  address for girls ","$25.99","XXL","female","Addidas",R.drawable.vender_image5);
        HomeModel homeModel_six = new HomeModel("https://www.cartersoshkosh.ca/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw851223ce/productimages/OF160441.jpg?sw=2000",
                "https://www.cartersoshkosh.ca/dw/image/v2/AAMK_PRD/on/demandware.static/-/Sites-carters_master_catalog/default/dw851223ce/productimages/OF160441.jpg?sw=2000","shoes","a comfartable blue sportive shoes for playing football","$20.00","M","female","POLO",R.drawable.vender_image1);
        homeDatabase.dao_home().addproductItem(homeModel_one);
        homeDatabase.dao_home().addproductItem(homeModel_two);
        homeDatabase.dao_home().addproductItem(homeModel_three);
        homeDatabase.dao_home().addproductItem(homeModel_four);
        homeDatabase.dao_home().addproductItem(homeModel_five);
        homeDatabase.dao_home().addproductItem(homeModel_six);
        homelist = homeDatabase.dao_home().get_profile_data();
        return homelist;
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
}
