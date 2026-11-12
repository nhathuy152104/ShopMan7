package model;

import java.sql.Date;

public class Customer7 extends Member7 {
    private static final long serialVersionUID = 1L;
    private String customerCode;
    private Date registerDate;

    public Customer7() { super(); }

    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }
    public Date getRegisterDate() { return registerDate; }
    public void setRegisterDate(Date registerDate) { this.registerDate = registerDate; }
}