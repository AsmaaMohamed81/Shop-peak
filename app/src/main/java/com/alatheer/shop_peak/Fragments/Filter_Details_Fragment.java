package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Adapter.FilterAdapterDetails;
import com.alatheer.shop_peak.Model.FilterModelDetails;
import com.alatheer.shop_peak.R;

import java.util.List;


public class Filter_Details_Fragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    FilterAdapterDetails filterAdapterDetails;
    List<FilterModelDetails> filterModelDetailsList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_filter__details_, container, false);
        initview(v);
        return v;
    }

    private void initview(View v) {
        recyclerView=v.findViewById(R.id.filter_list_details);
        recyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
         filterAdapterDetails=new FilterAdapterDetails(getlist(),getContext());
         recyclerView.setAdapter(filterAdapterDetails);

    }
    private List<FilterModelDetails> getlist() {
        Bundle bundle=getArguments();
        filterModelDetailsList= (List<FilterModelDetails>) bundle.getSerializable("aaa");
        return filterModelDetailsList;
    }

}
