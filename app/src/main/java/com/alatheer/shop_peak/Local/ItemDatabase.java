package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.Item2;

/**
 * Created by M.Hamada on 12/06/2019.
 */
@Database(entities = {Item2.class},version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao itemDao() ;

}
