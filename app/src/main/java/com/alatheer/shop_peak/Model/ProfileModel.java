package com.alatheer.shop_peak.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.Bitmap;

/**
 * Created by M.Hamada on 25/03/2019.
 */
@Entity(tableName = "profile_products")
public class ProfileModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "TiTle")
    String title;
    @ColumnInfo(name = "Image")
    String Image;

    public ProfileModel(int id, String title, String Image) {
        this.id = id;
        this.title = title;
        this.Image = Image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return Image;
    }
    public void setImage(String image) {
        Image = image;
    }
}
