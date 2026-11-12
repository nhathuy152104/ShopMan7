package model;

public class Shipper7 extends Staff7 {
    private static final long serialVersionUID = 1L;
    private String deliveryArea;

    public Shipper7() { super(); }

    public String getDeliveryArea() { return deliveryArea; }
    public void setDeliveryArea(String deliveryArea) { this.deliveryArea = deliveryArea; }
}