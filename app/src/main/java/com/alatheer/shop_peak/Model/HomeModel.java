package com.alatheer.shop_peak.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

/**
 * Created by M.Hamada on 23/03/2019.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeModel {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("store_id_fk")
    @Expose
    public String storeIdFk;
    @SerializedName("sanf_code")
    @Expose
    public String sanfCode;
    @SerializedName("sanf_name")
    @Expose
    public String sanfName;
    @SerializedName("main_tasnef")
    @Expose
    public String mainTasnef;
    @SerializedName("sub_tasnef")
    @Expose
    public String subTasnef;
    @SerializedName("price_before_dis")
    @Expose
    public String priceBeforeDis;
    @SerializedName("price_after_dis")
    @Expose
    public String priceAfterDis;
    @SerializedName("main_img")
    @Expose
    public String mainImg;
    @SerializedName("details")
    @Expose
    public String details;
    @SerializedName("offered")
    @Expose
    public String offered;
    @SerializedName("offered_title")
    @Expose
    public String offeredTitle;
    @SerializedName("price_offered")
    @Expose
    public String priceOffered;
    @SerializedName("date_offered_from")
    @Expose
    public String dateOfferedFrom;
    @SerializedName("date_offered_to")
    @Expose
    public String dateOfferedTo;
    @SerializedName("publisher")
    @Expose
    public String publisher;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("date_s")
    @Expose
    public String dateS;
    @SerializedName("approved")
    @Expose
    public String approved;
    @SerializedName("offer_id_fk")
    @Expose
    public String offerIdFk;
    @SerializedName("store_name")
    @Expose
    public String storeName;
    @SerializedName("store_img")
    @Expose
    public String storeImg;
    @SerializedName("rate")
    @Expose
    public Long rate;
    @SerializedName("img")
    @Expose
    public Img img;

    public HomeModel withId(String id) {
        this.id = id;
        return this;
    }

    public HomeModel withStoreIdFk(String storeIdFk) {
        this.storeIdFk = storeIdFk;
        return this;
    }

    public HomeModel withSanfCode(String sanfCode) {
        this.sanfCode = sanfCode;
        return this;
    }

    public HomeModel withSanfName(String sanfName) {
        this.sanfName = sanfName;
        return this;
    }

    public HomeModel withMainTasnef(String mainTasnef) {
        this.mainTasnef = mainTasnef;
        return this;
    }

    public HomeModel withSubTasnef(String subTasnef) {
        this.subTasnef = subTasnef;
        return this;
    }

    public HomeModel withPriceBeforeDis(String priceBeforeDis) {
        this.priceBeforeDis = priceBeforeDis;
        return this;
    }

    public HomeModel withPriceAfterDis(String priceAfterDis) {
        this.priceAfterDis = priceAfterDis;
        return this;
    }

    public HomeModel withMainImg(String mainImg) {
        this.mainImg = mainImg;
        return this;
    }

    public HomeModel withDetails(String details) {
        this.details = details;
        return this;
    }

    public HomeModel withOffered(String offered) {
        this.offered = offered;
        return this;
    }

    public HomeModel withOfferedTitle(String offeredTitle) {
        this.offeredTitle = offeredTitle;
        return this;
    }

    public HomeModel withPriceOffered(String priceOffered) {
        this.priceOffered = priceOffered;
        return this;
    }

    public HomeModel withDateOfferedFrom(String dateOfferedFrom) {
        this.dateOfferedFrom = dateOfferedFrom;
        return this;
    }

    public HomeModel withDateOfferedTo(String dateOfferedTo) {
        this.dateOfferedTo = dateOfferedTo;
        return this;
    }

    public HomeModel withPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public HomeModel withDate(String date) {
        this.date = date;
        return this;
    }

    public HomeModel withDateS(String dateS) {
        this.dateS = dateS;
        return this;
    }

    public HomeModel withApproved(String approved) {
        this.approved = approved;
        return this;
    }

    public HomeModel withOfferIdFk(String offerIdFk) {
        this.offerIdFk = offerIdFk;
        return this;
    }

    public HomeModel withStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public HomeModel withStoreImg(String storeImg) {
        this.storeImg = storeImg;
        return this;
    }

    public HomeModel withRate(Long rate) {
        this.rate = rate;
        return this;
    }

    public HomeModel withImg(Img img) {
        this.img = img;
        return this;
    }

}
