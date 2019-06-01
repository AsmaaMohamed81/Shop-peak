package com.alatheer.shop_peak.Adapter;

import android.app.Fragment;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.Basket_Activity;
import com.alatheer.shop_peak.Activities.Favorite_Activity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Fragments.Favorite_Fragment;
import com.alatheer.shop_peak.Fragments.HomeFragment;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.MyAppDatabase;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by M.Hamada on 05/05/2019.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {
    Context context;
    List<BasketModel> basketModelList;
    MyAppDatabase myAppDatabase;
    CustomSwipeAdapter customSwipeAdapter;
    Favorite_Database favoriteDatabase;
    Favorite_Fragment favorite_fragment;

    public FavoriteAdapter(Context context, List<BasketModel> basketModelList, Favorite_Fragment favorite_fragment) {
        this.context = context;
        this.basketModelList = basketModelList;
        this.favorite_fragment = favorite_fragment;
    }

    @NonNull
    @Override
    public FavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.basket_raw,parent,false);
        return new FavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteHolder holder, final int position) {
        favoriteDatabase = Room.databaseBuilder(getApplicationContext(), Favorite_Database.class, "favoritedb").allowMainThreadQueries().build();
        holder.basket_title.setText(basketModelList.get(position).getTitle());
        //MyHolder.title_img.setImageResource(basketModelList.get(position).getImg());
        Picasso.with(context).load(basketModelList.get(position).getImg()).into(holder.title_img);
        holder.counter.setText(basketModelList.get(position).getNum_of_cart());
        setFadeAnimation(holder.itemView);
        //Uri uri= Uri.parse(basketModelList.get(position).getImg());
        // File file =new File(uri.getPath());
        //Picasso.with(context).load(file).into(MyHolder.title_img);
        holder.delete_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favorite_fragment.senddate(position);
            }
        });
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
    }

    private void setFadeAnimation(View itemView) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(3000);
        itemView.startAnimation(anim);
    }


    @Override
    public int getItemCount() {
        return basketModelList.size();
    }

    class FavoriteHolder extends RecyclerView.ViewHolder{
        ImageView plus_circle, minus_circle, title_img, delete_img;
        TextView basket_title,counter;
        CheckBox c_red,c_blue,c_black;
        public FavoriteHolder(View itemView) {
            super(itemView);
            plus_circle=itemView.findViewById(R.id.plus_circle);
            minus_circle=itemView.findViewById(R.id.minus_circle);
            basket_title=itemView.findViewById(R.id.basket_title);
            counter=itemView.findViewById(R.id.counter);
            c_red=itemView.findViewById(R.id.checkbox_red);
            c_blue=itemView.findViewById(R.id.checkbox_blue);
            c_black=itemView.findViewById(R.id.checkbox_black);
            title_img=itemView.findViewById(R.id.img);
            delete_img = itemView.findViewById(R.id.img_delete);
        }
    }
}
