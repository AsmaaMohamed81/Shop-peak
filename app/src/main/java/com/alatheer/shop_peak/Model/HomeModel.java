package com.alatheer.shop_peak.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by M.Hamada on 23/03/2019.
 */

public class HomeModel implements Serializable{
    int[]    image_resources;
    String product_title;
    String product_describtion;
    String size;
    String gender;

    public HomeModel(int[] image_resources, String product_title, String product_describtion, String size, String gender, String product_price) {
        this.image_resources = image_resources;
        this.product_title = product_title;
        this.product_describtion = product_describtion;
        this.size = size;
        this.gender = gender;
        Product_price = product_price;
    }

    String Product_price;

    public int[] getImage_resources() {
        return image_resources;
    }

    public void setImage_resources(int[] image_resources) {
        this.image_resources = image_resources;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
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

    public void setProduct_price(String product_price) {
        Product_price = product_price;
    }
}
