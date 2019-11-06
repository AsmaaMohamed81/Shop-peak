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

import com.alatheer.shop_peak.Adapter.AllOfferAdapter;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Offer_Activity extends AppCompatActivity {

    RecyclerView recycler_offer;

    AllOfferAdapter allOfferAdapter;

    List<HomeModel> homeModelList;

    String offer_id,title,Fvender_image,Fvender_name;
    UserModel1 userModel1;
    MySharedPreference mprefs;

    TextView offer_title;

    private ProgressBar progressBar;
    private TextView txt_no,txt_name_vonder;
    CircleImageView img_c;
    MediaPlayer mSong;
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
        setContentView(R.layout.activity_offer_);
        mSong = MediaPlayer.create(Offer_Activity.this,R.raw.music);
        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.shop_peak_FCM-MESSAGE"));
        if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String msg = getIntent().getExtras().getString(key);

            }
        }
        getDataIntent();
        initview();
    }

    private void getDataIntent() {

        Intent intent=getIntent();

        if (intent!=null){
            offer_id=intent.getStringExtra("offer_id");
            title=intent.getStringExtra("title");



        }
    }

    private void initview() {
        recycler_offer=findViewById(R.id.recycler_offer);
        offer_title=findViewById(R.id.offer_title);
        txt_name_vonder=findViewById(R.id.txt_name);
        img_c=findViewById(R.id.img_c);

        offer_title.setText(title);


        progressBar = findViewById(R.id.progBar);
        txt_no = findViewById(R.id.tv_no);



        progressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN);




        homeModelList=new ArrayList<>();
        mprefs = MySharedPreference.getInstance();

        userModel1= mprefs.Get_UserData(this);
        initrecycle();
        get_offer_products(offer_id);

        txt_name_vonder.setText(Fvender_name);
        Picasso.with(this).load(Fvender_image).into(img_c);

    }

    private void get_offer_products(String offer_id) {

        Api.getService()
                .get_offer_products(offer_id)
                .enqueue(new Callback<List<HomeModel>>() {
                    @Override
                    public void onResponse(Call<List<HomeModel>> call, Response<List<HomeModel>> response) {
                        if (response.isSuccessful()){
                            progressBar.setVisibility(View.GONE);

                            if (response.body().size()>0){

                                homeModelList.addAll(response.body());
                                allOfferAdapter.notifyDataSetChanged();
                                txt_no.setVisibility(View.GONE);
                            }else {

                                txt_no.setVisibility(View.VISIBLE);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<List<HomeModel>> call, Throwable t) {

                        progressBar.setVisibility(View.GONE);

                    }
                });
    }

    private void initrecycle() {
        recycler_offer.setLayoutManager(new LinearLayoutManager(this));
        recycler_offer.setHasFixedSize(true);
        allOfferAdapter =new AllOfferAdapter(homeModelList,this);
        recycler_offer.setAdapter(allOfferAdapter);
    }

    public void sendHomeItem(String[] images_resources, List<Item> itemList, String sanf_name, String details, String price, String sanf_id, String rating, String store_id, String[] colors, String price_before_discount, String like,String vender_name,String vender_image) {
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
        if (userModel1!=null){
        String user_id = userModel1.getId();
        if (user_id!=null) {
            intent.putExtra("user_id", Integer.parseInt(user_id));

        }}
        intent.putExtra("color",colors);
        startActivity(intent);
        Animatoo.animateInAndOut(Offer_Activity.this);
    }

    public void dataVonder(String vender_name, String vender_image) {
        Fvender_image=vender_image;
        Fvender_name=vender_name;

        Log.d("asmmaa", "dataVonder: "+vender_name);

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

        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(message);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        mSong.start();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                mSong.pause();
                mSong.seekTo(0);
            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
    }
}
