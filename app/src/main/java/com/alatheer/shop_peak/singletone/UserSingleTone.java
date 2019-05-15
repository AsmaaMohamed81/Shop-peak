package com.alatheer.shop_peak.singletone;

import com.alatheer.shop_peak.Model.UserModel;

public class UserSingleTone {
    private static  UserSingleTone instance=null;
    private UserModel userModel=null;
    private UserSingleTone() {
    }

    public static synchronized UserSingleTone getInstance()
    {
        if (instance==null)
        {
            instance = new UserSingleTone();
        }
        return instance;
    }

    public void setUserModel(UserModel userModel)
    {
        this.userModel = userModel;
    }

    public UserModel getUserModel()
    {
        return this.userModel;
    }


    public void clearData()
    {
        this.userModel=null;
    }

}
