package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.R;

import java.util.List;

public class governAdapter extends RecyclerView.Adapter<governAdapter.Holder>{

    Context context;

    List<Govern> governsList;

    Signup_Activity signup_activity;

    public governAdapter(Context context, List<Govern> governsList) {
        this.context = context;
        this.governsList = governsList;
        signup_activity= (Signup_Activity) context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_txt,parent,false);
        return new governAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {

        Govern govern=governsList.get(position);
        holder.Bind(govern);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();
                signup_activity.pos_govern(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return governsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

        public void Bind(Govern govern) {
            textView.setText(govern.getName());
        }
    }
    }
