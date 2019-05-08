package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.HomeModel;

/**
 * Created by M.Hamada on 28/04/2019.
 */

@Database(entities = {HomeModel.class},version = 1)
public abstract class HomeDatabase extends RoomDatabase {
    public abstract Dao_Home dao_home();

}
