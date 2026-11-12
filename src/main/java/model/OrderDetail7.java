package model;

import java.io.Serializable;

public class OrderDetail7 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private int quantity;
    private float unitPrice;
    private float subTotal;
    private Product7 product;

    public OrderDetail7() {}

    public OrderDetail7(int id, int quantity, float unitPrice, Product7 product) {
        this.id = id;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.product = product;
        this.subTotal = quantity * unitPrice;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public float getUnitPrice() { return unitPrice; }
    public void setUnitPrice(float unitPrice) { this.unitPrice = unitPrice; }
    public float getSubTotal() { return quantity * unitPrice; }
    public void setSubTotal(float subTotal) { this.subTotal = subTotal; }
    public Product7 getProduct() { return product; }
    public void setProduct(Product7 product) { this.product = product; }
}