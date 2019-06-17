package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 16/06/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Follow {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("store_id_fk")
    @Expose
    public Object storeIdFk;
    @SerializedName("client_id_fk")
    @Expose
    public Object clientIdFk;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("approved")
    @Expose
    public String approved;
    @SerializedName("client_name")
    @Expose
    public Boolean clientName;

    public Follow withId(String id) {
        this.id = id;
        return this;
    }

    public Follow withStoreIdFk(Object storeIdFk) {
        this.storeIdFk = storeIdFk;
        return this;
    }

    public Follow withClientIdFk(Object clientIdFk) {
        this.clientIdFk = clientIdFk;
        return this;
    }

    public Follow withDate(String date) {
        this.date = date;
        return this;
    }

    public Follow withApproved(String approved) {
        this.approved = approved;
        return this;
    }

    public Follow withClientName(Boolean clientName) {
        this.clientName = clientName;
        return this;
    }

}
