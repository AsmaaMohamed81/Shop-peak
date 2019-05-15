package com.alatheer.shop_peak.Model;

public class UserModel {

private String name;
private String image_url;
private String email;

    public UserModel(String name, String image_url, String email) {
        this.name = name;
        this.image_url = image_url;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
