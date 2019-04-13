package com.alatheer.shop_peak.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by M.Hamada on 23/03/2019.
 */

public class HomeModel implements Serializable {
    public HomeModel() {
    }

    public int[] getProduct_image() {
        return product_image;
    }

    public void setProduct_image(int[] product_image) {
        this.product_image = product_image;
    }

    int[] product_image;
    String product_title;
    String product_describtion;
    String size;
    String gender;
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public HomeModel(int[] product_image, String product_title, String product_describtion, String Product_price, String size
    ,String gender) {
        this.product_image = product_image;
        this.product_title = product_title;
        this.product_describtion = product_describtion;
        this.Product_price = Product_price;
        this.size=size;
        this.gender=gender;

    }
    String Product_price;
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
}
