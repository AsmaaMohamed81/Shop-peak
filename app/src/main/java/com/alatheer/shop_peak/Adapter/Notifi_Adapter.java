package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.NotificationModel;
import com.alatheer.shop_peak.R;

import java.util.List;



public class Notifi_Adapter extends RecyclerView.Adapter<Notifi_Adapter.Follow_Holder> {
    List<NotificationModel> followList;
    Context context;

    public Notifi_Adapter(List<NotificationModel> followList, Context context) {
        this.followList = followList;
        this.context = context;
    }

    @NonNull
    @Override
    public Follow_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_row,viewGroup,false);
        return new Follow_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Follow_Holder follow_holder, int i) {
      follow_holder.details.setText(followList.get(i).getMessage());
    }

    @Override
    public int getItemCount() {
        return followList.size();
    }


    class Follow_Holder extends RecyclerView.ViewHolder{
        TextView details;
        public Follow_Holder(View itemView) {
            super(itemView);
            details=itemView.findViewById(R.id.notification_details);
        }
    }
}
