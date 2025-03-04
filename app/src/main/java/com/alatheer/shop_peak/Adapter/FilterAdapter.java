package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Filter_Activity;
import com.alatheer.shop_peak.Model.FilterModel;
import com.alatheer.shop_peak.Model.FilterModelDetails;
import com.alatheer.shop_peak.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by M.Hamada on 13/04/2019.
 */

public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.FilterHolder> {
    List<FilterModel>filterModelList;
    Context context;
    Filter_Activity filter_activity;
    public FilterAdapter(List<FilterModel> filterModelList, Context context) {
        this.filterModelList = filterModelList;
        this.context = context;
        this.filter_activity= (Filter_Activity) context;
    }


    @Override
    public FilterHolder onCreateViewHolder( ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.filter_raw,parent,false);
       return new FilterHolder(view);
    }

    @Override
    public void onBindViewHolder( final FilterHolder holder, final int position) {
     holder.filter_text.setText(filterModelList.get(position).getTitle());
     holder.filter_layout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             List<FilterModelDetails> filterModelDetailsList = filterModelList.get(position).getFilterModelDetailsList();
             filter_activity.sendlist(filterModelDetailsList);


         }
     });

    }

    @Override
    public int getItemCount() {
        return filterModelList.size();
    }

    class FilterHolder extends RecyclerView.ViewHolder{
        TextView filter_text;
        RelativeLayout filter_layout;
        public FilterHolder(View itemView) {
            super(itemView);
            filter_text=itemView.findViewById(R.id.filter_text);
            filter_layout=itemView.findViewById(R.id.filter_layout);
        }
    }
}
