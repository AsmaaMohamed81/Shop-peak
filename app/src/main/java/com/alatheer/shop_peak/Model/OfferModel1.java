package com.alatheer.shop_peak.Model;

/**
 * Created by M.Hamada on 29/05/2019.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferModel1 {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("store_id_fk")
    @Expose
    public String storeIdFk;
    @SerializedName("offer_title")
    @Expose
    public String offerTitle;
    @SerializedName("date_offer_from")
    @Expose
    public String dateOfferFrom;
    @SerializedName("date_offer_to")
    @Expose
    public String dateOfferTo;
    @SerializedName("offer_asnaf")
    @Expose
    public String offerAsnaf;
    @SerializedName("img_offer")
    @Expose
    public String imgOffer;
    @SerializedName("approved")
    @Expose
    public String approved;
    @SerializedName("date_added_offer")
    @Expose
    public String dateAddedOffer;

    public OfferModel1 withId(String id) {
        this.id = id;
        return this;
    }

    public OfferModel1 withStoreIdFk(String storeIdFk) {
        this.storeIdFk = storeIdFk;
        return this;
    }

    public OfferModel1 withOfferTitle(String offerTitle) {
        this.offerTitle = offerTitle;
        return this;
    }

    public OfferModel1 withDateOfferFrom(String dateOfferFrom) {
        this.dateOfferFrom = dateOfferFrom;
        return this;
    }

    public OfferModel1 withDateOfferTo(String dateOfferTo) {
        this.dateOfferTo = dateOfferTo;
        return this;
    }

    public OfferModel1 withOfferAsnaf(String offerAsnaf) {
        this.offerAsnaf = offerAsnaf;
        return this;
    }

    public OfferModel1 withImgOffer(String imgOffer) {
        this.imgOffer = imgOffer;
        return this;
    }

    public OfferModel1 withApproved(String approved) {
        this.approved = approved;
        return this;
    }

    public OfferModel1 withDateAddedOffer(String dateAddedOffer) {
        this.dateAddedOffer = dateAddedOffer;
        return this;
    }

}
