package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Activities.Offer_Activity;
import com.alatheer.shop_peak.Fragments.Favorite_Fragment;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AllOfferAdapter extends RecyclerView.Adapter<AllOfferAdapter.MyHolder> {
    List<HomeModel> listofhome;
    Context context;
    HomeModel homeModel;
    long id;
    Offer_Activity offer_activity;


    public AllOfferAdapter(List<HomeModel> listofhome, Context context) {
        this.listofhome = listofhome;
        this.context = context;
        offer_activity = (Offer_Activity) context;


    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_row, viewGroup, false);
        return new AllOfferAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {



        homeModel = listofhome.get(i);
        myHolder.BindData(homeModel);

        myHolder.Fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                homeModel = listofhome.get(i);

                int pos=myHolder.getAdapterPosition();

//                    favorite_fragment.pos(pos,homeModel.id);

            }
        });
        final String details = listofhome.get(i).details;
        final List<Item> itemList = listofhome.get(i).items;
        final String price = (String) listofhome.get(i).priceAfterDis;
        final String price_before_discount = listofhome.get(i).priceBeforeDis;
        final String sanf_name = listofhome.get(i).sanfName;
        final String vender_name = listofhome.get(i).storeName;
        final String vender_image = listofhome.get(i).storeImg;
        final String sanf_id = listofhome.get(i).id;
        final String rating = listofhome.get(i).rate;
        final String store_id = listofhome.get(i).storeIdFk;
        final String[]colors= listofhome.get(i).colors;
        final String link = listofhome.get(i).link;
        final String like = listofhome.get(i).getLike();
        final String[] image_resources = {listofhome.get(i).mainImg};


        offer_activity.dataVonder(vender_name,vender_image);

        myHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offer_activity.sendHomeItem(image_resources, itemList, sanf_name, details, price, sanf_id, rating, store_id,colors,price_before_discount,like,vender_name,vender_image);
            }
        });



    }

    @Override
    public int getItemCount() {
        return listofhome.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder{
        private FrameLayout fl_discount_container;
        private ImageView image,Fav;
        private TextView tv_discount,tv_name,tv_before_discount,tv_after_discount;

        public MyHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            Fav=itemView.findViewById(R.id.Fav);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_before_discount = itemView.findViewById(R.id.tv_before_discount);
            tv_after_discount = itemView.findViewById(R.id.tv_after_discount);

        }

        public void BindData(HomeModel homeModel) {

            Picasso.with(context).load(homeModel.mainImg).into(image);
            tv_name.setText(homeModel.sanfName);


            tv_before_discount.setPaintFlags(tv_before_discount.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

            tv_before_discount.setText(homeModel.priceBeforeDis);
            tv_after_discount.setText(String.valueOf(homeModel.priceAfterDis));


        }
    }




}

