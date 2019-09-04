package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.Follow_Vender;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.Holder> {
    List<Follow_Vender> model1List;
    Context context;

    public FollowersAdapter(List<Follow_Vender> ModelList, Context context) {
        this.model1List = ModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.followers_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.client_name.setText(model1List.get(position).getName());

        if (model1List.get(position).getLogo_img()!=null) {
            Picasso.with(context).load(model1List.get(position).getLogo_img()).into(holder.client_img);
        }else {
            holder.client_img.setImageResource(R.mipmap.icon_round);
        }
    }

    @Override
    public int getItemCount() {
        return model1List.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView client_name;
        CircleImageView client_img;
        public Holder(View itemView) {
            super(itemView);
            client_name=itemView.findViewById(R.id.client_name);
            client_img=itemView.findViewById(R.id.client_img);
        }
    }
}