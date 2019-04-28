package com.alatheer.shop_peak.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by M.Hamada on 23/03/2019.
 */
@Entity(tableName = "Home_Products")
public class HomeModel {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "product_image_url")
    String product_image_url;
    @ColumnInfo(name = "product_title")
    String product_title;
    @ColumnInfo(name = "product_describtion")
    String product_describtion;
    @ColumnInfo(name = "size")
    String size;
    @ColumnInfo(name = "gender")
    String gender;
    @ColumnInfo(name = "Product_price")
    String Product_price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_image_url() {
        return product_image_url;
    }

    public void setProduct_image_url(String product_image_url) {
        this.product_image_url = product_image_url;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public HomeModel(int id,String product_image_url, String product_title, String product_describtion, String Product_price, String size
    ,String gender) {
        this.product_image_url = product_image_url;
        this.product_title = product_title;
        this.product_describtion = product_describtion;
        this.Product_price = Product_price;
        this.size=size;
        this.gender=gender;
        this.id=id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_describtion() {
        return product_describtion;
    }

    public void setProduct_describtion(String product_describtion) {
        this.product_describtion = product_describtion;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(String Product_price) {
        this.Product_price = Product_price;
    }
    public HomeModel() {
    }

    public String getProduct_image() {
        return product_image_url;
    }

    public void setProduct_image(String product_image) {
        this.product_image_url = product_image;
    }


}
