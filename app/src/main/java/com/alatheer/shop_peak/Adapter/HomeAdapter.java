package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by M.Hamada on 22/03/2019.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Image2holder> {
    List<HomeModel>image2Models;
    Context context;
    public HomeAdapter(List<HomeModel> image2Models, Context context){
        this.image2Models=image2Models;
        this.context=context;
    }
    @NonNull
    @Override
    public Image2holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.list_item2,parent,false);
        Image2holder image2holder=new Image2holder(view);
        return image2holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Image2holder holder, int position) {
     holder.imageView.setImageResource(image2Models.get(position).getImage2());
    }

    @Override
    public int getItemCount() {
        return image2Models.size();
    }

    class Image2holder extends RecyclerView.ViewHolder{
        ImageView imageView,share,fav;
        CircleImageView img;
        TextView textView;
        RatingBar ratbar;
        public Image2holder(View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img);
            fav=itemView.findViewById(R.id.fav_img);
            share=itemView.findViewById(R.id.img_share);
            img=itemView.findViewById(R.id.img_c);
            textView=itemView.findViewById(R.id.txt_name);
            ratbar=itemView.findViewById(R.id.ratbar);
        }
    }
}
