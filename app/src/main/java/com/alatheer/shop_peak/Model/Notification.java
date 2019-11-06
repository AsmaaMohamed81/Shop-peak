package com.alatheer.shop_peak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("sound")
    @Expose
    private String sound;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("click_action")
    @Expose
    private String clickAction;

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

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClickAction() {
        return clickAction;
    }

    public void setClickAction(String clickAction) {
        this.clickAction = clickAction;
    }

}
