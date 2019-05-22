package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.RatingModel;
import com.alatheer.shop_peak.R;

import java.util.List;

/**
 * Created by M.Hamada on 22/05/2019.
 */

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingHolder> {
    Context context;
    List<RatingModel> ratingModelList;

    public RatingAdapter(Context context, List<RatingModel> ratingModelList) {
        this.context = context;
        this.ratingModelList = ratingModelList;
    }

    @NonNull
    @Override
    public RatingHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rating_item, viewGroup, false);
        return new RatingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingHolder ratingHolder, int i) {
        ratingHolder.name.setText(ratingModelList.get(i).getUsername());
        ratingHolder.rate.setNumStars(ratingModelList.get(i).getNum_of_stars());
    }

    @Override
    public int getItemCount() {
        return ratingModelList.size();
    }

    class RatingHolder extends RecyclerView.ViewHolder {
        TextView name;
        RatingBar rate;

        public RatingHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            rate = itemView.findViewById(R.id.ratbar);
        }
    }
}
