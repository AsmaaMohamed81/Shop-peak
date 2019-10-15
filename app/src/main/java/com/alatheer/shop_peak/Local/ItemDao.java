package com.alatheer.shop_peak.Local;



import com.alatheer.shop_peak.Model.Item2;
import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by M.Hamada on 12/06/2019.
 */
@Dao
public interface ItemDao {
    @Insert
    void addproductItem(Item2 item2);
    @Query("select * from myItem")
    List<Item2> get_profile_data();
}
