package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Adapter.All_Orders_Adapter;
import com.alatheer.shop_peak.Adapter.PassData;
import com.alatheer.shop_peak.Model.DeliveryOrder;
import com.alatheer.shop_peak.Model.UserModel1;
import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;
import com.alatheer.shop_peak.service.Api;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class Orders_Delivered extends Fragment {
    PassData passData;
    MySharedPreference mySharedPreference;
    UserModel1 userModel1;
    List<DeliveryOrder>deliveryOrders;
    RecyclerView recyclerView_delivered_orders;
    RecyclerView.LayoutManager layoutManager2;
    All_Orders_Adapter all_orders_adapter;
    SwipeRefreshLayout swipeRefresh;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof PassData) {
            passData = (PassData) context;
        }
        Paper.init(context);
        String lang = Paper.book().read("language");

        if (Paper.book().read("language").equals("ar")) {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.AR_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());

        } else {
            CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                    .setDefaultFontPath(Tags.EN_FONT_NAME)
                    .setFontAttrId(R.attr.fontPath)
                    .build());
        }
        super.onAttach(CalligraphyContextWrapper.wrap(LanguageHelper.onAttach(context, lang)));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ordres_un_delivered, container, false);
        initview(view);
        return view;
    }

    private void initview(View view) {
        mySharedPreference= MySharedPreference.getInstance();
        userModel1=mySharedPreference.Get_UserData(getActivity());
        deliveryOrders = new ArrayList<>();
        recyclerView_delivered_orders = view.findViewById(R.id.recycler_delivered_orders);
        get_delivered_orders();
        swipeRefresh = view.findViewById(R.id.swipe_container);
        swipeRefresh.setColorSchemeResources(R.color.colorAccent);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               get_delivered_orders();
               swipeRefresh.setRefreshing(false);
            }
        });


    }

    public void get_delivered_orders() {
        String id = userModel1.getId();
        Log.e("id",id);
        Api.getService().get_all_ordeers(id,"2").enqueue(new Callback<List<DeliveryOrder>>() {
            @Override
            public void onResponse(Call<List<DeliveryOrder>> call, Response<List<DeliveryOrder>> response) {
                if(response.isSuccessful()){
                    deliveryOrders = response.body();
                    recyclerView_delivered_orders.setHasFixedSize(true);
                    layoutManager2 = new LinearLayoutManager(getActivity());
                    recyclerView_delivered_orders.setLayoutManager(layoutManager2);
                    all_orders_adapter = new All_Orders_Adapter(deliveryOrders, getActivity(), Orders_Delivered.this);
                    recyclerView_delivered_orders.setAdapter(all_orders_adapter);

                }
            }

            @Override
            public void onFailure(Call<List<DeliveryOrder>> call, Throwable t) {
                Log.v("error",t.getMessage());
            }
        });
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
