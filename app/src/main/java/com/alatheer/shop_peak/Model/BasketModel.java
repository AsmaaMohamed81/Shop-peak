package com.alatheer.shop_peak.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * Created by M.Hamada on 22/04/2019.
 */
@Entity(tableName = "products")
public class BasketModel  {
    @PrimaryKey
    private int id;
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "num_of_cart")
    String num_of_cart;
    @ColumnInfo(name = "img")
    int img;
    @ColumnInfo(name = "red")
    boolean red_flag;
    @ColumnInfo(name = "blue")
    boolean blue_flag;
    @ColumnInfo(name = "black")
    boolean black_flag;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public boolean isRed_flag() {
        return red_flag;
    }

    public void setRed_flag(boolean red_flag) {
        this.red_flag = red_flag;
    }

    public boolean isBlue_flag() {
        return blue_flag;
    }

    public void setBlue_flag(boolean blue_flag) {
        this.blue_flag = blue_flag;
    }

    public boolean isBlack_flag() {
        return black_flag;
    }

    public void setBlack_flag(boolean black_flag) {
        this.black_flag = black_flag;
    }



    public BasketModel(int id,String title, String num_of_cart, boolean red_flag, boolean blue_flag, boolean black_flag,int img) {
        this.title = title;
        this.num_of_cart = num_of_cart;
        this.red_flag = red_flag;
        this.blue_flag = blue_flag;
        this.black_flag = black_flag;
        this.img=img;
        this.id=id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNum_of_cart() {
        return num_of_cart;
    }

    public void setNum_of_cart(String num_of_cart) {
        this.num_of_cart = num_of_cart;
    }
}
