package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Model.OfferModel;
import com.alatheer.shop_peak.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by M.Hamada on 21/03/2019.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ImageHolder> {
    public List<OfferModel>imageModels;
  public   Context context;
    public OfferAdapter(List<OfferModel> imageModels, Context context){
        this.imageModels=imageModels;
        this.context=context;
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
     holder.circleImageView.setImageResource(imageModels.get(position).getImage());
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
