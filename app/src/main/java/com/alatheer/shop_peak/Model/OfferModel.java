package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 21/03/2019.
 */

public class OfferModel {
    String image1;
    String image2;
    String product_title;
    String product_describtion;
    String size;
    String product_price;
    String gender;
    String vender_name;
    int vender_image;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
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

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public OfferModel(String image1, String image2, String product_title, String product_describtion, String size, String product_price, String gender, String vender_name, int vender_image) {
        this.image1 = image1;
        this.image2 = image2;
        this.product_title = product_title;
        this.product_describtion = product_describtion;
        this.size = size;
        this.product_price = product_price;
        this.gender = gender;
        this.vender_name = vender_name;
        this.vender_image = vender_image;

    }
}
