package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 16/06/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Like {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("client_id_fk")
    @Expose
    public String clientIdFk;
    @SerializedName("store_id_fk")
    @Expose
    public Object storeIdFk;
    @SerializedName("sanf_id_fk")
    @Expose
    public String sanfIdFk;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("approved")
    @Expose
    public String approved;
    @SerializedName("client_name")
    @Expose
    public String clientName;
    @SerializedName("sanf_name")
    @Expose
    public String sanfName;

    public Like withId(String id) {
        this.id = id;
        return this;
    }

    public Like withClientIdFk(String clientIdFk) {
        this.clientIdFk = clientIdFk;
        return this;
    }

    public Like withStoreIdFk(Object storeIdFk) {
        this.storeIdFk = storeIdFk;
        return this;
    }

    public Like withSanfIdFk(String sanfIdFk) {
        this.sanfIdFk = sanfIdFk;
        return this;
    }

    public Like withDate(String date) {
        this.date = date;
        return this;
    }

    public Like withApproved(String approved) {
        this.approved = approved;
        return this;
    }

    public Like withClientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

    public Like withSanfName(String sanfName) {
        this.sanfName = sanfName;
        return this;
    }

}
