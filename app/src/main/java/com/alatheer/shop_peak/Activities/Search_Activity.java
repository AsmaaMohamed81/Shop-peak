package com.alatheer.shop_peak.Activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import com.alatheer.shop_peak.Adapter.HomeAdapter;
import com.alatheer.shop_peak.Adapter.SearchAdapter;
import com.alatheer.shop_peak.Fragments.HomeFragment;
//import com.alatheer.shop_peak.Local.HomeDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Search_Activity extends AppCompatActivity  {
 android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView_search;
    RecyclerView.LayoutManager layoutManager_search;
    SearchAdapter searchAdapter;
    HomeFragment homeFragment;
    ImageView filter_image;
    LinearLayout linear_filter;
    Intent i;
    UserModel1 userModel1;
    //HomeDatabase homeDatabase;
    String title;
    List<HomeModel>homeModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_);
        i=getIntent();
        initview();
    }

    private void initview() {
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        homeFragment=new HomeFragment();
        title = i.getStringExtra("sanf_name");
        homeModelList = (List<HomeModel>) i.getExtras().getSerializable("list");
        recyclerView_search=findViewById(R.id.recycler_home);
        //homeDatabase = Room.databaseBuilder(getApplicationContext(), HomeDatabase.class, "home_db").allowMainThreadQueries().build();
        initRecyclerview();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    public void initRecyclerview(){
        //List<HomeModel> list = homeDatabase.dao_home().getdatasearch(title);
        recyclerView_search.setHasFixedSize(true);
        layoutManager_search=new LinearLayoutManager(this);
        recyclerView_search.setLayoutManager(layoutManager_search);
         searchAdapter = new SearchAdapter(homeModelList, this);
        recyclerView_search.setAdapter(searchAdapter);
    }



    /*@Override
     public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem search_item=menu.findItem(R.id.action_search);
        SearchView searchView= (SearchView) search_item.getActionView();
        searchView.setQueryHint(Html.fromHtml("<font color = #de9133 >"+"search for an item"+"</font>"));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home :
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendHomeItem(String[] images, List<Item> itemList, String sanf_name, String price, String sanf_id, String rating, String store_id, String[] colors, String price_before_discount) {
        Bundle bundle=new Bundle();
        bundle.putStringArray("homeimage", images);
        bundle.putSerializable("itemlist", (Serializable) itemList);
        //bundle.putString("details", details);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("title", sanf_name);
        intent.putExtra("price", price);
        intent.putExtra("price_before_dis",price_before_discount);
        intent.putExtra("id", sanf_id);
        intent.putExtra("rate", rating);
        intent.putExtra("user_id", Integer.parseInt(userModel1.getId()));
        intent.putExtra("color",colors);
        startActivity(intent);
        Animatoo.animateInAndOut(Search_Activity.this);

    }
}