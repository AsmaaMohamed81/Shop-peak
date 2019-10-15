package com.alatheer.shop_peak.Model;



import java.util.ArrayList;

import androidx.room.TypeConverter;

/**
 * Created by M.Hamada on 25/04/2019.
 */

public class Converter {
    @TypeConverter
    public int[] fromArray(int image) {
        int []images ={image};
        return images;
    }
}
