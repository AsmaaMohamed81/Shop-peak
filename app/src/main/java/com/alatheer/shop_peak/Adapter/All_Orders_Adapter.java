package com.alatheer.shop_peak.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alatheer.shop_peak.Fragments.All_Orders_Delivary;
import com.alatheer.shop_peak.Fragments.Orders_Accepted;
import com.alatheer.shop_peak.Fragments.Orders_Delivered;
import com.alatheer.shop_peak.Model.Asnaf;
import com.alatheer.shop_peak.Model.DeliveryOrder;
import com.alatheer.shop_peak.Model.OrderNotify;
import com.alatheer.shop_peak.Model.RatingModel2;
import com.alatheer.shop_peak.Model.SendNotify;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class All_Orders_Adapter extends RecyclerView.Adapter<All_Orders_Adapter.All_Orders_Holder> {
    List<DeliveryOrder> deliveryOrders ;
    Context context;
    androidx.fragment.app.Fragment fragment;
    All_Orders_Delivary all_orders_delivary;
    Orders_Accepted orders_accepted;
    Orders_Delivered orders_delivered;

    public All_Orders_Adapter(List<DeliveryOrder> deliveryOrders, Context context) {
        this.deliveryOrders = deliveryOrders;
        this.context = context;
    }

    public All_Orders_Adapter(List<DeliveryOrder> deliveryOrders, Context context, Fragment fragment) {
        this.deliveryOrders = deliveryOrders;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public All_Orders_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.delivery_item,parent,false);
        All_Orders_Holder all_orders_holder = new All_Orders_Holder(view);
        return all_orders_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final All_Orders_Holder holder, final int position) {
     String vender_name = deliveryOrders.get(position).getOrder().getStoreName();
     String vender_address = deliveryOrders.get(position).getOrder().getStoreAdress();
     String client_name = deliveryOrders.get(position).getOrder().getClientName();
     String client_address = deliveryOrders.get(position).getOrder().getAdress();
     final List<Asnaf>asnafList = deliveryOrders.get(position).getAsnaf();
     //int price =deliveryOrders.get(position).getAsnaf().get(position).getPrice();
     holder.client_name.setText(client_name);
     holder.client_address.setText(client_address);
     if(fragment instanceof Orders_Accepted){
         orders_accepted = (Orders_Accepted) fragment;
         holder.accept.setText("Do It");
         holder.txt_order.setText("لقد وافقت علي توصيل طلب الي");
     }else if(fragment instanceof Orders_Delivered){
            orders_delivered = (Orders_Delivered) fragment;
            holder.accept.setVisibility(View.GONE);
            holder.cancel.setVisibility(View.GONE);
            holder.txt_order.setText("لقد قمت بتوصيل طلب الي");
     }

     holder.order_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = LayoutInflater.from(context).inflate(R.layout.orders_list, null);
                final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(context)
                        .setCancelable(false)
                        .create();
                Sanf_Adapter sanf_adapter = new Sanf_Adapter(asnafList,deliveryOrders,context);
                RecyclerView recyclerView = v.findViewById(R.id.items_list);
                Button OK = v.findViewById(R.id.btn_ok);
                recyclerView.setAdapter(sanf_adapter);
                //int price = deliveryOrders.get(position).getAsnaf().get(position).getPrice();
               // price = price++;
               // holder.txt_price.setText(price);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
                OK.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
                dialog.setView(v);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }
        });
     holder.accept.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             if(fragment instanceof All_Orders_Delivary){
                 all_orders_delivary = (All_Orders_Delivary) fragment;
                 Accept_Orders(deliveryOrders.get(position).getOrderIdFk(),deliveryOrders.get(position).getDeliveryIdFk()
                         ,deliveryOrders.get(position).getOrder().getStoreIdFk(),deliveryOrders.get(position).getOrder().getClintIdFk(),
                         deliveryOrders.get(position).getOrder().getClientName(),deliveryOrders.get(position).getOrder().getAdress(),deliveryOrders.get(position).getPillNum());
                 all_orders_delivary.get_all_orders();
             }else if(fragment instanceof Orders_Accepted){
                 orders_accepted = (Orders_Accepted) fragment;
                 orders_accepted.get_accepted_orders();
                 Delivered_Order(deliveryOrders.get(position).getOrderIdFk(),deliveryOrders.get(position).getDeliveryIdFk()
                 ,deliveryOrders.get(position).getOrder().getStoreIdFk(),deliveryOrders.get(position).getOrder().getClintIdFk()
                 ,deliveryOrders.get(position).getOrder().getClientName(),deliveryOrders.get(position).getOrder().getAdress(),deliveryOrders.get(position).getPillNum());
                 orders_accepted.get_accepted_orders();
             }else if(fragment instanceof Orders_Delivered){
                  orders_delivered = (Orders_Delivered) fragment;
                  orders_delivered.get_delivered_orders();

             }

         }
     });

    }

    /*private void CreateDialog() {
        final android.app.AlertDialog dialog = new android.app.AlertDialog.Builder(context)
                .setCancelable(false)
                .create();

        View v = LayoutInflater.from(context).inflate(R.layout.orders_list, null);

        Sanf_Adapter sanf_adapter = new Sanf_Adapter(asnafList,deliveryOrders,context);
        RecyclerView recyclerView = v.findViewById(R.id.items_list);
        Button OK = v.findViewById(R.id.btn_ok);
        recyclerView.setAdapter(sanf_adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().getAttributes().windowAnimations = R.style.custom_dialog_animation;
        dialog.setView(v);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }*/

    private void Delivered_Order(String orderIdFk, final String deliveryIdFk, String storeIdFk, String clintIdFk, final String clientName, final String adress, String pillNum) {
        Api.getService().delivered_order(orderIdFk,deliveryIdFk,storeIdFk,clintIdFk,pillNum).enqueue(new Callback<RatingModel2>() {
            @Override
            public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==1){
                        orders_accepted.get_accepted_orders();
                        Api.getService().send_order_notify(deliveryIdFk,adress+"الي "+ clientName+"تم توصيل الطلب بواسطة الطيار ").enqueue(new Callback<OrderNotify>() {
                            @Override
                            public void onResponse(Call<OrderNotify> call, Response<OrderNotify> response) {
                                Toast.makeText(context,"you receive this order",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<OrderNotify> call, Throwable t) {

                            }
                        });



                    }
                    }
                }


            @Override
            public void onFailure(Call<RatingModel2> call, Throwable t) {

            }
        });
    }

    private void Accept_Orders(String order_id_fk, final String delivery_id_fk, String store_id_fk, final String client_id_fk, final String clientName, final String adress, String pillNum) {
        Api.getService().accept_order(order_id_fk,delivery_id_fk,store_id_fk,client_id_fk,pillNum).enqueue(new Callback<RatingModel2>() {
            @Override
            public void onResponse(Call<RatingModel2> call, Response<RatingModel2> response) {
                if(response.isSuccessful()){
                    if(response.body().getSuccess()==1){
                      all_orders_delivary.get_all_orders();
                        Api.getService().send_order_notify(delivery_id_fk,adress+"الي "+ clientName +"تم قبول الطلب  بواسطة الطيار").enqueue(new Callback<OrderNotify>() {
                            @Override
                            public void onResponse(Call<OrderNotify> call, Response<OrderNotify> response) {
                                Toast.makeText(context,"you accept this order",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<OrderNotify> call, Throwable t) {

                            }
                        });



                    }
                    }
                }


            @Override
            public void onFailure(Call<RatingModel2> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return deliveryOrders.size();
    }

    class All_Orders_Holder extends  RecyclerView.ViewHolder{
        TextView seller_name,seller_address,client_name,client_address,order_details,txt_order,txt_price;
        RecyclerView order_products;
        Button accept,cancel;
        public All_Orders_Holder(@NonNull View itemView) {
            super(itemView);
            txt_order = itemView.findViewById(R.id.txt_one);
            client_name = itemView.findViewById(R.id.client_name);
            client_address = itemView.findViewById(R.id.client_address);
            accept = itemView.findViewById(R.id.btn_accept);
            cancel = itemView.findViewById(R.id.btn_cancel);
            order_details = itemView.findViewById(R.id.order_details);
            txt_price = itemView.findViewById(R.id.txt_price);
        }
    }
}
