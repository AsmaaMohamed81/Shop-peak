package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.Follow;
import com.alatheer.shop_peak.Model.NotificationModel;
import com.alatheer.shop_peak.R;

import java.util.List;

/**
 * Created by M.Hamada on 16/06/2019.
 */

public class Follows_Adapter extends RecyclerView.Adapter<Follows_Adapter.Follow_Holder> {
    List<Follow> followList;
    Context context;

    public Follows_Adapter(List<Follow> followList, Context context) {
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
      follow_holder.details.setText(" بمتابعتك"+followList.get(i).clientName+"قام ");
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
