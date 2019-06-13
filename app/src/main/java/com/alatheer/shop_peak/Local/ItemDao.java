package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alatheer.shop_peak.Model.Item2;
import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

/**
 * Created by M.Hamada on 12/06/2019.
 */
@android.arch.persistence.room.Dao
public interface ItemDao {
    @Insert
    void addproductItem(Item2 item2);
    @Query("select * from myItem")
    List<Item2> get_profile_data();
}
