package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.FilterModelDetails;
import com.alatheer.shop_peak.R;

import java.util.List;

/**
 * Created by M.Hamada on 13/04/2019.
 */

public class FilterAdapterDetails extends RecyclerView.Adapter<FilterAdapterDetails.FilterHolderDetails> {
    List<FilterModelDetails>filterModelDetailsList;
    Context context;
    private Boolean accepted = false;
    public FilterAdapterDetails(List<FilterModelDetails> filterModelDetailsList, Context context) {
        this.filterModelDetailsList = filterModelDetailsList;
        this.context = context;
    }


    @NonNull
    @Override
    public FilterHolderDetails onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.filter_raw_details,parent,false);
        return new FilterHolderDetails(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FilterHolderDetails holder, int position) {
        holder.textView.setText(filterModelDetailsList.get(position).getNames());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    holder.checkBox.setButtonDrawable(R.drawable.ic_check);
                    accepted = true;
                } else {
                    holder.checkBox.setButtonDrawable(R.drawable.ic_check_gray);
                    accepted = false;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return filterModelDetailsList.size() ;
    }

    class FilterHolderDetails extends RecyclerView.ViewHolder{
     TextView textView;
     CheckBox checkBox;
        public FilterHolderDetails(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text_filter_details);
            checkBox=itemView.findViewById(R.id.check_box);
        }
    }
}
