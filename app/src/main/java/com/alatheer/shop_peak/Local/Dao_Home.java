package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.*;

import com.alatheer.shop_peak.Model.HomeModel;
import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

/**
 * Created by M.Hamada on 28/04/2019.
 */

@android.arch.persistence.room.Dao
public interface Dao_Home {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addproductItem(HomeModel homeModel);
    @Query("select * from myproducts where id IN (1,2,3,4,5,6)")
    List<HomeModel> get_profile_data();
    @Query("select * from myproducts")
    List<HomeModel>get_profile_data2();

    @Query("select * from myproducts where id IN (1,2,3,4,5,6) and product_title=:title")
    List<HomeModel> getdatasearch(String title);
    @Query("delete from myproducts")
    void delete_all();
}
