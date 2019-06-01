package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.Sub_product_Adapter;
import com.alatheer.shop_peak.Adapter.main_sub_Adapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

public class Category_Activity extends AppCompatActivity {

    RecyclerView Recyc_sub,Recyc_main;

    int id_main_cat;

    Sub_product_Adapter sub_product_adapter;
    main_sub_Adapter main_sub_adapter;

    ArrayList<list_cats.Subs> subsArrayList;
    ArrayList<HomeModel> homeModelArrayList;


    list_cats list_catss;
    list_cats.Subs subs;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        getdataIntent();
        initView();
    }

    private void getdataIntent() {

        Intent intent=getIntent();

        if (intent!=null){

            id_main_cat=intent.getIntExtra("id_main_cats",1);
            Log.d("asmaa_end", "getdataIntent: "+id_main_cat);
            Toast.makeText(this, ""+id_main_cat, Toast.LENGTH_SHORT).show();
            subsArrayList= (ArrayList<list_cats.Subs>) intent.getSerializableExtra("cats");


        }
    }

    private void initView() {
        Recyc_sub=findViewById(R.id.Recyc_sub);
        Recyc_main=findViewById(R.id.Recyc_main);

        homeModelArrayList=new ArrayList<>();

        getRcycSub();
        getRcycmain();
    }

    private void getRcycmain() {

        Recyc_main.setLayoutManager(new GridLayoutManager(this,1));
        Recyc_main.setHasFixedSize(true);
        main_sub_adapter=new main_sub_Adapter(homeModelArrayList,this,Recyc_main);
        Recyc_main.setAdapter(main_sub_adapter);

        getmaincatsWeb();
    }

    private void getmaincatsWeb() {

        Api.getService()
                .get_all_main_product(id_main_cat)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {

                        if (response.isSuccessful()){

                            homeModelArrayList.addAll(response.body());
                            main_sub_adapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

                    }
                });
    }

    private void getRcycSub() {


//        list_catss=catsArrayList.get(id_main_cat);
//        subsArrayList=list_catss.getSubs();

        Recyc_sub.setLayoutManager(new LinearLayoutManager(this));
        Recyc_sub.setHasFixedSize(true);
        sub_product_adapter=new Sub_product_Adapter(subsArrayList,this);
        Recyc_sub.setAdapter(sub_product_adapter);

    }

    public void list_product_pos(int pos) {

        int id = Integer.parseInt(subsArrayList.get(pos).getId());
        Log.d("asmaaaaaa", "list_product_pos: "+id);
        getSubcatsWeb(id);

    }

    private void getSubcatsWeb(int id) {

        homeModelArrayList.clear();
        main_sub_adapter.notifyDataSetChanged();
        Api.getService()
                .get_all_sub_product(id)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                        if (response.isSuccessful()){

                            if (response.body()!=null) {
                                homeModelArrayList.addAll(response.body());
                                main_sub_adapter.notifyDataSetChanged();


                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

                    }
                });
    }
}
