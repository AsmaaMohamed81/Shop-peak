package com.alatheer.shop_peak.Fragments;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alatheer.shop_peak.Local.MySharedPreference;
import com.alatheer.shop_peak.R;


public class SettingFragment extends PreferenceFragment {
    MySharedPreference mySharedPreference;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        mySharedPreference=new MySharedPreference(getActivity());
        String[] data= mySharedPreference.getDataFromSharedPreference();
        String name=data[0];
        String url=data[1];
        String email=data[2];
        EditTextPreference editTextPreference= (EditTextPreference) findPreference("key_name");
        editTextPreference.setSummary(name);
        EditTextPreference editTextPreference1= (EditTextPreference) findPreference("key_email");
        editTextPreference1.setSummary(email);
    }
}
