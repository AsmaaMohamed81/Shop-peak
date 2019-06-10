package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 30/05/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RatingModel2 {

    @SerializedName("success")
    @Expose
    public Long success;

    public Long getSuccess() {
        return success;
    }

    public void setSuccess(Long success) {
        this.success = success;
    }
}
