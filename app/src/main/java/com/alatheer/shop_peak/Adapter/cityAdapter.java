package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.R;

import java.util.List;

public class cityAdapter extends RecyclerView.Adapter<cityAdapter.Holder>{

    Context context;

    List<City> cityList;

    Signup_Activity signup_activity;

    public cityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;
        signup_activity= (Signup_Activity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_txt,parent,false);
        return new cityAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {

        City city=cityList.get(position);
        holder.Bind(city);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();
                signup_activity.pos_city(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

        public void Bind(City city) {
            textView.setText(city.getName());
        }
    }
    }
