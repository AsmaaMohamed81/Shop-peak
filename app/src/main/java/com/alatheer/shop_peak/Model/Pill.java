package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 17/06/2019.
 */

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class Pill {

    @SerializedName("pill_num")
    @Expose
    public Long pillNum;

    public Long getPillNum() {
        return pillNum;
    }

    public void setPillNum(Long pillNum) {
        this.pillNum = pillNum;
    }


}
