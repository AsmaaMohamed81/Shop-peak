package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.alatheer.shop_peak.Activities.Signup_Activity;
import com.alatheer.shop_peak.Activities.Vender_Signup_Activity;
import com.alatheer.shop_peak.Fragments.Client_Profile_Fragment;
import com.alatheer.shop_peak.Model.Govern;
import com.alatheer.shop_peak.R;

import java.util.List;

public class governAdapter extends RecyclerView.Adapter<governAdapter.Holder>{

    Context context;

    List<Govern> governsList;
    Fragment fragment;
    Signup_Activity signup_activity;
    Vender_Signup_Activity vender_signup_activity;
     Client_Profile_Fragment client_profile_fragment;

    public governAdapter(Context context, List<Govern> governsList) {
        this.context = context;
        this.governsList = governsList;

    }
    public governAdapter(Fragment fragment, List<Govern> governsList) {
        this.context = context;
        this.governsList = governsList;

    }



    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_txt,parent,false);
        return new governAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Holder holder, int position) {

        Govern govern=governsList.get(position);
        holder.Bind(govern);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();

                if (context instanceof Signup_Activity){


                    signup_activity=(Signup_Activity) context;
                    signup_activity.pos_govern(pos);

                }else if (context instanceof Vender_Signup_Activity){

                    vender_signup_activity=(Vender_Signup_Activity) context;

                    vender_signup_activity.pos_govern(pos);

                }
                else if(fragment instanceof  Client_Profile_Fragment){
                    client_profile_fragment = (Client_Profile_Fragment) fragment;
                    client_profile_fragment.pos_govern(pos);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return governsList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.text);
        }

        public void Bind(Govern govern) {
            textView.setText(govern.getName());
        }
    }
    }
