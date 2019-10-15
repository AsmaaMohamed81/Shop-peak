package com.alatheer.shop_peak.Local;



import com.alatheer.shop_peak.Model.BasketModel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * Created by M.Hamada on 25/04/2019.
 */
@Database(entities = {BasketModel.class},version = 1)
public abstract class Favorite_Database extends RoomDatabase {
    public abstract Dao_favorite dao_favorite();
}
