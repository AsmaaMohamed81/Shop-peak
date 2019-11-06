package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.Asnaf;
import com.alatheer.shop_peak.Model.DeliveryOrder;
import com.alatheer.shop_peak.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Sanf_Adapter extends RecyclerView.Adapter<Sanf_Adapter.Sanf_Holder> {
    List<Asnaf>asnafList;
    List<DeliveryOrder> deliveryOrders;
    Context context;

    public Sanf_Adapter(List<Asnaf> asnafList,List<DeliveryOrder> deliveryOrders, Context context) {
        this.asnafList = asnafList;
        this.context = context;
        this.deliveryOrders= deliveryOrders;
    }

    @NonNull
    @Override
    public Sanf_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item,parent,false);
        return new Sanf_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Sanf_Holder holder, int position) {
        holder.sanf_name.setText(asnafList.get(position).getSanfName());
        holder.sanf_amount.setText(asnafList.get(position).getAmount());
        holder.seller_name.setText(asnafList.get(position).getStoreName());
        holder.seller_address.setText(asnafList.get(position).getStoreAdress());
        holder.price.setText(asnafList.get(position).getPrice()+"LE"+"");
    }

    @Override
    public int getItemCount() {
        return asnafList.size();
    }

    class Sanf_Holder extends  RecyclerView.ViewHolder{
         TextView sanf_name,sanf_amount,seller_name,seller_address,price;
        public Sanf_Holder(@NonNull View itemView) {
            super(itemView);
            sanf_name = itemView.findViewById(R.id.item_name);
            sanf_amount = itemView.findViewById(R.id.item_amount);
            seller_name = itemView.findViewById(R.id.txt_seller_name);
            seller_address = itemView.findViewById(R.id.txt_seller_address);
            price = itemView.findViewById(R.id.item_price);
        }
    }
}
