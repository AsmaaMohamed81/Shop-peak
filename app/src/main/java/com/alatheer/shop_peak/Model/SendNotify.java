package com.alatheer.shop_peak.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendNotify {

    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("notification")
    @Expose
    private Notification notification;
    @SerializedName("success")
    @Expose
    private Integer success;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

}