package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Seller_Search_Activity;
import com.alatheer.shop_peak.Model.SellerSearch;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Seller_Search_Adapter extends RecyclerView.Adapter<Seller_Search_Adapter.Seller_Search_Holder>{
    List<SellerSearch>list;
    Context context;
    Seller_Search_Activity seller_search_activity;
    SellerSearch sellerSearch;
    public Seller_Search_Adapter(List<SellerSearch> list, Context context) {
        this.list = list;
        this.context = context;
        this.seller_search_activity= (Seller_Search_Activity) context;
    }

    @NonNull
    @Override
    public Seller_Search_Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.followers_item,viewGroup,false);
        return new Seller_Search_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Seller_Search_Holder seller_search_holder, final int position) {
        seller_search_holder.client_name.setText(list.get(position).getFull_name());
        if (list.get(position).getLogo_img()!=null) {
            Picasso.with(context).load(list.get(position).getStore_img()).into(seller_search_holder.client_img);
        }else {
            seller_search_holder.client_img.setImageResource(R.mipmap.icon_round);
        }
        seller_search_holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellerSearch=list.get(position);
                seller_search_activity.send_data(sellerSearch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Seller_Search_Holder extends  RecyclerView.ViewHolder{
        TextView client_name;
        CircleImageView client_img;
        public Seller_Search_Holder(@NonNull View itemView) {
            super(itemView);
            client_name=itemView.findViewById(R.id.client_name);
            client_img=itemView.findViewById(R.id.client_img);
        }
    }
}
