package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Activities.Vender_Signup_Activity;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.Model.Tasnefat;
import com.alatheer.shop_peak.R;

import java.util.List;

public class TasnefAdapter extends RecyclerView.Adapter<TasnefAdapter.Holder>{

    Context context;

    List<Tasnefat> tasnefatList;


    Vender_Signup_Activity vender_signup_activity;


    public TasnefAdapter(Context context, List<Tasnefat> tasnefatList) {
        this.context = context;
        this.tasnefatList = tasnefatList;
        vender_signup_activity=(Vender_Signup_Activity) context;


    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_txt,parent,false);
        return new TasnefAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {

        final Tasnefat tasnefat=tasnefatList.get(position);
        holder.Bind(tasnefat);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();
                vender_signup_activity.pos_tasnefat(pos);




            }
        });
    }

    @Override
    public int getItemCount() {
        return tasnefatList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

        public void Bind(Tasnefat tasnefat) {
            textView.setText(tasnefat.getName());
        }
    }
    }
