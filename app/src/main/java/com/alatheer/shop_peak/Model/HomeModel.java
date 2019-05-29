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
    public String rate;
    @SerializedName("img")
    @Expose
    public String[] img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreIdFk() {
        return storeIdFk;
    }

    public void setStoreIdFk(String storeIdFk) {
        this.storeIdFk = storeIdFk;
    }

    public String getSanfCode() {
        return sanfCode;
    }

    public void setSanfCode(String sanfCode) {
        this.sanfCode = sanfCode;
    }

    public String getSanfName() {
        return sanfName;
    }

    public void setSanfName(String sanfName) {
        this.sanfName = sanfName;
    }

    public String getMainTasnef() {
        return mainTasnef;
    }

    public void setMainTasnef(String mainTasnef) {
        this.mainTasnef = mainTasnef;
    }

    public String getSubTasnef() {
        return subTasnef;
    }

    public void setSubTasnef(String subTasnef) {
        this.subTasnef = subTasnef;
    }

    public String getPriceBeforeDis() {
        return priceBeforeDis;
    }

    public void setPriceBeforeDis(String priceBeforeDis) {
        this.priceBeforeDis = priceBeforeDis;
    }

    public String getPriceAfterDis() {
        return priceAfterDis;
    }

    public void setPriceAfterDis(String priceAfterDis) {
        this.priceAfterDis = priceAfterDis;
    }

    public String getMainImg() {
        return mainImg;
    }

    public void setMainImg(String mainImg) {
        this.mainImg = mainImg;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getOffered() {
        return offered;
    }

    public void setOffered(String offered) {
        this.offered = offered;
    }

    public String getOfferedTitle() {
        return offeredTitle;
    }

    public void setOfferedTitle(String offeredTitle) {
        this.offeredTitle = offeredTitle;
    }

    public String getPriceOffered() {
        return priceOffered;
    }

    public void setPriceOffered(String priceOffered) {
        this.priceOffered = priceOffered;
    }

    public String getDateOfferedFrom() {
        return dateOfferedFrom;
    }

    public void setDateOfferedFrom(String dateOfferedFrom) {
        this.dateOfferedFrom = dateOfferedFrom;
    }

    public String getDateOfferedTo() {
        return dateOfferedTo;
    }

    public void setDateOfferedTo(String dateOfferedTo) {
        this.dateOfferedTo = dateOfferedTo;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateS() {
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = dateS;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getOfferIdFk() {
        return offerIdFk;
    }

    public void setOfferIdFk(String offerIdFk) {
        this.offerIdFk = offerIdFk;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String[] getImg() {
        return img;
    }

    public void setImg(String[] img) {
        this.img = img;
    }
}
