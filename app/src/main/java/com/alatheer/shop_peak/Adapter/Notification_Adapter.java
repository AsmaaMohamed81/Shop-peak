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

/**
 * Created by M.Hamada on 06/05/2019.
 */

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.Notification_Holder> {
    List<NotificationModel>notificationModelList;
    Context context;

    public Notification_Adapter(List<NotificationModel> notificationModelList, Context context) {
        this.notificationModelList = notificationModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Notification_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.notification_row,parent,false);
        return new Notification_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Notification_Holder holder, int position) {
     holder.title.setText(notificationModelList.get(position).getTitle());
     holder.details.setText(notificationModelList.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return notificationModelList.size();
    }

    class Notification_Holder extends RecyclerView.ViewHolder{
        TextView title;
        TextView details;
        public Notification_Holder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.notification_title);
            details=itemView.findViewById(R.id.notification_details);
        }
    }
}
