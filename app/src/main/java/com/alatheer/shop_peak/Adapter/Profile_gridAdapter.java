package com.alatheer.shop_peak.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;

import java.util.List;

public class Profile_gridAdapter extends RecyclerView.Adapter<Profile_gridAdapter.Profile_gridHolder> {
    List<HomeModel> mHomeModelList;
    CustomSwipeAdapter customSwipeAdapter;
    Context context;
    public Profile_gridAdapter(List<HomeModel> profileModels, Context context){
        this.mHomeModelList=profileModels;
        this.context=context;
    }
    @NonNull
    @Override
    public Profile_gridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.profile_raw1,viewGroup,false);
        Profile_gridHolder profileVerticalHolder=new Profile_gridHolder(view);
        return profileVerticalHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Profile_gridHolder profile_gridHolder, int i) {
        final String details = mHomeModelList.get(i).details;
        final List<Item> itemList = mHomeModelList.get(i).items;
        final String price = (String) mHomeModelList.get(i).priceAfterDis;
        final String price_before_discount = mHomeModelList.get(i).priceBeforeDis;
        final String sanf_name = mHomeModelList.get(i).sanfName;
        final String vender_name = mHomeModelList.get(i).storeName;
        final String vender_image = mHomeModelList.get(i).storeImg;
        final String sanf_id = mHomeModelList.get(i).id;
        final String rating = mHomeModelList.get(i).rate;
        final String store_id = mHomeModelList.get(i).storeIdFk;
        final String[]colors= mHomeModelList.get(i).colors;
        final String link = mHomeModelList.get(i).link;
        final String like = mHomeModelList.get(i).getLike();

        profile_gridHolder.viewPager.setAdapter(customSwipeAdapter);


        final String[] image_resources = {mHomeModelList.get(i).mainImg};
        final String[] image_resources2 =mHomeModelList.get(i).img;
        if(image_resources2.length <1){
            customSwipeAdapter = new CustomSwipeAdapter(image_resources,itemList, sanf_name, details, price, sanf_id, rating, store_id,colors,price_before_discount,like,context);
            profile_gridHolder.viewPager.setAdapter(customSwipeAdapter);


        }else {
            customSwipeAdapter = new CustomSwipeAdapter(image_resources2,itemList, sanf_name, details, price, sanf_id, rating, store_id,colors,price_before_discount,like,context);
            profile_gridHolder.viewPager.setAdapter(customSwipeAdapter);

        }

    }

    @Override
    public int getItemCount() {
        return mHomeModelList.size();
    }

    class Profile_gridHolder extends RecyclerView.ViewHolder{
        ViewPager viewPager;
        public Profile_gridHolder(@NonNull View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.viewpager);
        }
    }
}
