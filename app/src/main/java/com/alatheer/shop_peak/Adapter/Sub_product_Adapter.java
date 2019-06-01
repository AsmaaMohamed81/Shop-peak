package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.Category_Activity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.preferance.MySharedPreference;

import java.util.List;

/**
 * Created by M.Hamada on 24/03/2019.
 */

public class Sub_product_Adapter extends RecyclerView.Adapter<Sub_product_Adapter.NavigationHolder> {
    List<list_cats.Subs> subsList;
    Context context;
    MySharedPreference mPrefs;
    Category_Activity category_activity;
    public Sub_product_Adapter(List<list_cats.Subs> subsList, Context context){
        this.subsList=subsList;
        this.context=context;
        category_activity=(Category_Activity) context;
    }
    @NonNull
    @Override
    public NavigationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.filter_raw,parent,false);
        NavigationHolder navigationHolder=new NavigationHolder(view);
        return navigationHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final NavigationHolder holder, final int position) {
     holder.txt_raw.setText(subsList.get(position).getName());
//     MyHolder.image_raw.setImageResource(navigationModels.get(position).getItem_image());
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             int pos=holder.getAdapterPosition();

             Log.d("asmaa", "onClick: "+pos);
             category_activity.list_product_pos(pos);


         }
     });
    }

    @Override
    public int getItemCount() {
        return subsList.size();
    }

    class NavigationHolder extends RecyclerView.ViewHolder{
//        ImageView image_raw;
        TextView txt_raw;
        public NavigationHolder(View itemView) {
            super(itemView);
//            image_raw=itemView.findViewById(R.id.image_raw);
            txt_raw=itemView.findViewById(R.id.filter_text);
        }
    }
}
