package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alatheer.shop_peak.Model.ProfileModel;
import com.alatheer.shop_peak.R;

import java.util.List;

/**
 * Created by M.Hamada on 25/03/2019.
 */

public class Profile_GridAdapter extends RecyclerView.Adapter<Profile_GridAdapter.Profile_GridHolder> {
    List<ProfileModel>profileModels;
    Context context;
    public Profile_GridAdapter(List<ProfileModel> profileModels, Context context){
        this.profileModels=profileModels;
        this.context=context;
    }
    @NonNull
    @Override
    public Profile_GridHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_raw1,parent,false);
        Profile_GridHolder profile_gridHolder=new Profile_GridHolder(view);

        return profile_gridHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Profile_GridHolder holder, int position) {
     holder.imageView.setImageResource(profileModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return profileModels.size();
    }

    class Profile_GridHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public Profile_GridHolder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
        }
    }
}
