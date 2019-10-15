package com.alatheer.shop_peak.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by M.Hamada on 25/03/2019.
 */
@Entity(tableName = "profile_products")
public class ProfileModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "TiTle")
    String title;
    @ColumnInfo(name = "Image_title")
    String Image_title;

    public String getImage_title() {
        return Image_title;
    }

    public void setImage_title(String image_title) {
        Image_title = image_title;
    }

    @ColumnInfo(name = "Image1")

    String Image1;
    @ColumnInfo(name = "Image2")
    String Image2;
    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public ProfileModel( String title, String Image_title,String Image1,String Image2) {
        this.id = id;
        this.title = title;
        this.Image1 = Image1;
        this.Image2 = Image2;
        this.Image_title=Image_title;
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
        return Image1;
    }
    public void setImage(String image) {
        Image1 = image;
    }
}
