package com.alatheer.shop_peak.Model;

import android.graphics.Bitmap;

/**
 * Created by M.Hamada on 25/03/2019.
 */

public class ProfileModel {
    int Image;

    public ProfileModel(int image) {
        Image = image;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
