package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 02/06/2019.
 */



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "morders")
public class OrderItemList implements Serializable  {
    @ColumnInfo(name = "sanf_id_fk")
    @SerializedName("sanf_id_fk")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @NonNull
    public String sanfIdFk;


    @ColumnInfo(name = "store_id_fk")
    @SerializedName("store_id_fk")
    @Expose
    public String storeIdFk;

    @ColumnInfo(name = "sanf_amount")
    @SerializedName("sanf_amount")
    @Expose
    public String sanfAmount;

    @ColumnInfo(name = "sanf_price")
    @SerializedName("sanf_price")
    @Expose
    public String sanfPrice;

    public String getSanfImage() {
        return sanfImage;
    }

    public void setSanfImage(String sanfImage) {
        this.sanfImage = sanfImage;
    }

    @ColumnInfo(name = "sanf_image")
    @SerializedName("sanf_image")
    @Expose
    public String sanfImage;

    public String getSanfTitle() {
        return sanfTitle;
    }

    public void setSanfTitle(String sanfTitle) {
        this.sanfTitle = sanfTitle;
    }


    @ColumnInfo(name = "sanf_title")
    @SerializedName("sanf_title")

    @Expose
    public String sanfTitle;

    public OrderItemList() {
    }

    public OrderItemList(String storeIdFk, String sanfIdFk, String sanfAmount, String sanfPrice) {
        this.storeIdFk = storeIdFk;
        this.sanfIdFk = sanfIdFk;
        this.sanfAmount = sanfAmount;
        this.sanfPrice = sanfPrice;

    }



        public OrderItemList withStoreIdFk(String storeIdFk) {
            this.storeIdFk = storeIdFk;
            return this;
        }

        public OrderItemList withSanfIdFk(String sanfIdFk) {
            this.sanfIdFk = sanfIdFk;
            return this;
        }

        public OrderItemList withSanfAmount(String sanfAmount) {
            this.sanfAmount = sanfAmount;
            return this;
        }

        public OrderItemList withSanfPrice(String sanfPrice) {
            this.sanfPrice = sanfPrice;
            return this;
        }

    }

