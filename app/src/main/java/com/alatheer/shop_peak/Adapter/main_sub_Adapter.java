package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Category_Activity;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by M.Hamada on 22/03/2019.
 */

public class main_sub_Adapter extends RecyclerView.Adapter {
    List<HomeModel> listofhome;
    Context context;
    Category_Activity category_activity;
    List<HomeModel> full_list_ofhome;
    Favorite_Database favorite_database;
    boolean accepted = false;
    CustomSwipeAdapter customSwipeAdapter;
    HomeModel homeModel;
    long id;

    private final int ITEM_DATA = 1;
    private final int ITEM_PROGRESS = 2;

    private int lastVisibleItem , totalItemCount;
    private boolean canLoadMore = true;




    public main_sub_Adapter(List<HomeModel> listofhome, Context context) {
        this.listofhome = listofhome;
        this.context = context;
        this.category_activity = (Category_Activity) context;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_category_row, parent, false);
        MyHolder MyHolder = new MyHolder(view);
        return MyHolder;
    }
    @Override
    public void onBindViewHolder( final RecyclerView.ViewHolder MyHolder, final int position) {
        if (MyHolder instanceof MyHolder)
        {
            MyHolder myHolder = (MyHolder) MyHolder;
            homeModel = listofhome.get(position);
            myHolder.BindData(homeModel);
            final String details = listofhome.get(position).details;
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
            final String like = listofhome.get(position).getLike();
            final String[] image_resources = {listofhome.get(position).mainImg};
            /*if (like.equals("1")){

                myHolder.fav.setChecked(true);
            }else {

                myHolder.fav.setChecked(false);

            }*/

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    category_activity.sendHomeItem(image_resources, itemList, sanf_name, details, price, sanf_id, rating, store_id,colors,price_before_discount,like);
                }
            });

        }
    }


    @Override
    public int getItemCount() {
        return listofhome.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private FrameLayout fl_discount_container;
        private ImageView image;
        private TextView tv_discount,tv_name,tv_before_discount,tv_after_discount;

        public MyHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_before_discount = itemView.findViewById(R.id.tv_before_discount);
            tv_after_discount = itemView.findViewById(R.id.tv_after_discount);

        }

        public void BindData(HomeModel homeModel) {

                Picasso.with(context).load(homeModel.mainImg).into(image);
                tv_name.setText(homeModel.sanfName);


            tv_before_discount.setPaintFlags(tv_before_discount.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

            tv_before_discount.setText(homeModel.priceBeforeDis);
                tv_after_discount.setText( String.valueOf(homeModel.priceAfterDis));


        }
    }






}
