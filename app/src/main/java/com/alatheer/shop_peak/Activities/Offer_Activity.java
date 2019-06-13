package com.alatheer.shop_peak.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alatheer.shop_peak.Adapter.AllOfferAdapter;
import com.alatheer.shop_peak.Adapter.HomeAdapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Offer_Activity extends AppCompatActivity {

    RecyclerView recycler_offer;

    AllOfferAdapter allOfferAdapter;

    List<HomeModel> homeModelList;

    String offer_id;
    UserModel1 userModel1;
    MySharedPreference mprefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_);
        getDataIntent();
        initview();
    }

    private void getDataIntent() {

        Intent intent=getIntent();

        if (intent!=null){
            offer_id=intent.getStringExtra("offer_id");

        }
    }

    private void initview() {
        recycler_offer=findViewById(R.id.recycler_offer);
        homeModelList=new ArrayList<>();
        mprefs = MySharedPreference.getInstance();
        userModel1= mprefs.Get_UserData(this);
        initrecycle();
        get_offer_products(offer_id);

    }

    private void get_offer_products(String offer_id) {

        Api.getService()
                .get_offer_products(offer_id)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size()>0){

                                homeModelList.addAll(response.body());
                                allOfferAdapter.notifyDataSetChanged();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

                    }
                });
    }

    private void initrecycle() {
        recycler_offer.setLayoutManager(new LinearLayoutManager(this));
        recycler_offer.setHasFixedSize(true);
        allOfferAdapter =new AllOfferAdapter(homeModelList,this);
        recycler_offer.setAdapter(allOfferAdapter);
    }

    public void sendHomeItem(String[] images_resources, List<Item> itemList, String sanf_name, String details, String price, String sanf_id, String rating, String store_id, String[] colors, String price_before_discount, String like) {
        Bundle bundle=new Bundle();
        bundle.putStringArray("homeimage", images_resources);
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
        intent.putExtra("like",like);
        Log.v("gggg",store_id);
        String user_id = userModel1.getId();
        if (user_id!=null) {
            intent.putExtra("user_id", Integer.parseInt(user_id));

        }
        intent.putExtra("color",colors);
        startActivity(intent);
        Animatoo.animateInAndOut(Offer_Activity.this);
    }
}
