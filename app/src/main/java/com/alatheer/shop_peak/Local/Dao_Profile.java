package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

/**
 * Created by M.Hamada on 27/04/2019.
 */
@android.arch.persistence.room.Dao
public interface Dao_Profile {
    @Insert
    void addproductItem(ProfileModel profileModel);
    @Query("select * from profile_products")
    List<ProfileModel> get_profile_data();
    @Query("select * from profile_products where id IN (1,2,3,4,5,6) and TiTle=:name ")
    List<ProfileModel>getdata1(String name);
}
