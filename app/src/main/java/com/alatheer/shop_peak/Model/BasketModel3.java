package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 03/06/2019.
 */


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasketModel3 {

        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("user_lat")
        @Expose
        public String userLat;
        @SerializedName("user_lang")
        @Expose
        public String userLang;
        @SerializedName("phone")
        @Expose
        public String phone;
        @SerializedName("client_name")
        @Expose
        public String clientName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserLat() {
        return userLat;
    }

    public void setUserLat(String userLat) {
        this.userLat = userLat;
    }

    public String getUserLang() {
        return userLang;
    }

    public void setUserLang(String userLang) {
        this.userLang = userLang;
    }

    public BasketModel3(String userId, String address, String userLat, String userLang, String phone, String clientName) {
        this.userId = userId;
        this.address = address;
        this.userLat = userLat;
        this.userLang = userLang;
        this.phone = phone;
        this.clientName = clientName;
    }

    public String getPhone() {

        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public BasketModel3 withUserId(String userId) {
            this.userId = userId;
            return this;

        }

        public BasketModel3 withAddress(String address) {
            this.address = address;
            return this;
        }

        public BasketModel3 withUserLat(String userLat) {
            this.userLat = userLat;
            return this;
        }

        public BasketModel3 withUserLang(String userLang) {
            this.userLang = userLang;
            return this;
        }

        public BasketModel3 withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public BasketModel3 withClientName(String clientName) {
            this.clientName = clientName;
            return this;
        }

    }