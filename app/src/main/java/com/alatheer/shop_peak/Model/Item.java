package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 29/05/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("store_id_fk")
        @Expose
        public String storeIdFk;
        @SerializedName("sanf_id_fk")
        @Expose
        public String sanfIdFk;
        @SerializedName("sanf_code")
        @Expose
        public String sanfCode;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("details")
        @Expose
        public String details;

        public Item withId(String id) {
            this.id = id;
            return this;
        }

        public Item withStoreIdFk(String storeIdFk) {
            this.storeIdFk = storeIdFk;
            return this;
        }

        public Item withSanfIdFk(String sanfIdFk) {
            this.sanfIdFk = sanfIdFk;
            return this;
        }

        public Item withSanfCode(String sanfCode) {
            this.sanfCode = sanfCode;
            return this;
        }

        public Item withTitle(String title) {
            this.title = title;
            return this;
        }

        public Item withDetails(String details) {
            this.details = details;
            return this;
        }

    }
