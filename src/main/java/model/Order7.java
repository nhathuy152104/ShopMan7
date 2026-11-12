package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order7 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String orderCode;
    private Date orderDate;
    private Date exportDate;
    private String status;
    private float totalAmount;
    private String note;
    private Customer7 customer;
    private SalesStaff7 salesStaff;
    private Shipper7 shipper;
    private List<OrderDetail7> orderDetails = new ArrayList<>();

    public Order7() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }
    public Date getOrderDate() { return orderDate; }
    public void setOrderDate(Date orderDate) { this.orderDate = orderDate; }
    public Date getExportDate() { return exportDate; }
    public void setExportDate(Date exportDate) { this.exportDate = exportDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
    public Customer7 getCustomer() { return customer; }
    public void setCustomer(Customer7 customer) { this.customer = customer; }
    public SalesStaff7 getSalesStaff() { return salesStaff; }
    public void setSalesStaff(SalesStaff7 salesStaff) { this.salesStaff = salesStaff; }
    public Shipper7 getShipper() { return shipper; }
    public void setShipper(Shipper7 shipper) { this.shipper = shipper; }
    public List<OrderDetail7> getOrderDetails() { return orderDetails; }
    public void setOrderDetails(List<OrderDetail7> orderDetails) { this.orderDetails = orderDetails; }
}