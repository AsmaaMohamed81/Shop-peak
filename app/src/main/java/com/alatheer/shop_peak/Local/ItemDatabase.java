package com.alatheer.shop_peak.Local;


import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.Item2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by M.Hamada on 12/06/2019.
 */
@Database(entities = {Item2.class},version = 1)
public abstract class ItemDatabase extends RoomDatabase {
    public abstract ItemDao itemDao() ;

}
