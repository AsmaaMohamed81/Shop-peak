package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by M.Hamada on 13/05/2019.
 */

public class Search_Navigation_Adapter extends RecyclerView.Adapter<Search_Navigation_Adapter.Search_Navigation_Holder> implements Filterable {
    Context context;
    List<HomeModel> homeModelList;
    List<HomeModel> full_list_ofhome;

    public Search_Navigation_Adapter(Context context, List<HomeModel> homeModelList) {
        this.context = context;
        this.homeModelList = homeModelList;
        full_list_ofhome = new ArrayList<>(homeModelList);
    }

    @NonNull
    @Override
    public Search_Navigation_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_item, parent, false);
        Search_Navigation_Holder image2holder = new Search_Navigation_Holder(view);
        return image2holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Search_Navigation_Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return homeModelList.size();
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
                    if (homeModel.getProduct_title().toLowerCase().contains(filterpattern) ||
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
            homeModelList.clear();
            homeModelList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class Search_Navigation_Holder extends RecyclerView.ViewHolder {
        ImageView share;
        CheckBox fav;
        ViewPager viewPager;
        CircleImageView img_profile;
        TextView text_profile;
        RatingBar ratbar;
        LinearLayout home_linear;
        EditText order_num;

        public Search_Navigation_Holder(View itemView) {
            super(itemView);
            viewPager = itemView.findViewById(R.id.viewpager);
            fav = itemView.findViewById(R.id.fav_check);
            share = itemView.findViewById(R.id.img_share);
            img_profile = itemView.findViewById(R.id.img_c);
            text_profile = itemView.findViewById(R.id.txt_name);
            ratbar = itemView.findViewById(R.id.ratbar);
            home_linear = itemView.findViewById(R.id.home_linear);
            order_num = itemView.findViewById(R.id.order_num);
        }
    }
}
