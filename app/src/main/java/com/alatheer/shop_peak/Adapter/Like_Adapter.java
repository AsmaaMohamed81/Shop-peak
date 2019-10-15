package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.Like;
import com.alatheer.shop_peak.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by M.Hamada on 16/06/2019.
 */

public class Like_Adapter extends RecyclerView.Adapter<Like_Adapter.Like_Holder> {
    List<Like>likeList;
    Context context;

    public Like_Adapter(List<Like> likeList, Context context) {
        this.likeList = likeList;
        this.context = context;
    }

    @NonNull
    @Override
    public Like_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.notification_row,viewGroup,false);
        return new Like_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Like_Holder like_holder, int i) {
        like_holder.details.setText(likeList.get(i).sanfName+" بالاعجاب "+likeList.get(i).clientName+"قام ");
    }

    @Override
    public int getItemCount() {
        return likeList.size();
    }

    class  Like_Holder extends  RecyclerView.ViewHolder{
        TextView details;
        public Like_Holder(@NonNull View itemView) {
            super(itemView);
            details=itemView.findViewById(R.id.notification_details);
        }
    }
}
