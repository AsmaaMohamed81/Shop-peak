package com.alatheer.shop_peak.Adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Basket_Activity;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by M.Hamada on 22/04/2019.
 */

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.BasketHolder> {
    Context context;
    List<BasketModel>basketModelList;
    Basket_Activity basket_activity;
    MyAppDatabase myAppDatabase;
    CustomSwipeAdapter customSwipeAdapter;
    int count;
    public BasketAdapter(Context context, List<BasketModel> basketModelList) {
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
        holder.basket_title.setText(basketModelList.get(position).getTitle());
        holder.counter.setText(basketModelList.get(position).getNum_of_cart());
        count =Integer.parseInt(basketModelList.get(position).getNum_of_cart());
        holder.plus_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count=count+1;
                holder.counter.setText(count +"");
            }
        });
        holder.minus_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count != 0) {
                    count=count-1;
                    holder.counter.setText(count + "");
                }
            }
        });
        final int image = basketModelList.get(position).getImg();
        int id = basketModelList.get(position).getId();
        boolean red=basketModelList.get(position).isRed_flag();
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
        }
         holder.img.setImageResource(image);
        basket_activity.senddata(id,basketModelList.get(position).getTitle(),holder.counter.getText().toString(),red,blue,black);
    }

    @Override
    public int getItemCount() {
        return basketModelList.size();
    }

    class  BasketHolder extends RecyclerView.ViewHolder{
       ImageView plus_circle,minus_circle,img;
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
            img=itemView.findViewById(R.id.img);

        }
    }
}
