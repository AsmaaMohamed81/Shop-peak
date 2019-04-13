package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.DetailsActivity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.Search_Activity;
import com.alatheer.shop_peak.Fragments.DetailsFragment;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by M.Hamada on 22/03/2019.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Image2holder> implements Filterable {
    List<HomeModel> listofhome;
    Context context;
    MainActivity mainActivity;
    List<HomeModel> full_list_ofhome;
    boolean flag = true;
    CustomSwipeAdapter customSwipeAdapter;
    public HomeAdapter(List<HomeModel> listofhome, Context context) {
        this.listofhome = listofhome;
        this.context = context;
        full_list_ofhome = new ArrayList<>(listofhome);
        this.mainActivity = (MainActivity) context;
    }

    @NonNull
    @Override
    public Image2holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        Image2holder image2holder = new Image2holder(view);
        return image2holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final Image2holder holder, int position) {
        final int[] image = listofhome.get(position).getProduct_image();
        final String title = listofhome.get(position).getProduct_title();
        final String des = listofhome.get(position).getProduct_describtion();
        final String price = listofhome.get(position).getProduct_price();
        customSwipeAdapter = new CustomSwipeAdapter(image, context);
        holder.viewPager.setAdapter(customSwipeAdapter);
        holder.viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("homeimage", image);
                intent.putExtra("title", title);
                intent.putExtra("des", des);
                intent.putExtra("price", price);
                context.startActivity(intent);
            }
        });
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("homeimage", image);
                intent.putExtra("title", title);
                intent.putExtra("des", des);
                intent.putExtra("price", price);
                context.startActivity(intent);
            }
        });
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag) {
                    holder.fav.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_favorite_sold));
                    flag = false;
                } else if (!flag) {
                    holder.fav.setImageDrawable(ContextCompat.getDrawable(context.getApplicationContext(), R.drawable.ic_favorite));
                    flag = true;
                }
            }
        });
        holder.ratbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float touchPositionX = event.getX();
                    float width = holder.ratbar.getWidth();
                    float starsf = (touchPositionX / width) * 5.0f;
                    int stars = (int) starsf + 1;
                    holder.ratbar.setRating(stars);
                    v.setPressed(false);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setPressed(true);
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    v.setPressed(false);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listofhome.size();
    }

    @Override
    public Filter getFilter() {
        return homefilter;
    }

    private Filter homefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<HomeModel> filterlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterlist.addAll(full_list_ofhome);
            } else {
                String filterpattern = constraint.toString().toLowerCase().trim();
                for (HomeModel homeModel : full_list_ofhome) {
                    if (homeModel.getProduct_title().toLowerCase().contains(filterpattern)||
                            homeModel.getSize().toLowerCase().contains(filterpattern)
                            || homeModel.getGender().toLowerCase().contains(filterpattern)) {
                        filterlist.add(homeModel);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterlist;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listofhome.clear();
            listofhome.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    class Image2holder extends RecyclerView.ViewHolder{
        ImageView share,fav;
        ViewPager viewPager;
        CircleImageView img;
        TextView textView;
        RatingBar ratbar;
        public Image2holder(View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.viewpager);
            fav=itemView.findViewById(R.id.fav_img);
            share=itemView.findViewById(R.id.img_share);
            img=itemView.findViewById(R.id.img_c);
            textView=itemView.findViewById(R.id.txt_name);
            ratbar=itemView.findViewById(R.id.ratbar);
        }
    }



}
