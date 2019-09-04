package com.alatheer.shop_peak.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alatheer.shop_peak.Fragments.DescriptionFragment;
import com.alatheer.shop_peak.Fragments.Fragment_Details;
import com.alatheer.shop_peak.Fragments.RatingFragment;

/**
 * Created by M.Hamada on 23/05/2019.
 */
/*
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
                Fragment_two_Details fragment_two_details = new Fragment_two_Details();
                return fragment_two_details;
            case 1:
                DescriptionFragment descriptionFragment = new DescriptionFragment();
                return descriptionFragment;
            case 2:
                RatingFragment ratingFragment = new RatingFragment();
                return ratingFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return mNoOfTaps;
    }
}
*/