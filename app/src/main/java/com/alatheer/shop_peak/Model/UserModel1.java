package com.alatheer.shop_peak.Model;

import android.content.Intent;

import java.io.Serializable;

public class UserModel1 implements Serializable {

    private String id;
    private String full_name;
    private String email;
    private String phone;

    private String madina;
    private String mohafza;
    private String govern;
    private String city;


    private String address;
    private String password;

    private Integer agree;
    private Integer success;

    private String type;
    private String active;
    private String logo_img;
    private String send_order;

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }



    public String getSend_order() {
        return send_order;
    }

    public void setSend_order(String send_order) {
        this.send_order = send_order;
    }

    public String getLogo_img() {
        return logo_img;
    }

    public void setLogo_img(String logo_img) {
        this.logo_img = logo_img;
    }

    public String getFull_name() {
        return full_name;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMadina() {
        return madina;
    }

    public void setMadina(String madina) {
        this.madina = madina;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMohafza() {
        return mohafza;
    }

    public void setMohafza(String mohafza) {
        this.mohafza = mohafza;
    }

    public String getGovern() {
        return govern;
    }

    public void setGovern(String govern) {
        this.govern = govern;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
