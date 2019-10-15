package com.alatheer.shop_peak.Local;



import com.alatheer.shop_peak.Model.BasketModel;
import com.alatheer.shop_peak.Model.OrderItemList;
import com.alatheer.shop_peak.Model.ProfileModel;

import java.util.List;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

/**
 * Created by M.Hamada on 02/04/2019.
 */
@androidx.room.Dao
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
