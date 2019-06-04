package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 02/06/2019.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasketModel2 {

    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("client_name")
    @Expose
    public String clientName;
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
    @SerializedName("OrderItemList")
    @Expose
    public List<OrderItemList> orderItemList = null;

    public BasketModel2 withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BasketModel2 withClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public BasketModel2 withAddress(String address) {
        this.address = address;
        return this;
    }

    public BasketModel2 withUserLat(String userLat) {
        this.userLat = userLat;
        return this;
    }

    public BasketModel2 withUserLang(String userLang) {
        this.userLang = userLang;
        return this;
    }

    public BasketModel2 withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public BasketModel2 withOrderItemList(List<OrderItemList> orderItemList) {
        this.orderItemList = orderItemList;
        return this;
    }

}

