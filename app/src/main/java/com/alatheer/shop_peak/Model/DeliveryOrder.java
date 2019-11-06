package com.alatheer.shop_peak.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeliveryOrder {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("order_id_fk")
    @Expose
    private String orderIdFk;
    @SerializedName("delivery_id_fk")
    @Expose
    private String deliveryIdFk;
    @SerializedName("accept_refuse")
    @Expose
    private String acceptRefuse;
    @SerializedName("order")
    @Expose
    private Order order;
    @SerializedName("asnaf")
    @Expose
    private List<Asnaf> asnaf = null;
    @SerializedName("pill_num")
    @Expose
    private String pillNum;

    public String getPillNum() {
        return pillNum;
    }

    public void setPillNum(String pillNum) {
        this.pillNum = pillNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderIdFk() {
        return orderIdFk;
    }

    public void setOrderIdFk(String orderIdFk) {
        this.orderIdFk = orderIdFk;
    }

    public String getDeliveryIdFk() {
        return deliveryIdFk;
    }

    public void setDeliveryIdFk(String deliveryIdFk) {
        this.deliveryIdFk = deliveryIdFk;
    }

    public String getAcceptRefuse() {
        return acceptRefuse;
    }

    public void setAcceptRefuse(String acceptRefuse) {
        this.acceptRefuse = acceptRefuse;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Asnaf> getAsnaf() {
        return asnaf;
    }

    public void setAsnaf(List<Asnaf> asnaf) {
        this.asnaf = asnaf;
    }

}