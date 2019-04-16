package com.alatheer.shop_peak.Local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

import java.util.Map;
import java.util.Set;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by M.Hamada on 16/04/2019.
 */

public class MySharedPreference {
    Context context;

    public MySharedPreference(Context context) {
        this.context = context;
    }

    public void PutDataInSharedPreference(String name, String image_url){
        SharedPreferences.Editor editor=context.getSharedPreferences("user_data",MODE_PRIVATE).edit();
        editor.putString("name",name);
        editor.putString("image_url",image_url);
        editor.apply();
    }
    public String[] getDataFromSharedPreference(){
        SharedPreferences mPrefs = context.getSharedPreferences("user_data", MODE_PRIVATE);
        String name=mPrefs.getString("name",null);
        String url=mPrefs.getString("image_url",null);
        return new String[]{name,url};
    }
    public void DeleteallDatainSharedPreference(){
        SharedPreferences mPrefs = context.getSharedPreferences("user_data", MODE_PRIVATE);
        mPrefs.edit().clear();
    }
}
