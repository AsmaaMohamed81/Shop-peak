package com.alatheer.shop_peak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OnlineModel {

        @SerializedName("succes")
        @Expose
        private Integer succes;

        public Integer getSucces() {
            return succes;
        }

        public void setSucces(Integer succes) {
            this.succes = succes;
        }

    }

