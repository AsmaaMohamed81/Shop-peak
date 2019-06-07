package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Fragments.Favorite_Fragment;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by M.Hamada on 22/03/2019.
 */

public class Favourit_Adapter extends RecyclerView.Adapter {
    List<HomeModel> listofhome;
    Context context;
    HomeModel homeModel;
    long id;

    Favorite_Fragment favorite_fragment;





    public Favourit_Adapter(List<HomeModel> listofhome, Context context,Favorite_Fragment fragment) {
        this.listofhome = listofhome;
        this.context = context;
        this.favorite_fragment=fragment;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_row, parent, false);
        MyHolder MyHolder = new MyHolder(view);
        return MyHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder MyHolder, final int position) {
        if (MyHolder instanceof MyHolder)
        {
            final MyHolder myHolder = (MyHolder) MyHolder;
            homeModel = listofhome.get(position);
            myHolder.BindData(homeModel);

            myHolder.Fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {




                    homeModel = listofhome.get(position);

                    int pos=myHolder.getAdapterPosition();

                    favorite_fragment.pos(pos,homeModel.id);

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