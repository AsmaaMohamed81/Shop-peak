package com.alatheer.shop_peak.Adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import android.widget.Toast;

import com.alatheer.shop_peak.Activities.DetailsActivity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;

import java.io.File;
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
    Favorite_Database favorite_database;
    boolean accepted = false;
    CustomSwipeAdapter customSwipeAdapter;
    long id;
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
    public void onBindViewHolder(@NonNull final Image2holder holder, final int position) {
       // Uri uri = Uri.parse(listofhome.get(position).getProduct_image());
        //final File path = new File(uri.getPath());

        favorite_database = Room.databaseBuilder(context,Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
        final int[] image_resources = listofhome.get(position).getImage_resources();
        final int image = image_resources[0];
        final String title = listofhome.get(position).getProduct_title();
        final String des = listofhome.get(position).getProduct_describtion();
        final String price = listofhome.get(position).getProduct_price();
        final String gender = listofhome.get(position).getGender();
        final String vender_name=listofhome.get(position).getVender_name();
        final int vender_image=listofhome.get(position).getVender_image();
        holder.img_profile.setImageResource(vender_image);
        holder.text_profile.setText(vender_name);
        customSwipeAdapter = new CustomSwipeAdapter(image_resources, context);
        holder.viewPager.setAdapter(customSwipeAdapter);
        holder.home_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.setSelectProfile(vender_name,vender_image);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mainActivity.sendHomeItem(image_resources,title,des,price,gender);
            }
        });
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.fav.isChecked()) {
                    accepted = true;
                    int id2=Integer.parseInt(holder.order_num.getText().toString());
                    BasketModel basketModel=new BasketModel(id2,title,0+"",gender,price,des,false,false,false,image);
                     id =favorite_database.dao_favorite().add_favorite(basketModel);
                    Log.e("add_to_favorite","true");
                } else {
                    accepted = false;
                    BasketModel basketModel2=new BasketModel();
                    basketModel2.setId((int) id);
                    favorite_database.dao_favorite().delete_favorite(basketModel2);
                    Log.e("delete_from_favorite","true");
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
                    Toast.makeText(context,stars+"", Toast.LENGTH_SHORT).show();
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
        ImageView share;
        CheckBox fav;
        ViewPager viewPager;
        CircleImageView img_profile;
        TextView text_profile;
        RatingBar ratbar;
        LinearLayout home_linear;
        EditText order_num;
        public Image2holder(View itemView) {
            super(itemView);
            viewPager=itemView.findViewById(R.id.viewpager);
            fav=itemView.findViewById(R.id.fav_check);
            share=itemView.findViewById(R.id.img_share);
            img_profile=itemView.findViewById(R.id.img_c);
            text_profile=itemView.findViewById(R.id.txt_name);
            ratbar=itemView.findViewById(R.id.ratbar);
            home_linear=itemView.findViewById(R.id.home_linear);
            order_num=itemView.findViewById(R.id.order_num);
        }
    }



}
