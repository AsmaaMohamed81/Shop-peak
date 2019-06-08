package com.alatheer.shop_peak.Adapter;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.BuildConfig;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.Item;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by M.Hamada on 22/03/2019.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.Image2holder> {
    List<HomeModel> listofhome;
    Context context;
    MainActivity mainActivity;
    List<HomeModel> full_list_ofhome;
    Favorite_Database favorite_database;
    ProfileDatabase profileDatabase;
    boolean accepted = false;
    CustomSwipeAdapter customSwipeAdapter;

    HomeModel model;
    long id;
    public HomeAdapter(List<HomeModel> listofhome, Context context) {
        this.listofhome = listofhome;
        this.context = context;
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
        profileDatabase= Room.databaseBuilder(getApplicationContext(),ProfileDatabase.class,"product_db").allowMainThreadQueries().build();
        favorite_database = Room.databaseBuilder(context,Favorite_Database.class,"favoritedb").allowMainThreadQueries().build();
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
        Picasso.with(context).load(vender_image).into(holder.img_profile);
        holder.text_profile.setText(vender_name);

           final String[] image_resources = {listofhome.get(position).mainImg};
           final String[] image_resources2 =listofhome.get(position).img;
           if(image_resources2 == null){
               customSwipeAdapter = new CustomSwipeAdapter(image_resources, context);
               holder.viewPager.setAdapter(customSwipeAdapter);
               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       mainActivity.sendHomeItem(image_resources, itemList, sanf_name, details, price, sanf_id, rating, store_id,colors,price_before_discount);
                   }
               });

           }else {
               customSwipeAdapter = new CustomSwipeAdapter(image_resources, context);
               holder.viewPager.setAdapter(customSwipeAdapter);
               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       mainActivity.sendHomeItem(image_resources, itemList, sanf_name, details, price, sanf_id, rating, store_id,colors,price_before_discount);
                   }
               });
           }
            mainActivity.sendLink(link);
            mainActivity.passdata(sanf_name, listofhome);

        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();



                if (holder.fav.isChecked()) {
                    accepted = true;

                    mainActivity.addfavPos(pos);
                    Log.e("add_to_favorite","true");
                } else {
                    accepted = false;

                    mainActivity.deletfavPos(pos);

                    Log.e("delete_from_favorite","true");
                }
            }
        });
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SHOP_PEAK");
                String shareMessage= "\nLet me recommend you this application\n\n";
                shareMessage = shareMessage + link;
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                context.startActivity(Intent.createChooser(shareIntent, "choose one"));
            }
        });
        holder.ratbar.setRating(Float.parseFloat(rating));
        holder.home_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 model=listofhome.get(position);

                mainActivity.profilePos(model);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listofhome.size();
    }

    /* @Override
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
     };*/
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
