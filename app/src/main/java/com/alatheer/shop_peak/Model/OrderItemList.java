package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 02/06/2019.
 */


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
@Entity(tableName = "orders")
public class OrderItemList  {
        @PrimaryKey(autoGenerate = true)
        private int id;

    public void setId(int id) {
        this.id = id;
    }

    public OrderItemList() {
    }

    public int getId() {
        return id;
    }

    @ColumnInfo(name = "store_id_fk")
        @SerializedName("store_id_fk")
        @Expose

        public String storeIdFk;
        @ColumnInfo(name = "sanf_id_fk")
        @SerializedName("sanf_id_fk")
        @Expose
        public String sanfIdFk;

    public OrderItemList(String storeIdFk, String sanfIdFk, String sanfAmount, String sanfPrice) {
        this.storeIdFk = storeIdFk;
        this.sanfIdFk = sanfIdFk;
        this.sanfAmount = sanfAmount;
        this.sanfPrice = sanfPrice;
    }

    @ColumnInfo(name = "sanf_amount")
        @SerializedName("sanf_amount")
        @Expose

        public String sanfAmount;
        @ColumnInfo(name = "sanf_price")
        @SerializedName("sanf_price")
        @Expose
        public String sanfPrice;

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

