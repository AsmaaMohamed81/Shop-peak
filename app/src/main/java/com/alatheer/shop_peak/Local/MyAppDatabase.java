package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alatheer.shop_peak.Model.ProfileModel;

/**
 * Created by M.Hamada on 02/04/2019.
 */


public abstract class MyAppDatabase extends RoomDatabase {
    public abstract Dao dao();
}
