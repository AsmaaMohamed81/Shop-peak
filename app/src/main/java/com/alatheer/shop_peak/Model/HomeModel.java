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
    List<Integer>  image_resources;
    String product_title;
    String product_describtion;
    String size;
    String Product_price;
    String gender;
    String vender_name;
    int    vender_image;
    public HomeModel(List<Integer> image_resources, String product_title, String product_describtion, String size, String gender, String product_price, String vender_name, int vender_image) {
        this.image_resources = image_resources;
        this.product_title = product_title;
        this.product_describtion = product_describtion;
        this.size = size;
        this.gender = gender;
        this.Product_price = product_price;
        this.vender_name=vender_name;
        this.vender_image=vender_image;
    }
    public List<Integer> getImage_resources() {
        return image_resources;
    }
    public void setImage_resources(List<Integer> image_resources) {
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
    public String getVender_name() {
        return vender_name;
    }

    public void setVender_name(String vender_name) {
        this.vender_name = vender_name;
    }

    public int getVender_image() {
        return vender_image;
    }

    public void setVender_image(int vender_image) {
        this.vender_image = vender_image;
    }
}
