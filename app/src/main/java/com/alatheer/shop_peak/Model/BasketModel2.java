package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 02/06/2019.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasketModel2 {

    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("orderItemList")
    @Expose
    private List<OrderItemList> orderItemList = null;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("user_lat")
    @Expose
    private String userLat;
    @SerializedName("user_lang")
    @Expose
    private String userLang;
    @SerializedName("phone")
    @Expose
    private String phone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItemList> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemList> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BasketModel2(String type, List<OrderItemList> orderItemList, String userId, String clientName, String address, String userLat, String userLang, String phone) {
        this.type = type;
        this.orderItemList = orderItemList;
        this.userId = userId;
        this.clientName = clientName;
        this.address = address;
        this.userLat = userLat;
        this.userLang = userLang;
        this.phone = phone;
    }
}

