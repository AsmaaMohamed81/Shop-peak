package com.alatheer.shop_peak.Activities;

import android.content.Intent;
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
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Search_Activity extends AppCompatActivity  {
 android.support.v7.widget.Toolbar toolbar;
    RecyclerView recyclerView_search;
    RecyclerView.LayoutManager layoutManager_search;
    SearchAdapter searchAdapter;
    HomeFragment homeFragment;
    ImageView filter_image;
    LinearLayout linear_filter;
    Intent i;
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
        final String title=i.getStringExtra("title");
        filter_image=findViewById(R.id.filter_img);
        linear_filter=findViewById(R.id.linear_filter);
        recyclerView_search=findViewById(R.id.recycler_home);
        initRecyclerview();
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        linear_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Search_Activity.this,Filter_Activity.class);
                startActivity(i);
            }
        });

    }
    public void initRecyclerview(){
        List<HomeModel>list= (ArrayList<HomeModel>) i.getSerializableExtra("list");
        recyclerView_search.setHasFixedSize(true);
        layoutManager_search=new LinearLayoutManager(this);
        recyclerView_search.setLayoutManager(layoutManager_search);
        searchAdapter = new SearchAdapter(list, this);
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

}