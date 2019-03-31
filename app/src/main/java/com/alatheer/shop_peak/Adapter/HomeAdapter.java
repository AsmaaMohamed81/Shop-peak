package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.DetailsActivity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Fragments.DetailsFragment;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by M.Hamada on 22/03/2019.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Image2holder> {
    List<HomeModel>listofhome;
    Context context;
    MainActivity mainActivity;
    public HomeAdapter(List<HomeModel> listofhome, Context context){
        this.listofhome=listofhome;
        this.context=context;
        this.mainActivity= (MainActivity) context;
    }
    @NonNull
    @Override
    public Image2holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.home_item,parent,false);
        Image2holder image2holder=new Image2holder(view);
        return image2holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Image2holder holder, int position) {
        final int image= listofhome.get(position).getProduct_image();
        final String title=listofhome.get(position).getProduct_title();
        final String des=listofhome.get(position).getProduct_describtion();
        final String price=listofhome.get(position).getProduct_price();
     holder.imageView.setImageResource(image);
     holder.img.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             mainActivity.setSelectProfile();

         }
     });
     holder.textView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             mainActivity.setSelectProfile();
         }
     });
     holder.imageView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(context, DetailsActivity.class);
             intent.putExtra("homeimage",image);
             intent.putExtra("title",title);
             intent.putExtra("des",des);
             intent.putExtra("price",price);
             context.startActivity(intent);
         }
     });
    }

    @Override
    public int getItemCount() {
        return listofhome.size();
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
