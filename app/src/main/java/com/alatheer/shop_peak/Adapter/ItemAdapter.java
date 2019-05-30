package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;

import java.util.List;

/**
 * Created by M.Hamada on 29/05/2019.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {
    List<Item> itemList;
    Context context;

    public ItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.description_item, viewGroup, false);
        ItemHolder itemHolder = new ItemHolder(view);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int i) {
        itemHolder.element_name.setText(itemList.get(i).title);
        itemHolder.element_description.setText(itemList.get(i).details);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {
        TextView element_name, element_description;

        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            element_name = itemView.findViewById(R.id.element_name);
            element_description = itemView.findViewById(R.id.element_description);


        }
    }
}
