package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.*;

import com.alatheer.shop_peak.Model.BasketModel;

import java.util.List;

/**
 * Created by M.Hamada on 25/04/2019.
 */
@android.arch.persistence.room.Dao
public interface Dao_favorite {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add_favorite(BasketModel basketModel);
    @Query("delete from products")
    void delete_all_favorite();
    @Delete
    void delete_favorite(BasketModel basketModel);
    @Query("select * from products")
    List<BasketModel> getdata();
}

