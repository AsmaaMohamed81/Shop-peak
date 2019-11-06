package com.alatheer.shop_peak.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.Sub_product_Adapter;
import com.alatheer.shop_peak.Adapter.main_sub_Adapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Category_Activity extends AppCompatActivity {

    RecyclerView Recyc_sub,Recyc_main;

    int id_main_cat;

    Sub_product_Adapter sub_product_adapter;
    main_sub_Adapter main_sub_adapter;

    ArrayList<list_cats.Subs> subsArrayList;
    ArrayList<HomeModel> homeModelArrayList;


    list_cats list_catss;
    list_cats.Subs subs;
    MediaPlayer mSong;

    private ProgressBar progressBar;
    private TextView txt_no;

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
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        mSong = null;
        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.shop_peak_FCM-MESSAGE"));
        if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String msg = getIntent().getExtras().getString(key);

            }
        }
        getdataIntent();
        initView();
    }

    private void getdataIntent() {

        Intent intent=getIntent();

        if (intent!=null){

            id_main_cat=intent.getIntExtra("id_main_cats",1);
            Log.d("asmaa_end", "getdataIntent: "+id_main_cat);
//            Toast.makeText(this, ""+id_main_cat, Toast.LENGTH_SHORT).show();
            subsArrayList= (ArrayList<list_cats.Subs>) intent.getSerializableExtra("cats");


        }
    }

    private void initView() {
        Recyc_sub=findViewById(R.id.Recyc_sub);
        Recyc_main=findViewById(R.id.Recyc_main);

        homeModelArrayList=new ArrayList<>();

        progressBar = findViewById(R.id.progBar);
        txt_no = findViewById(R.id.tv_no);



        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);



        getRcycSub();
        getRcycmain();
    }

    private void getRcycmain() {

        Recyc_main.setLayoutManager(new GridLayoutManager(this,1));
        Recyc_main.setHasFixedSize(true);
        main_sub_adapter=new main_sub_Adapter(homeModelArrayList,this);
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

                            progressBar.setVisibility(View.GONE);

                            if (response.body().size() > 0) {
                                homeModelArrayList.addAll(response.body());
                                main_sub_adapter.notifyDataSetChanged();
                                txt_no.setVisibility(View.GONE);

                            }
                            else {

                                txt_no.setVisibility(View.VISIBLE);

                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(Category_Activity.this, "Check You Internet", Toast.LENGTH_SHORT).show();
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

    public void sendHomeItem(String[] image_resources, List<Item> itemList, String sanf_name, String details, String price, String sanf_id, String rating, String store_id, String[] colors, String price_before_discount, String like) {
        Bundle bundle=new Bundle();
        bundle.putStringArray("homeimage", image_resources);
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
        intent.putExtra("color",colors);
        startActivity(intent);
        Animatoo.animateInAndOut(Category_Activity.this);
    }
    private BroadcastReceiver mhandler = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("message");
            //message.setText(msg);

            showNotificationInADialog(msg);
        }
    };
    private void showNotificationInADialog(final String message) {
        final android.app.AlertDialog gps_dialog = new android.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .create();
        stopPlaying();
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(message);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        mSong = MediaPlayer.create(Category_Activity.this,R.raw.music);
        mSong.setLooping(true);
        mSong.start();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                //mSong.pause();
                //mSong.seekTo(0);
                stopPlaying();
            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }

    private void stopPlaying() {
        if(mSong != null){
            mSong.stop();
            mSong.release();
            mSong = null;
        }
    }
}
