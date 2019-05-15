package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

/**
 * Created by M.Hamada on 02/04/2019.
 */

@android.arch.persistence.room.Dao
public interface Dao {
    @Insert
     void addproduct(BasketModel basketModel);
    @Query("select * from products")
     List<BasketModel> getdata();
    @Query("delete from products")
     void deleteproduct();

    @Query("delete from products where id=:id")
    void Delete_Item(int id);
    @Update
     void editproduct(BasketModel basketModel);
}
