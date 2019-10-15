package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.Contact_Us_Activity;
import com.alatheer.shop_peak.Activities.Favorite_Activity;
import com.alatheer.shop_peak.Activities.Login_Activity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.MapsActivity;
import com.alatheer.shop_peak.Activities.Settings_Activity;
import com.alatheer.shop_peak.Activities.Vender_Signup_Activity;
import com.alatheer.shop_peak.Model.list_cats;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.Model.NavigationModel;
import com.alatheer.shop_peak.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by M.Hamada on 24/03/2019.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder> {
    List<list_cats>navigationModels;
    Context context;
    MySharedPreference mPrefs;
    MainActivity mainActivity;
    public NavigationAdapter(List<list_cats> navigationModels, Context context){
        this.navigationModels=navigationModels;
        this.context=context;
        mainActivity=(MainActivity) context;
    }

    @Override
    public NavigationHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.navigation_raw,parent,false);
        NavigationHolder navigationHolder=new NavigationHolder(view);
        return navigationHolder;
    }

    @Override
    public void onBindViewHolder( final NavigationHolder holder, final int position) {
     holder.txt_raw.setText(navigationModels.get(position).getName());
//     MyHolder.image_raw.setImageResource(navigationModels.get(position).getItem_image());
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
//             Toast.makeText(context, navigationModels.get(position).getName(), Toast.LENGTH_SHORT).show();
             int pos=holder.getAdapterPosition();
             mainActivity.list_cats_pos(pos);


         }
     });
    }

    @Override
    public int getItemCount() {
        return navigationModels.size();
    }

    class NavigationHolder extends RecyclerView.ViewHolder{
//        ImageView image_raw;
        TextView txt_raw;
        public NavigationHolder(View itemView) {
            super(itemView);
//            image_raw=itemView.findViewById(R.id.image_raw);
            txt_raw=itemView.findViewById(R.id.txt_raw);
        }
    }
}
