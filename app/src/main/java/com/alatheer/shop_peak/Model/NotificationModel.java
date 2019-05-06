package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 06/05/2019.
 */

public class NotificationModel {
    String title ;
    String details;

    public NotificationModel(String title, String details) {
        this.title = title;
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
