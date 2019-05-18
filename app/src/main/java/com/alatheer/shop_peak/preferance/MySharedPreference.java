package com.alatheer.shop_peak.preferance;

import android.content.Context;
import android.content.SharedPreferences;

import com.alatheer.shop_peak.Model.UserModel;
import com.alatheer.shop_peak.Tags.Tags;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by M.Hamada on 16/04/2019.
 */

public class MySharedPreference {
    Context context;
    SharedPreferences mPrefs;

    public MySharedPreference(Context context) {
        this.context = context;
    }

    public void Create_Update_UserData(Context context, UserModel userModel)
    {
        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(userModel);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("user_data",userData);
        editor.apply();
        Create_Update_Session(context, Tags.session_login);

    }

    public void Create_Update_Session(Context context,String session)
    {
        mPrefs = context.getSharedPreferences("session", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("state",session);
        editor.apply();
    }

    public UserModel Get_UserData(Context context){

        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson=new Gson();
        String userData = mPrefs.getString("user_data", "");
        UserModel userModel=gson.fromJson(userData,UserModel.class);
        return userModel;


    }

    public void ClearData(Context context) {
        UserModel userModel = null;
        mPrefs = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String userData = gson.toJson(userModel);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putString("user_data", userData);
        editor.apply();
        Create_Update_Session(context, Tags.session_login);
    }

//
//    public void PutDataInSharedPreference(String name, String image_url,String email){
//        mPrefs =context.getSharedPreferences("user_data",MODE_PRIVATE);
//        SharedPreferences.Editor editor=mPrefs.edit();
//        editor.putString("name",name);
//        editor.putString("image_url",image_url);
//        editor.putString("email",email);
//        editor.apply();
//    }
//    public String[] getDataFromSharedPreference(){
//        mPrefs = context.getSharedPreferences("user_data", MODE_PRIVATE);
//        String name=mPrefs.getString("name",null);
//        String url=mPrefs.getString("image_url",null);
//        String email=mPrefs.getString("email",null);
//        return new String[]{name,url,email};
//    }
//    public void DeleteallDatainSharedPreference(){
//        mPrefs = context.getSharedPreferences("user_data", MODE_PRIVATE);
//        SharedPreferences.Editor editor=mPrefs.edit();
//        editor.putString("name",null);
//        editor.putString("image_url",null);
//        editor.putString("email",null);
//        editor.apply();
//    }


}
