package com.alatheer.shop_peak.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Filter_Activity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Fragments.Filter_Details_Fragment;
import com.alatheer.shop_peak.Model.FilterModel;
import com.alatheer.shop_peak.Model.FilterModelDetails;
import com.alatheer.shop_peak.R;

import java.io.Serializable;
import java.nio.file.DirectoryStream;
import java.util.List;

/**
 * Created by M.Hamada on 13/04/2019.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {
    List<FilterModel>filterModelList;
    Context context;
    Filter_Activity activity;
    private OnTextClickListener onTextClickListener;
    public FilterAdapter(List<FilterModel> filterModelList, Context context,Filter_Activity activity,OnTextClickListener onTextClickListener) {
        this.filterModelList = filterModelList;
        this.context = context;
        this.activity=activity;
        this.onTextClickListener=onTextClickListener;
    }

    @NonNull
    @Override
    public FilterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.filter_raw,parent,false);
       return new FilterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterHolder holder, final int position) {
     holder.filter_text.setText(filterModelList.get(position).getTitle());
     holder.filter_text.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             List<FilterModelDetails> filterModelDetailsList = filterModelList.get(position).getFilterModelDetailsList();
             onTextClickListener.onTextClick(filterModelDetailsList);
         }
     });

    }

    @Override
    public int getItemCount() {
        return filterModelList.size();
    }

    class FilterHolder extends RecyclerView.ViewHolder{
        TextView filter_text;
      //  RecyclerView recyclerView;
        public FilterHolder(View itemView) {
            super(itemView);
            filter_text=itemView.findViewById(R.id.filter_text);
          //  recyclerView=itemView.findViewById(R.id.filter_list_details);
        }
    }
}
