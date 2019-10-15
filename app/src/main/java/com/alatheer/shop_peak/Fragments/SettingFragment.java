package com.alatheer.shop_peak.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.alatheer.shop_peak.R;
import com.alatheer.shop_peak.Tags.Tags;
import com.alatheer.shop_peak.languagehelper.LanguageHelper;
import com.alatheer.shop_peak.preferance.MySharedPreference;

import androidx.annotation.Nullable;
import io.paperdb.Paper;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class SettingFragment extends PreferenceFragment {
    MySharedPreference mySharedPreference;

    @Override
    public void onAttach(Context context) {

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        mySharedPreference=new MySharedPreference(getActivity());

//        UserModel userModel = mySharedPreference.Get_UserData(getActivity());
//
//        String name=userModel.getName();
//        String url=userModel.getImage_url();
//        String email=userModel.getEmail();


//        EditTextPreference editTextPreference= (EditTextPreference) findPreference("key_name");
//        editTextPreference.setSummary(name);
//        EditTextPreference editTextPreference1= (EditTextPreference) findPreference("key_email");
//        editTextPreference1.setSummary(email);
    }
}
