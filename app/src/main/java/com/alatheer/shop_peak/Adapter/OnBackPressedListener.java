package com.alatheer.shop_peak.Adapter;

import android.app.FragmentManager;
import android.support.v4.app.FragmentActivity;

/**
 * Created by M.Hamada on 18/05/2019.
 */

public class OnBackPressedListener implements OnBackPressed {
    private final FragmentActivity activity;

    public OnBackPressedListener(FragmentActivity activity) {
        this.activity = activity;
    }

    @Override
    public void DoBack() {
        activity.getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
}
