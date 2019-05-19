package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Model.OfferModel;
import com.alatheer.shop_peak.Model.ProfileModel;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by M.Hamada on 21/03/2019.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ImageHolder> {
    public List<OfferModel>imageModels;
    public Context context;
    MainActivity mainActivity;
    CustomSwipeAdapter customSwipeAdapter;
    public OfferAdapter(List<OfferModel> imageModels, Context context){
        this.imageModels=imageModels;
        this.context=context;
        this.mainActivity = (MainActivity) context;
    }
    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.offer_item,parent,false);
        ImageHolder imageHolder=new ImageHolder(view);
        return  imageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        Picasso.with(context).load(imageModels.get(position).getImage1()).into(holder.circleImageView);
        final String image1 = imageModels.get(position).getImage1();
        final String image2 = imageModels.get(position).getImage2();
        final String[] image_resources = {image1, image2};
        final String title = imageModels.get(position).getProduct_title();
        final String des = imageModels.get(position).getProduct_describtion();
        final String price = imageModels.get(position).getProduct_price();
        final String gender = imageModels.get(position).getGender();
        final String vender_name = imageModels.get(position).getVender_name();
        final int vender_image = imageModels.get(position).getVender_image();

        customSwipeAdapter = new CustomSwipeAdapter(image_resources, context);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.sendOfferItem(image_resources, title, des, price, gender);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageModels.size();
    }

    class ImageHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        public ImageHolder(View itemView) {
            super(itemView);
            circleImageView=itemView.findViewById(R.id.img_c);
        }
    }
}
