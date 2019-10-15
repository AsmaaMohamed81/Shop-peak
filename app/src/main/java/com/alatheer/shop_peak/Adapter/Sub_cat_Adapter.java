package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.AddProductActivity;
import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Activities.Vender_Signup_Activity;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class Sub_cat_Adapter extends RecyclerView.Adapter<Sub_cat_Adapter.Sub_cat_Holder> {
    Context context;
    List<list_cats.Subs> sub_list;
    AddProductActivity addProductActivity;

    public Sub_cat_Adapter(Context context,List<list_cats.Subs> sub_list) {
        this.context = context;
        this.sub_list = sub_list;
    }

    @Override
    public Sub_cat_Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_txt,viewGroup,false);
        return new Sub_cat_Holder(view);
    }

    @Override
    public void onBindViewHolder(final Sub_cat_Holder sub_cat_holder, int i) {
        final list_cats.Subs city=sub_list.get(i);
        sub_cat_holder.Bind(city);

        sub_cat_holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=sub_cat_holder.getAdapterPosition();


                if (context instanceof AddProductActivity) {
                    addProductActivity = (AddProductActivity) context;
                   addProductActivity.pos_sub(pos);

                }
                }
        });

    }

    @Override
    public int getItemCount() {
        return sub_list.size();
    }

    class Sub_cat_Holder extends  RecyclerView.ViewHolder{
         TextView textView;
        public Sub_cat_Holder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

        public void Bind(list_cats.Subs list_cats) {
            textView.setText(list_cats.getName());
        }

    }
}
