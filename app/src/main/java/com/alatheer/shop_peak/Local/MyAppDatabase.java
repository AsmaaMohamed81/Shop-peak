package com.alatheer.shop_peak.Local;

import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.ProfileModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by M.Hamada on 02/04/2019.
 */

@Database(entities = {OrderItemList.class},version = 1)
public abstract class MyAppDatabase extends RoomDatabase {
    public abstract Dao dao();

}
