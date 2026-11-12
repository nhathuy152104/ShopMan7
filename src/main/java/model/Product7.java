package model;

import java.io.Serializable;

public class Product7 implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String productCode;
    private String productName;
    private String category;
    private String unit;
    private float price;
    private int stockQuantity;
    private String description;
    private String image;

    public Product7() {}

    public Product7(int id, String productCode, String productName, String category,
                    String unit, float price, int stockQuantity, String description, String image) {
        this.id = id;
        this.productCode = productCode;
        this.productName = productName;
        this.category = category;
        this.unit = unit;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.image = image;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}