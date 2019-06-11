package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 10/06/2019.
 */

public class Product_Specification {
    String name;
    String value;

    public Product_Specification(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
