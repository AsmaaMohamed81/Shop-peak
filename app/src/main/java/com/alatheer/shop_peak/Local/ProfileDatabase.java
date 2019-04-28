package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alatheer.shop_peak.Model.ProfileModel;

/**
 * Created by M.Hamada on 27/04/2019.
 */
@Database(entities = {ProfileModel.class},version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
    public abstract Dao_Profile dao();
}
