package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Activities.Category_Activity;
import com.alatheer.shop_peak.Activities.MainActivity;
import com.alatheer.shop_peak.Local.Favorite_Database;
import com.alatheer.shop_peak.Local.ProfileDatabase;
import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by M.Hamada on 22/03/2019.
 */

public class main_sub_Adapter extends RecyclerView.Adapter {
    List<HomeModel> listofhome;
    Context context;
    Category_Activity category_activity;
    List<HomeModel> full_list_ofhome;
    Favorite_Database favorite_database;
    ProfileDatabase profileDatabase;
    boolean accepted = false;
    CustomSwipeAdapter customSwipeAdapter;
    HomeModel homeModel;
    long id;

    private final int ITEM_DATA = 1;
    private final int ITEM_PROGRESS = 2;

    private int lastVisibleItem , totalItemCount;
    private boolean canLoadMore = true;

    private main_sub_Adapter.LoadMoreListener loadMoreListener;



    public main_sub_Adapter(List<HomeModel> listofhome, Context context,final RecyclerView recView) {
        this.listofhome = listofhome;
        this.context = context;
        this.category_activity = (Category_Activity) context;

        recView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                if (dy>0)
                {
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) recView.getLayoutManager();
                    lastVisibleItem = gridLayoutManager.findLastVisibleItemPosition();
                    totalItemCount = gridLayoutManager.getChildCount();

                    if ((totalItemCount-lastVisibleItem)<=30 && canLoadMore)
                    {
                        if (loadMoreListener!=null)
                        {
                            loadMoreListener.onLoadMore();
                            canLoadMore = false;
                        }
                    }
                }


            }
        });
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_category_row, parent, false);
        MyHolder MyHolder = new MyHolder(view);
        return MyHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder MyHolder, final int position) {
        if (MyHolder instanceof MyHolder)
        {
            MyHolder myHolder = (MyHolder) MyHolder;
            homeModel = listofhome.get(position);
            myHolder.BindData(homeModel);

            myHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    homeModel = listofhome.get(position);

                    if (homeModel!=null)
                    {
//                        fragment_offers.setItemForDetails(products);

                    }
                }
            });

        }else if (MyHolder instanceof ProgressHolder)
        {
            ProgressHolder progressHolder = (ProgressHolder) MyHolder;
            progressHolder.progBar.setIndeterminate(true);
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
                tv_after_discount.setText(homeModel.priceAfterDis);


        }
    }

    public class ProgressHolder extends RecyclerView.ViewHolder {
        private ProgressBar progBar;
        public ProgressHolder(View itemView) {
            super(itemView);
            progBar = itemView.findViewById(R.id.progBar);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (listofhome.get(position) != null)
        {
            return ITEM_DATA;
        }else
        {
            return ITEM_PROGRESS;
        }

    }


    public interface LoadMoreListener
    {
        void onLoadMore();
    }

    public void setOnLoadMoreListener(LoadMoreListener loadMoreListener)
    {
        this.loadMoreListener = loadMoreListener;
    }

    public void setLoaded()
    {
        this.canLoadMore = true;
    }


}