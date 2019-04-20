package com.alatheer.shop_peak.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alatheer.shop_peak.Adapter.FilterAdapter;
import com.alatheer.shop_peak.Adapter.FilterAdapterDetails;
import com.alatheer.shop_peak.Adapter.OnTextClickListener;
import com.alatheer.shop_peak.Model.FilterModel;
import com.alatheer.shop_peak.Model.FilterModelDetails;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;

public class Filter_Activity extends AppCompatActivity{
    Toolbar toolbar;
    RecyclerView recyclerView_filter,recyclerView_filterdetails;
    RecyclerView.LayoutManager layoutManager_filter,layoutManager_filterdetails;
    FilterAdapter filterAdapter;
    FilterAdapterDetails filterAdapterDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_);
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
        recyclerView_filterdetails.setLayoutManager(layoutManager_filter);
        recyclerView_filterdetails.setAdapter(filterAdapterDetails);
    }
}
