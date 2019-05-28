package com.alatheer.shop_peak.Model;

import android.content.Intent;

public class UserModel1 {

    private String full_name;
    private String email;
    private String phone;

    private String madina;
    private String address;
    private String password;

    private Integer agree;

    public UserModel1(String full_name, String email, String phone, String madina, String address, String password, Integer agree) {
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.madina = madina;
        this.address = address;
        this.password = password;
        this.agree = agree;
    }

    public String getFull_name() {
        return full_name;
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
}
