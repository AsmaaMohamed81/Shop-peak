package com.alatheer.shop_peak.Model;

    import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Order {
        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("delivery_id_fk")
        @Expose
        private String deliveryIdFk;
        @SerializedName("clint_id_fk")
        @Expose
        private String clintIdFk;
        @SerializedName("store_id_fk")
        @Expose
        private String storeIdFk;
        @SerializedName("accepted")
        @Expose
        private String accepted;
        @SerializedName("date")
        @Expose
        private String date;
        @SerializedName("time")
        @Expose
        private String time;
        @SerializedName("rate")
        @Expose
        private String rate;
        @SerializedName("adress")
        @Expose
        private String adress;
        @SerializedName("client_name")
        @Expose
        private String clientName;
        @SerializedName("store_name")
        @Expose
        private String storeName;
        @SerializedName("store_adress")
        @Expose
        private String storeAdress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDeliveryIdFk() {
            return deliveryIdFk;
        }

        public void setDeliveryIdFk(String deliveryIdFk) {
            this.deliveryIdFk = deliveryIdFk;
        }

        public String getClintIdFk() {
            return clintIdFk;
        }

        public void setClintIdFk(String clintIdFk) {
            this.clintIdFk = clintIdFk;
        }

        public String getStoreIdFk() {
            return storeIdFk;
        }

        public void setStoreIdFk(String storeIdFk) {
            this.storeIdFk = storeIdFk;
        }

        public String getAccepted() {
            return accepted;
        }

        public void setAccepted(String accepted) {
            this.accepted = accepted;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getAdress() {
            return adress;
        }

        public void setAdress(String adress) {
            this.adress = adress;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
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
    }

