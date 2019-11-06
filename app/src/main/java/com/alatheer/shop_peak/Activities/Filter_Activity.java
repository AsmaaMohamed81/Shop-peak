package com.alatheer.shop_peak.Activities;

import android.content.BroadcastReceiver;
import android.content.Context;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.alatheer.shop_peak.Adapter.FilterAdapter;
import com.alatheer.shop_peak.Adapter.FilterAdapterDetails;
import com.alatheer.shop_peak.Model.FilterModel;
import com.alatheer.shop_peak.Model.FilterModelDetails;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Filter_Activity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_filter,recyclerView_filterdetails;
    RecyclerView.LayoutManager layoutManager_filter,layoutManager_filterdetails;
    FilterAdapter filterAdapter;
    FilterAdapterDetails filterAdapterDetails;
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
        setContentView(R.layout.activity_filter_);
        mSong = null ;

        if(getIntent().getExtras() != null){
            for(String key : getIntent().getExtras().keySet()){
                String msg = getIntent().getExtras().getString(key);

            }
        }
        initview();
    }

    private void initview() {
        recyclerView_filter=findViewById(R.id.filter_list);
        recyclerView_filterdetails=findViewById(R.id.filter_list_details);
        initRecyclerview();

    }
    public void initRecyclerview(){
        recyclerView_filter.setHasFixedSize(true);
        recyclerView_filterdetails.setHasFixedSize(true);
        // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Filter_Details_Fragment()).commit();
        toolbar=findViewById(R.id.toolbar);
        layoutManager_filter=new LinearLayoutManager(this);
        layoutManager_filterdetails=new LinearLayoutManager(this);
        recyclerView_filter.setLayoutManager(layoutManager_filter);
        recyclerView_filterdetails.setLayoutManager(layoutManager_filterdetails);
        filterAdapter=new FilterAdapter(getFilterModelist(),this);
        recyclerView_filter.setAdapter(filterAdapter);
        filterAdapterDetails=new FilterAdapterDetails(filterModelDetailsList(),this);
        recyclerView_filterdetails.setAdapter(filterAdapterDetails);

    }
    public List<FilterModel>getFilterModelist(){
        List<FilterModel>filterModelList=new ArrayList<>();
        filterModelList.add(new FilterModel("Brand",filterModelDetailsList()));
        filterModelList.add(new FilterModel("Price",filterModelDetailsList2()));
        filterModelList.add(new FilterModel("Size",filterModelDetailsList3()));
        filterModelList.add(new FilterModel("Color",filterModelDetailsList4()));
        filterModelList.add(new FilterModel("Gender",filterModelDetailsList5()));
        return filterModelList;
    }
    public List<FilterModelDetails>filterModelDetailsList(){
        List<FilterModelDetails>filterModelDetailsList=new ArrayList<>();
        filterModelDetailsList.add(new FilterModelDetails("Addidas"));
        filterModelDetailsList.add(new FilterModelDetails("Nike"));
        filterModelDetailsList.add(new FilterModelDetails("Puma"));
        return filterModelDetailsList;
    }
    private List<FilterModelDetails>filterModelDetailsList2(){
        List<FilterModelDetails>filterModelDetailsList=new ArrayList<>();
        filterModelDetailsList.add(new FilterModelDetails("30.00$"));
        filterModelDetailsList.add(new FilterModelDetails("50.00$"));
        filterModelDetailsList.add(new FilterModelDetails("100.00$"));
        return filterModelDetailsList;
    }
    private List<FilterModelDetails>filterModelDetailsList3(){
        List<FilterModelDetails>filterModelDetailsList=new ArrayList<>();
        filterModelDetailsList.add(new FilterModelDetails("M"));
        filterModelDetailsList.add(new FilterModelDetails("S"));
        filterModelDetailsList.add(new FilterModelDetails("L"));
        filterModelDetailsList.add(new FilterModelDetails("XL"));
        filterModelDetailsList.add(new FilterModelDetails("XXL"));
        return filterModelDetailsList;
    }
    private List<FilterModelDetails>filterModelDetailsList4(){
        List<FilterModelDetails>filterModelDetailsList=new ArrayList<>();
        filterModelDetailsList.add(new FilterModelDetails("black"));
        filterModelDetailsList.add(new FilterModelDetails("blue"));
        filterModelDetailsList.add(new FilterModelDetails("white"));
        return filterModelDetailsList;
    }
    private List<FilterModelDetails>filterModelDetailsList5(){
        List<FilterModelDetails>filterModelDetailsList=new ArrayList<>();
        filterModelDetailsList.add(new FilterModelDetails("male"));
        filterModelDetailsList.add(new FilterModelDetails("female"));
        filterModelDetailsList.add(new FilterModelDetails("child"));
        return filterModelDetailsList;
    }

   /* @Override
    public void onTextClick(List<FilterModelDetails> filterModelDetailsList) {
        filterAdapterDetails=new FilterAdapterDetails(filterModelDetailsList,this);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(layoutManager2);
        recyclerView2.setAdapter(filterAdapterDetails);
        //recyclerView2.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

    }*/

    public void sendlist(List<FilterModelDetails> filterModelDetailsList) {
        filterAdapterDetails=new FilterAdapterDetails(filterModelDetailsList,this);
        recyclerView_filterdetails.setHasFixedSize(true);
        recyclerView_filterdetails.setLayoutManager(layoutManager_filterdetails);
        recyclerView_filterdetails.setAdapter(filterAdapterDetails);
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
        mSong = MediaPlayer.create(Filter_Activity.this,R.raw.music);
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
