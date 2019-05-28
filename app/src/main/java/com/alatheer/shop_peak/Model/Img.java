package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 28/05/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Img {

    @SerializedName("0")
    @Expose
    public String _0;
    @SerializedName("1")
    @Expose
    public String _1;
    @SerializedName("2")
    @Expose
    public String _2;
    @SerializedName("3")
    @Expose
    public String _3;

    public Img with0(String _0) {
        this._0 = _0;
        return this;
    }

    public Img with1(String _1) {
        this._1 = _1;
        return this;
    }

    public Img with2(String _2) {
        this._2 = _2;
        return this;
    }

    public Img with3(String _3) {
        this._3 = _3;
        return this;
    }

}
