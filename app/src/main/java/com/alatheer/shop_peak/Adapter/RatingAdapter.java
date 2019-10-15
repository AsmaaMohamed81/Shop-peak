package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alatheer.shop_peak.Model.RatingModel;
import com.alatheer.shop_peak.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

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


    @Override
    public RatingHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.rating_item, viewGroup, false);
        return new RatingHolder(view);
    }

    @Override
    public void onBindViewHolder( RatingHolder ratingHolder, int i) {
        ratingHolder.name.setText(ratingModelList.get(i).userName);
        ratingHolder.rate.setRating(Float.parseFloat(ratingModelList.get(i).rateValue));
    }

    @Override
    public int getItemCount() {
        return ratingModelList.size();
    }

    class RatingHolder extends RecyclerView.ViewHolder {
        TextView name;
        RatingBar rate;

        public RatingHolder( View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_name);
            rate = itemView.findViewById(R.id.ratbar);
        }
    }
}
