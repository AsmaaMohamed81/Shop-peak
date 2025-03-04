package com.alatheer.shop_peak.Activities;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Adapter.BasketAdapter;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.Pill;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;

import java.io.Serializable;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Basket_Activity extends AppCompatActivity {
    Toolbar toolbar;
    ImageView image_title;
    TextView text_title,txt_no_data;
    RecyclerView recyclerView_basket;
    GridLayoutManager basket_manager;
    BasketAdapter basketAdapter;
    MyAppDatabase myAppDatabase;
    Favorite_Database favorite_database;
    List<OrderItemList>basketModelList;
    Button add;
    Button add_lat_lon;
    MySharedPreference mPrefs;
    UserModel1 userModel1;
    private String lat,lon;
    int price,count_of_item;
    String sum;
    int total;
    TextView txt_total;
    long pill_num;
    String name,USER_ID,address,phone,type;
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
        setContentView(R.layout.activity_basket_);
        mSong = null ;

        LocalBroadcastManager.getInstance(this).registerReceiver(mhandler,new IntentFilter("com.alatheer.shop_peak_FCM-MESSAGE"));
        if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String msg = getIntent().getExtras().getString(key);

            }
        }
        initview();
    }

    private void initview() {
        recyclerView_basket=findViewById(R.id.basket_recycler);
        text_title=findViewById(R.id.basket_title);
        text_title.setText("Basket");
        image_title=findViewById(R.id.back_image);
        add = findViewById(R.id.add);
        txt_total = findViewById(R.id.txt_total);
        txt_no_data = findViewById(R.id.txt__no_data);
        myAppDatabase= Room.databaseBuilder(getApplicationContext(),MyAppDatabase.class,"myorders_db").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(getApplicationContext(),Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
        initRecyclerview();
        mPrefs =MySharedPreference.getInstance();
        userModel1 = mPrefs.Get_UserData(Basket_Activity.this);

        if (userModel1!=null) {
              name = userModel1.getFull_name();
              USER_ID = userModel1.getId();
              address = userModel1.getAddress();
              phone = userModel1.getPhone();
              type = userModel1.getType();

        }
        if(basketModelList.size()==0){
            txt_no_data.setVisibility(View.VISIBLE);
        }
        image_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (userModel1==null){
                    CreateDialog();

                }else {
               // BasketModel2 basketModel2 =new BasketModel2(type,basketModelList,USER_ID,name,address
                //,lat,lon,phone);

                if(basketModelList.size()>0){


                                Intent intent = new Intent(Basket_Activity.this,MapsActivity.class);
                                intent.putExtra("type",type);
                                intent.putExtra("user_id",USER_ID);
                                intent.putExtra("name",name);
                                intent.putExtra("address",address);
                                intent.putExtra("flag",1);
                                intent.putExtra("list",(Serializable) basketModelList);
                                intent.putExtra("phone",phone);
                                intent.putExtra("pill_num",pill_num);
                                Log.v("pill_num2",pill_num+"");
                                startActivity(intent);
                            }



                else {
                    Toast.makeText(Basket_Activity.this,"there is no product in basket",Toast.LENGTH_LONG).show();
                }


                //basketModel2.withOrderItemList(myAppDatabase.dao().getdata());
                /*Api.getService().add_to_basket(basketModel2).enqueue(new Callback<RatingModel2>() {
                    @Override
                    public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                       Log.v("llll",response.message());
                    }

                    @Override
                    public void onFailure(Call<RatingModel2> call, Throwable t) {
                    Log.v("eeee",t.getMessage());
                    }
                });*/
            }}
        });

    }
    public void initRecyclerview(){
         basketModelList=myAppDatabase.dao().getdata();
         recyclerView_basket.setHasFixedSize(true);
         basket_manager=new GridLayoutManager(this,2);
         recyclerView_basket.setLayoutManager(basket_manager);
         basketAdapter=new BasketAdapter(this,basketModelList);
         recyclerView_basket.setAdapter(basketAdapter);
     }

    public void senddata(final int pos,int count) {
        OrderItemList orderItemList = new OrderItemList();
        orderItemList.withSanfAmount(count+"");
        if(count>0){
            orderItemList.withSanfIdFk(basketModelList.get(pos).sanfIdFk);
            orderItemList.withSanfPrice(basketModelList.get(pos).sanfPrice);
            orderItemList.withStoreIdFk(basketModelList.get(pos).storeIdFk);
            orderItemList.setSanfImage(basketModelList.get(pos).getSanfImage());
            orderItemList.setSanfTitle(basketModelList.get(pos).sanfTitle);
            myAppDatabase.dao().editproduct(orderItemList);
            total = total+(price*count_of_item);
            txt_total.setText("TOTAL : "+ total+"");
            finish();
            startActivity(getIntent());
        }else {

            myAppDatabase.dao().Delete_Item(Integer.parseInt(basketModelList.get(pos).sanfIdFk));
            total = total-(price*count_of_item);
            txt_total.setText("TOTAL : "+ total+"");
            finish();
            startActivity(getIntent());
        }

    }


    public void senddata2(int position) {
        myAppDatabase.dao().Delete_Item(Integer.parseInt(basketModelList.get(position).sanfIdFk));
        price = Integer.parseInt(basketModelList.get(position).sanfPrice);
        count_of_item = Integer.parseInt(basketModelList.get(position).sanfAmount);
        total = total-(price*count_of_item);
        txt_total.setText("TOTAL : "+ total+"");
        finish();
        startActivity(getIntent());

    }

    public void sendBasketData(int position) {
        price = Integer.parseInt(basketModelList.get(position).sanfPrice);
        count_of_item = Integer.parseInt(basketModelList.get(position).sanfAmount);
        total = total + price * count_of_item;
        txt_total.setText("TOTAL : "+ total+"");
    }

    private void CreateDialog() {

        final android.app.AlertDialog gps_dialog = new android.app.AlertDialog.Builder(this)
                .setCancelable(false)
                .create();
        stopPlaying();
        View view = LayoutInflater.from(this).inflate(R.layout.custom_dialog, null);
        TextView tv_msg = view.findViewById(R.id.tv_msg);
        tv_msg.setText(R.string.SH_Log);
        Button doneBtn = view.findViewById(R.id.doneBtn);
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                Intent intent = new Intent(Basket_Activity.this, IntroActivity.class);
                startActivity(intent);

            }
        });

        gps_dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        gps_dialog.setView(view);
        gps_dialog.setCanceledOnTouchOutside(false);
        gps_dialog.show();
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
        mSong = MediaPlayer.create(Basket_Activity.this,R.raw.music);
        mSong.setLooping(true);
        mSong.start();
        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps_dialog.dismiss();
                stopPlaying();
                //mSong.pause();
                //mSong.seekTo(0);
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
