package com.alatheer.shop_peak.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


    public class Asnaf {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("store_id_fk")
        @Expose
        private String storeIdFk;
        @SerializedName("sanf_code")
        @Expose
        private String sanfCode;
        @SerializedName("sanf_name")
        @Expose
        private String sanfName;
        @SerializedName("main_tasnef")
        @Expose
        private String mainTasnef;
        @SerializedName("sub_tasnef")
        @Expose
        private String subTasnef;
        @SerializedName("price_before_dis")
        @Expose
        private String priceBeforeDis;
        @SerializedName("price_after_dis")
        @Expose
        private String priceAfterDis;
        @SerializedName("main_img")
        @Expose
        private String mainImg;
        @SerializedName("details")
        @Expose
        private String details;
        @SerializedName("offered")
        @Expose
        private String offered;
        @SerializedName("offered_title")
        @Expose
        private String offeredTitle;
        @SerializedName("price_offered")
        @Expose
        private String priceOffered;
        @SerializedName("date_offered_from")
        @Expose
        private String dateOfferedFrom;
        @SerializedName("date_offered_to")
        @Expose
        private String dateOfferedTo;
        @SerializedName("status_store")
        @Expose
        private String statusStore;
        @SerializedName("publisher")
        @Expose
        private String publisher;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("date_s")
        @Expose
        private String dateS;
        @SerializedName("approved")
        @Expose
        private String approved;
        @SerializedName("offer_id_fk")
        @Expose
        private String offerIdFk;
        @SerializedName("store_name")
        @Expose
        private String storeName;
        @SerializedName("store_adress")
        @Expose
        private String storeAdress;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("amount")
        @Expose
        private String amount;
        @SerializedName("price")
        @Expose
        private Integer price;

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

        public String getStatusStore() {
            return statusStore;
        }

        public void setStatusStore(String statusStore) {
            this.statusStore = statusStore;
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

        public String getStoreAdress() {
            return storeAdress;
        }

        public void setStoreAdress(String storeAdress) {
            this.storeAdress = storeAdress;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }


}
