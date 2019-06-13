package com.alatheer.shop_peak.Local;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

/**
 * Created by M.Hamada on 02/04/2019.
 */

@android.arch.persistence.room.Dao
public interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void addproduct(OrderItemList orderItemList);
    @Query("select * from morders")
         List<OrderItemList> getdata();
    @Query("delete from morders")
     void deleteproduct();

    @Query("delete from morders where sanf_id_fk=:id")
    void Delete_Item(int id);
    @Update
    void editproduct(OrderItemList basketModel);
}
