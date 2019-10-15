package com.alatheer.shop_peak.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.SearchAdapter;
import com.alatheer.shop_peak.Fragments.HomeFragment;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import com.alatheer.shop_peak.Local.HomeDatabase;

public class Search_Activity extends AppCompatActivity {
    Toolbar toolbar;
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
    String user_id;

    protected void attachBaseContext(Context newBase) {
        Paper.init(newBase);
        String lang = Paper.book().read("language");
        if (Paper.book().read("language").equals("ar"))
        {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.AR_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        }else
        {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.EN_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }

        super.attachBaseContext(CalligraphyContextWrapper.wrap(LanguageHelper.onAttach(newBase, lang)));



    }


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

    public void sendHomeItem(String[] images, List<Item> itemList, String sanf_name,String details ,String price, String sanf_id, String rating, String store_id, String[] colors, String price_before_discount) {
        Bundle bundle=new Bundle();
        bundle.putStringArray("homeimage", images);
        bundle.putSerializable("itemlist", (Serializable) itemList);
        bundle.putString("details", details);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtras(bundle);
        intent.putExtra("title", sanf_name);
        intent.putExtra("itemlist", (Serializable) itemList);
        intent.putExtra("details", details);
        intent.putExtra("price", price);
        intent.putExtra("price_before_dis",price_before_discount);
        intent.putExtra("id", sanf_id);
        intent.putExtra("rate", rating);
        intent.putExtra("store_id",store_id);
        intent.putExtra("color",colors);
        startActivity(intent);
        Animatoo.animateInAndOut(Search_Activity.this);

    }

    public void addfavPos(int pos) {

        String sanf_id = homeModelList.get(pos).id;

        if (userModel1 != null){
            user_id = userModel1.getId();
        }

        Log.d("Mainasmaaa", "favPos: "+sanf_id);
        Log.d("Mainasmaaa", "favPos: "+user_id);



        Api.getService()
                .add_to_favourite(user_id,sanf_id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                    }
                });
    }

    public void deletfavPos(int pos) {

        String sanf_id=homeModelList.get(pos).id;

        if (userModel1!=null) {
            user_id = userModel1.getId();
        }else {

            Toast.makeText(this, "You Should SignUp First !!", Toast.LENGTH_SHORT).show();
        }

        Api.getService()
                .delet_to_favourite(user_id,sanf_id)
                .enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {

                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {

                    }
                });
    }
}