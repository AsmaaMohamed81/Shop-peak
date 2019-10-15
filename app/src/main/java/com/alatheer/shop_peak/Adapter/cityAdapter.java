package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Activities.Vender_Signup_Activity;
import com.alatheer.shop_peak.Fragments.Client_Profile_Fragment;
import com.alatheer.shop_peak.Model.City;
import com.alatheer.shop_peak.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class cityAdapter extends RecyclerView.Adapter<cityAdapter.Holder>{

    Context context;

    List<City> cityList;

    Signup_Activity signup_activity;
    Vender_Signup_Activity vender_signup_activity;
    Client_Profile_Fragment client_profile_fragment;


    androidx.fragment.app.Fragment fragment;

    public cityAdapter(Context context,List<City> cityList, Fragment fragment) {
        this.cityList = cityList;
        this.fragment = fragment;
        this.context=context;
    }

    public cityAdapter(Context context, List<City> cityList) {
        this.context = context;
        this.cityList = cityList;


    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_txt,parent,false);
        return new cityAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {

        final City city=cityList.get(position);
        holder.Bind(city);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();


                if (context instanceof Signup_Activity){
                    signup_activity= (Signup_Activity) context;
                    signup_activity.pos_city(pos);


                }else if (context instanceof Vender_Signup_Activity){

                    vender_signup_activity= (Vender_Signup_Activity) context;
                    vender_signup_activity.pos_city(pos);


                }else if(fragment instanceof  Client_Profile_Fragment){
                         client_profile_fragment = (Client_Profile_Fragment) fragment;
                         client_profile_fragment.pos_city(pos);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

        public void Bind(City city) {
            textView.setText(city.getName());
        }
    }
    }
