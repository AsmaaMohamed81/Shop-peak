package com.alatheer.shop_peak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderNotify {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sound")
    @Expose
    private String sound;
    @SerializedName("success")
    @Expose
    private Integer success;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}
