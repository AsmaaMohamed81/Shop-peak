package com.alatheer.shop_peak.Local;


import com.alatheer.shop_peak.Model.BasketModel;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * Created by M.Hamada on 25/04/2019.
 */
@Dao
public interface Dao_favorite {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long add_favorite(BasketModel basketModel);
    @Query("delete from products")
    void delete_all_favorite();
    @Delete
    void delete_favorite(BasketModel basketModel);

    @Query("delete from products where id=:id")
    void Delete_Item(int id);
    @Query("select * from products")
    List<BasketModel> getdata();
}

