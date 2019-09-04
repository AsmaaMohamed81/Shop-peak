package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.DetailsActivity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by M.Hamada on 02/04/2019.
 */

public class CustomSwipeAdapter extends PagerAdapter{
    private String[] images_resources,colors;
    List<Item> item;
    String sanf_name,details,price,product_id,rating,store_id,price_before_dis,like;
    private Context context;

    public CustomSwipeAdapter(String[] images_resources, List<Item> item, String sanf_name, String details, String price, String product_id, String rating, String store_id, String[] colors,String price_before_dis,String like, Context context) {
        this.images_resources = images_resources;
        this.item= item;
        this.sanf_name=sanf_name;
        this.details= details;
        this.price = price;
        this.product_id = product_id;
        this.rating = rating;
        this.store_id = store_id;
        this.price_before_dis=price_before_dis;
        this.colors = colors;
        this.like = like;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images_resources.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(FrameLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.swipe_layout,container,false);
        ImageView imageView=view.findViewById(R.id.details_image);
        TextView textView=view.findViewById(R.id.image_number);
        //Picasso.with(context).load(images_url[position]).into(imageView);
        //imageView.setImageURI(images_resources[position]);
        Picasso.with(context).load(images_resources[position]).into(imageView);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putStringArray("homeimage", images_resources);
                bundle.putSerializable("itemlist", (Serializable) item);
                bundle.putString("details", details);
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtras(bundle);
                intent.putExtra("title", sanf_name);
                intent.putExtra("itemlist", (Serializable) item);
                intent.putExtra("details", details);
                intent.putExtra("price", price);
                intent.putExtra("price_before_dis",price_before_dis);
                intent.putExtra("id", product_id);
                intent.putExtra("rate", rating);
                intent.putExtra("store_id",store_id);
                intent.putExtra("like",like);
                intent.putExtra("color",colors);
                context.startActivity(intent);
            }
        });
        textView.setText(position+1 +"/"+images_resources.length);
        container.addView(view);
        return view;
    }


//    public void sendOfferItem(String[] image_resources, String title, String des, String price, String gender) {
//        Bundle bundle = new Bundle();
//        bundle.putStringArray("homeimage", image_resources);
//        Intent intent = new Intent(this, Offer_Details_Activity.class);
//        intent.putExtras(bundle);
//        intent.putExtra("title", title);
//        intent.putExtra("des", des);
//        intent.putExtra("price", price);
//        intent.putExtra("gender", gender);
//        startActivity(intent);
//        Animatoo.animateInAndOut(MainActivity.this);
//    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((FrameLayout)object);
    }
}
