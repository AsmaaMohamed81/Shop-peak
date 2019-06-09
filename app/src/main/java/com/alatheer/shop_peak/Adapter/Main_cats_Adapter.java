package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.AddProductActivity;
import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Activities.Vender_Signup_Activity;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.R;

import java.util.List;

public class Main_cats_Adapter extends RecyclerView.Adapter<Main_cats_Adapter.RecyclerHolder> {
    Context context;
    List<list_cats> main_cat_List;
    AddProductActivity addProductActivity;
    public Main_cats_Adapter(Context context, List<list_cats> main_cat_List) {
        this.context = context;
        this.main_cat_List = main_cat_List;
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_txt,viewGroup,false);
        return new  RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerHolder recyclerHolder, int i) {
        list_cats main_cat=main_cat_List.get(i);
        recyclerHolder.Bind(main_cat);

        recyclerHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=recyclerHolder.getAdapterPosition();

                if (context instanceof AddProductActivity){


                    addProductActivity=(AddProductActivity) context;
                    addProductActivity.pos_main_cat(pos);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return main_cat_List.size();
    }

    class  RecyclerHolder extends  RecyclerView.ViewHolder{
        TextView textView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

        public void Bind(list_cats list_cats) {
            textView.setText(list_cats.getName());
        }
        }
    }

