package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alatheer.shop_peak.Model.ProfileModel;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by M.Hamada on 25/03/2019.
 */

public class Profile_verticalAdapter extends RecyclerView.Adapter<Profile_verticalAdapter.Profile_verticalHolder> {
    List<ProfileModel> profileModels;
    CustomSwipeAdapter customSwipeAdapter;
    Context context;
    public Profile_verticalAdapter(List<ProfileModel> profileModels, Context context){
        this.profileModels=profileModels;
        this.context=context;
    }
    @NonNull
    @Override
    public Profile_verticalHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_raw2,parent,false);
        Profile_verticalHolder profileVerticalHolder=new Profile_verticalHolder(view);
        return profileVerticalHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Profile_verticalHolder holder, int position) {
        final String image1 = profileModels.get(position).getImage1();
        final String image2 = profileModels.get(position).getImage2();
        final String[] image_resources = {image1,image2};
        customSwipeAdapter = new CustomSwipeAdapter(image_resources, context);
        holder.viewPager.setAdapter(customSwipeAdapter);
        //Uri uri = Uri.parse(profileModels.get(position).getImage());
        //Picasso.with(context).load(new File(uri.getPath())).into(MyHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return profileModels.size();
    }

    class Profile_verticalHolder extends RecyclerView.ViewHolder{
     ViewPager viewPager;
        public Profile_verticalHolder(View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.viewpager);
        }
    }
}
