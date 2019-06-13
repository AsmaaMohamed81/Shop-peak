package com.alatheer.shop_peak.Adapter;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.Basket_Activity;
import com.alatheer.shop_peak.Activities.DetailsActivity;
import com.alatheer.shop_peak.Activities.Details_two_Activity;
import com.alatheer.shop_peak.Activities.Favorite_Activity;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;
import java.util.zip.Inflater;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by M.Hamada on 22/04/2019.
 */

public class  BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketHolder> {
    Context context;
    List<OrderItemList>basketModelList;
    Basket_Activity basket_activity;
    MyAppDatabase myAppDatabase;
    CustomSwipeAdapter customSwipeAdapter;
    int count;
    int id;
    public BasketAdapter(Context context, List<OrderItemList> basketModelList) {
        this.context = context;
        this.basketModelList = basketModelList;
        this.basket_activity= (Basket_Activity) context;

    }

    @NonNull
    @Override
    public BasketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.basket_raw,parent,false);
        return new BasketHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BasketHolder holder, final int position) {
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "productdb").allowMainThreadQueries().build();
        holder.basket_title.setText(basketModelList.get(position).sanfIdFk);
        Picasso.with(context).load(basketModelList.get(position).sanfPrice).into(holder.title_img);
        holder.counter.setText(basketModelList.get(position).sanfAmount);
        holder.delete_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket_activity.senddata2(position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "id"+basketModelList.get(position).getId()+"", Toast.LENGTH_SHORT).show();
                basket_activity.senddata(position);

            }
        });
        //Uri uri= Uri.parse(basketModelList.get(position).getImg());
       // File file =new File(uri.getPath());
        //Picasso.with(context).load(file).into(MyHolder.title_img);
        /*boolean red=basketModelList.get(position).isRed_flag();
        boolean blue=basketModelList.get(position).isBlue_flag();
        boolean black=basketModelList.get(position).isBlack_flag();
        if(red == true){
            holder.c_red.setButtonDrawable(R.drawable.ic_check);
        }else {
            holder.c_red.setButtonDrawable(R.drawable.ic_check_gray);
        }
        if (blue == true){
            holder.c_blue.setButtonDrawable(R.drawable.ic_check);
        }else {
            holder.c_blue.setButtonDrawable(R.drawable.ic_check_gray);
        }
        if (black == true){
            holder.c_black.setButtonDrawable(R.drawable.ic_check);
        }else {
            holder.c_black.setButtonDrawable(R.drawable.ic_check_gray);
        }*/


    }
    public void startActivity(){

    }

    @Override
    public int getItemCount() {
        return basketModelList.size();
    }

    class  BasketHolder extends RecyclerView.ViewHolder{
        ImageView plus_circle, minus_circle, title_img, delete_image;
       TextView basket_title,counter;
       CheckBox c_red,c_blue,c_black;
        public BasketHolder(View itemView) {
            super(itemView);
            plus_circle=itemView.findViewById(R.id.plus_circle);
            minus_circle=itemView.findViewById(R.id.minus_circle);
            basket_title=itemView.findViewById(R.id.basket_title);
            counter=itemView.findViewById(R.id.counter);
            c_red=itemView.findViewById(R.id.checkbox_red);
            c_blue=itemView.findViewById(R.id.checkbox_blue);
            c_black=itemView.findViewById(R.id.checkbox_black);
            title_img=itemView.findViewById(R.id.img);
            delete_image = itemView.findViewById(R.id.img_delete);
        }
    }
}
