package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.List;

/**
 * Created by M.Hamada on 25/03/2019.
 */

public class Profile_GridAdapter extends RecyclerView.Adapter<Profile_GridAdapter.Profile_GridHolder> {
    List<HomeModel>mHomeModelList;
    Context context;
    CustomSwipeAdapter customSwipeAdapter;
    public Profile_GridAdapter(List<HomeModel> profileModels, Context context){
        this.mHomeModelList=profileModels;
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


        final String[] image_resources2 ={mHomeModelList.get(position).mainImg};
        customSwipeAdapter = new CustomSwipeAdapter(image_resources2, context);
        holder.viewPager.setAdapter(customSwipeAdapter);
     //Uri uri = Uri.parse(profileModels.get(position).getImage());
     //Picasso.with(context).load(new File(uri.getPath())).into(MyHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return mHomeModelList.size();
    }

    class Profile_GridHolder extends RecyclerView.ViewHolder{
        ViewPager viewPager;
        public Profile_GridHolder(View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.viewpager);
        }
    }
}
