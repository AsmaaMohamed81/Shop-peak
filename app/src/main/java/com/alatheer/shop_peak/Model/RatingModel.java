package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 22/05/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingModel {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("sanf_id_fk")
    @Expose
    public String sanfIdFk;
    @SerializedName("user_web_id_fk")
    @Expose
    public String userWebIdFk;
    @SerializedName("rate_value")
    @Expose
    public String rateValue;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("rate_data_s")
    @Expose
    public String rateDataS;
    @SerializedName("rate_date")
    @Expose
    public String rateDate;

    public RatingModel withId(String id) {
        this.id = id;
        return this;
    }

    public RatingModel withSanfIdFk(String sanfIdFk) {
        this.sanfIdFk = sanfIdFk;
        return this;
    }

    public RatingModel withUserWebIdFk(String userWebIdFk) {
        this.userWebIdFk = userWebIdFk;
        return this;
    }

    public RatingModel withRateValue(String rateValue) {
        this.rateValue = rateValue;
        return this;
    }

    public RatingModel withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public RatingModel withRateDataS(String rateDataS) {
        this.rateDataS = rateDataS;
        return this;
    }

    public RatingModel withRateDate(String rateDate) {
        this.rateDate = rateDate;
        return this;
    }

}