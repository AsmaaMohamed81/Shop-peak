package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.Contact_Us_Activity;
import com.alatheer.shop_peak.Activities.Favorite_Activity;
import com.alatheer.shop_peak.Activities.Login_Activity;
import com.alatheer.shop_peak.Activities.Settings_Activity;
import com.alatheer.shop_peak.Activities.Vender_Signup_Activity;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.Model.NavigationModel;
import com.alatheer.shop_peak.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

/**
 * Created by M.Hamada on 24/03/2019.
 */

public class NavigationAdapter extends RecyclerView.Adapter<NavigationAdapter.NavigationHolder> {
    List<NavigationModel>navigationModels;
    Context context;
    MySharedPreference mPrefs;
    public NavigationAdapter(List<NavigationModel> navigationModels, Context context){
        this.navigationModels=navigationModels;
        this.context=context;
    }
    @NonNull
    @Override
    public NavigationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.navigation_raw,parent,false);
        NavigationHolder navigationHolder=new NavigationHolder(view);
        return navigationHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NavigationHolder holder, final int position) {
     holder.txt_raw.setText(navigationModels.get(position).getItem_name());
     holder.image_raw.setImageResource(navigationModels.get(position).getItem_image());
     holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Toast.makeText(context, navigationModels.get(position).getItem_name(), Toast.LENGTH_SHORT).show();
             if (navigationModels.get(position).getItem_name().equals(context.getString(R.string.logout))){
                 mPrefs = new MySharedPreference(context);
                 mPrefs.ClearData(context);
                 context.startActivity(new Intent(context, Login_Activity.class));
                 Animatoo.animateSlideLeft(context);
             }
             if (navigationModels.get(position).getItem_name().equals(context.getString(R.string.contact))){
                 Intent intent= new Intent(context, Contact_Us_Activity.class);
                 context.startActivity(intent);
                 Animatoo.animateSlideLeft(context);
             }
             if ((navigationModels.get(position).getItem_name().equals(context.getString(R.string.setting)))){
                 Intent intent= new Intent(context, Settings_Activity.class);
                 context.startActivity(intent);
                 Animatoo.animateSlideLeft(context);
             }
             if((navigationModels.get(position).getItem_name().equals(context.getString(R.string.favorite)))){
                 Intent intent = new Intent(context, Favorite_Activity.class);
                 context.startActivity(intent);
                 Animatoo.animateSlideLeft(context);
             }
             if ((navigationModels.get(position).getItem_name().equals(context.getString(R.string.join_us)))) {
                 Intent intent = new Intent(context, Vender_Signup_Activity.class);
                 context.startActivity(intent);
                 Animatoo.animateSlideLeft(context);
             }

         }
     });
    }

    @Override
    public int getItemCount() {
        return navigationModels.size();
    }

    class NavigationHolder extends RecyclerView.ViewHolder{
        ImageView image_raw;
        TextView txt_raw;
        public NavigationHolder(View itemView) {
            super(itemView);
            image_raw=itemView.findViewById(R.id.image_raw);
            txt_raw=itemView.findViewById(R.id.txt_raw);
        }
    }
}
