package com.alatheer.shop_peak.Adapter;


import com.alatheer.shop_peak.Fragments.All_Orders_Delivary;
import com.alatheer.shop_peak.Fragments.Orders_Accepted;
import com.alatheer.shop_peak.Fragments.Orders_Delivered;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

/**
 * Created by M.Hamada on 23/05/2019.
 */

public class PagerAdapterTwo extends FragmentStatePagerAdapter {
    int mNoOfTaps;

    public PagerAdapterTwo(FragmentManager fm, int mNoOfTaps) {
        super(fm);
        this.mNoOfTaps = mNoOfTaps;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                All_Orders_Delivary all_orders_delivary = new All_Orders_Delivary();
                return all_orders_delivary;
            case 1:
                Orders_Accepted orders_delivered = new Orders_Accepted();
                return orders_delivered;
            case 2:
                Orders_Delivered orders_un_delivered = new Orders_Delivered();
                return orders_un_delivered;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTaps;
    }
}
