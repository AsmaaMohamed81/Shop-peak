package com.alatheer.shop_peak.Model;


import java.lang.reflect.Array;
import java.util.List;

/**
 * Created by M.Hamada on 21/05/2019.
 */

public class Address {
    String governate;
    String[] city;

    public Address(String governate, String[] city) {
        this.governate = governate;
        this.city = city;
    }

    public String getGovernate() {
        return governate;
    }

    public void setGovernate(String governate) {
        this.governate = governate;
    }

    public String[] getCity() {
        return city;
    }

    public void setCity(String[] city) {
        this.city = city;
    }


    @Override
    public String toString() {
        return governate;
    }
}
