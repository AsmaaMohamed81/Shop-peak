package com.alatheer.shop_peak.Adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Basket_Activity;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

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


    @Override
    public BasketHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.basket_raw,parent,false);
        return new BasketHolder(view);
    }

    @Override
    public void onBindViewHolder( final BasketHolder holder, final int position) {
        myAppDatabase = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "productdb").allowMainThreadQueries().build();
        holder.basket_title.setText(basketModelList.get(position).getSanfTitle());
        Picasso.with(context).load(basketModelList.get(position).getSanfImage()).into(holder.title_img);
        holder.counter.setText(basketModelList.get(position).sanfAmount);
        holder.txt_price.setText(basketModelList.get(position).sanfPrice+"LE");
        count = Integer.parseInt(holder.counter.getText().toString());

        basket_activity.sendBasketData(position);

        holder.plus_circle.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                count = count+1;
                holder.counter.setText(count+"");

                basket_activity.senddata(position,count);
            }
        });
        holder.minus_circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count-1;
               /* holder.counter.setText(count--+"");
                OrderItemList orderItemList = new OrderItemList();
                orderItemList.withSanfAmount(count+"");
                orderItemList.withSanfIdFk(basketModelList.get(position).sanfIdFk);
                orderItemList.withSanfPrice(basketModelList.get(position).sanfPrice);
                orderItemList.withStoreIdFk(basketModelList.get(position).storeIdFk);
                orderItemList.setSanfImage(basketModelList.get(position).getSanfImage());
                orderItemList.setSanfTitle(basketModelList.get(position).sanfTitle);
                basket_activity.senddata(position);
                myAppDatabase.dao().editproduct(orderItemList);*/
               basket_activity.senddata(position,count);
            }
        });

        holder.delete_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basket_activity.senddata2(position);
            }
        });
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "id"+basketModelList.get(position).getId()+"", Toast.LENGTH_SHORT).show();
                basket_activity.senddata(position);

            }
        });*/
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
       TextView basket_title,counter,txt_price;
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
            txt_price = itemView.findViewById(R.id.txt_price);
        }
    }
}
