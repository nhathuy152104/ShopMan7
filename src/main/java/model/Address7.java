package model;

import java.io.Serializable;

public class Address7 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String houseName;
    private String building;
    private String street;
    private String ward;
    private String district;
    private String city;

    public Address7() {}

    public Address7(int id, String houseName, String building, String street, String ward, String district, String city) {
        this.id = id;
        this.houseName = houseName;
        this.building = building;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getHouseName() { return houseName; }
    public void setHouseName(String houseName) { this.houseName = houseName; }
    public String getBuilding() { return building; }
    public void setBuilding(String building) { this.building = building; }
    public String getStreet() { return street; }
    public void setStreet(String street) { this.street = street; }
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getFullAddress() {
        return houseName + " " + street + ", " + district + ", " + city;
    }
}