package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 06/05/2019.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationModel {

    @SerializedName("like")
    @Expose
    public List<Like> like = null;
    @SerializedName("follows")
    @Expose
    public List<Follow> follows = null;

    public NotificationModel withLike(List<Like> like) {
        this.like = like;
        return this;
    }

    public NotificationModel withFollows(List<Follow> follows) {
        this.follows = follows;
        return this;
    }

}