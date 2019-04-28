package com.alatheer.shop_peak.Fragments;

import android.app.SearchManager;
import android.arch.persistence.room.Room;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceFragment;
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
    RecyclerView recyclerView,recyclerView2,recyclerView3;
    RecyclerView.LayoutManager layoutManager,layoutManager2;
    HomeAdapter homeAdapter;
    OfferAdapter offerAdapter;
    EditText search;
    List<HomeModel>  homelist;
    HomeDatabase homeDatabase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {

        search = v.findViewById(R.id.txt_search);
        homeDatabase= Room.databaseBuilder(getApplicationContext(),HomeDatabase.class,"home_db").allowMainThreadQueries().build();
        //final String title=search.getText().toString();
        setHasOptionsMenu(true);
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
        recyclerView2=v.findViewById(R.id.recycler_home);
        recyclerView2.setHasFixedSize(true);
        layoutManager2=new LinearLayoutManager(getActivity());
        recyclerView2.setLayoutManager(layoutManager2);
         homeAdapter = new HomeAdapter(homeDatabase.dao_home().get_profile_data(), getActivity());
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
                 if(actionId==EditorInfo.IME_ACTION_SEARCH){
                     Intent i=new Intent(getActivity(),Search_Activity.class);
                     i.putExtra("title",search.getText().toString());
                     i.putExtra("list",(Serializable) homelist);
                     startActivity(i);
                     return true;
                 }
                 return false;
             }
         });

    }

    public List<OfferModel> offerModelList (){

        List<OfferModel>  offerlist = new ArrayList<>();

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

    /*public void homeModelList (){

        homelist = new ArrayList<>();
        homelist.add(new HomeModel(new int[]{R.drawable.item2,R.drawable.item2,R.drawable.item2},"dress","a beautiful blue  address for girls ","$25.99","XL","female"));
        homelist.add(new HomeModel(new int[]{R.drawable.item1,R.drawable.item1},"jacket","a comfartable black jacket for boys","$30.00 ","L","male"));
        homelist.add(new HomeModel(new int[]{R.drawable.item3,R.drawable.item3},"shoes","a comfartable blue sportive shoes for playing football","$20.00","L","male"));
        homelist.add(new HomeModel(new int[]{R.drawable.item1,R.drawable.item1},"jacket","a comfartable black jacket for boys","$30.00 ","XXL","male"));
        homelist.add(new HomeModel(new int[]{R.drawable.item2,R.drawable.item2},"dress","a beautiful blue  address for girls ","$25.99","XXL","female"));
        homelist.add(new HomeModel(new int[]{R.drawable.item3,R.drawable.item3},"shoes","a comfartable blue sportive shoes for playing football","$20.00","M","female"));

    }*/

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
