package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.Search_Activity;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by M.Hamada on 10/04/2019.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {
    List<HomeModel> listofhome;
    Context context;
    CustomSwipeAdapter customSwipeAdapter;
    Search_Activity search_activity;
    public SearchAdapter(List<HomeModel> listofhome, Context context) {
        this.listofhome = listofhome;
        this.context = context;

    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        SearchHolder searchHolder =new SearchHolder(view);
        return searchHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        //Uri uri = Uri.parse(listofhome.get(position).getProduct_image());

       // File file =new File(uri.getPath());
        //File[]path=new File[]{file,file,file};

        String image = listofhome.get(position).mainImg;
        final String[] images = {listofhome.get(position).mainImg};
        customSwipeAdapter = new CustomSwipeAdapter(images, context);
        holder.viewPager.setAdapter(customSwipeAdapter);
        final List<Item> itemList = listofhome.get(position).items;
        final String price = (String) listofhome.get(position).priceAfterDis;
        final String price_before_discount = listofhome.get(position).priceBeforeDis;
        final String sanf_name = listofhome.get(position).sanfName;
        final String vender_name = listofhome.get(position).storeName;
        final String vender_image = listofhome.get(position).storeImg;
        final String sanf_id = listofhome.get(position).id;
        final String rating = listofhome.get(position).rate;
        final String store_id = listofhome.get(position).storeIdFk;
        final String[]colors= listofhome.get(position).colors;
        final String link = listofhome.get(position).link;
        holder.viewPager.setAdapter(customSwipeAdapter);
        Picasso.with(context).load(vender_image).into(holder.img);
        holder.textView.setText(vender_name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search_activity.sendHomeItem(images, itemList, sanf_name, price, sanf_id, rating, store_id,colors,price_before_discount);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listofhome.size();
    }

    /*@Override
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
                            ||homeModel.getGender().toLowerCase().contains(filterpattern)) {
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
    };*/
    class  SearchHolder extends RecyclerView.ViewHolder{
        ImageView share;
        CheckBox fav;
        ViewPager viewPager;
        CircleImageView img;
        TextView textView;
        RatingBar ratbar;
        public SearchHolder(View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.viewpager);
            fav=itemView.findViewById(R.id.fav_check);
            share=itemView.findViewById(R.id.img_share);
            img=itemView.findViewById(R.id.img_c);
            textView=itemView.findViewById(R.id.txt_name);
            ratbar=itemView.findViewById(R.id.ratbar);
        }
    }
}
