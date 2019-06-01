package com.alatheer.shop_peak.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class list_cats implements Serializable  {

    private String id;
    private String name;
    private String type;

    private ArrayList<Subs> subs;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public  ArrayList<Subs> getSubs() {
        return subs;
    }

    public class Subs implements  Serializable {

        private String id;
        private String name;
        private String type;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getType() {
            return type;
        }
    }
}
