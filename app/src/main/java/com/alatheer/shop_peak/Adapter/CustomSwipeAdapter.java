package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by M.Hamada on 02/04/2019.
 */

public class CustomSwipeAdapter extends PagerAdapter{
    private File[] images_url;
    private Context context;

    public CustomSwipeAdapter(File[] images_url, Context context) {
        this.images_url = images_url;
        this.context = context;
    }

    @Override
    public int getCount() {
        return images_url.length;
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
        Picasso.with(context).load(images_url[position]).into(imageView);
        textView.setText(position+1 +"/"+images_url.length);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((FrameLayout)object);
    }
}
