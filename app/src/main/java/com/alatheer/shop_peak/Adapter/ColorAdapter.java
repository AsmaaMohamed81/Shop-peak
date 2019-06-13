package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.alatheer.shop_peak.Model.Color;
import com.alatheer.shop_peak.R;

import java.util.List;

/**
 * Created by M.Hamada on 03/06/2019.
 */

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorHolder>{
    Context context;
    String[] colors;

    public ColorAdapter(Context context, String[] colors) {
        this.context = context;
        this.colors = colors;
    }

    @NonNull
    @Override
    public ColorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(context).inflate(R.layout.color_item,viewGroup,false);
        ColorHolder colorHolder = new ColorHolder(view);
        return colorHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ColorHolder colorHolder, int i) {
        if (!colors[i].isEmpty()) {
            colorHolder.linearLayout_color.setBackgroundColor(android.graphics.Color.parseColor("#" + colors[i]));
        }
    }

    @Override
    public int getItemCount() {
        return colors.length;
    }

    class ColorHolder extends  RecyclerView.ViewHolder{
        Button linearLayout_color;
        public ColorHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout_color = itemView.findViewById(R.id.btn_color);
        }
    }
}
