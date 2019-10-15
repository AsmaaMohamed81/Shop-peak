package com.alatheer.shop_peak.Local;



import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by M.Hamada on 27/04/2019.
 */

public interface Dao_Profile {
    @Insert
    void addproductItem(ProfileModel profileModel);
    @Query("select * from profile_products")
    List<ProfileModel> get_profile_data();
    @Query("select * from profile_products where id IN (1,2,3,4,5,6) and TiTle=:name ")
    List<ProfileModel>getdata1(String name);
}
