package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 22/05/2019.
 */

public class RatingModel {
    String username;
    int num_of_stars;

    public RatingModel(String username, int num_of_stars) {
        this.username = username;
        this.num_of_stars = num_of_stars;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getNum_of_stars() {
        return num_of_stars;
    }

    public void setNum_of_stars(int num_of_stars) {
        this.num_of_stars = num_of_stars;
    }
}
