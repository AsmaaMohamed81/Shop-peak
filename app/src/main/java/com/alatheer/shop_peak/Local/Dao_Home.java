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
    @Insert
    void addproductItem(HomeModel homeModel);
    @Query("select * from myproducts")
    List<HomeModel> get_profile_data();
    @Query("delete from myproducts")
    void delete_all();
}
