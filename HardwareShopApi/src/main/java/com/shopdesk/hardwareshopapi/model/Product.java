package com.shopdesk.hardwareshopapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotBlank(message = "Category is required")
    @Column(name = "category", nullable = false)
    private String category;

    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Max(value = 5000, message = "Quantity cannot be more than 5000")
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @NotNull(message = "Unit price is required")
    @Min(value = 1, message = "Unit price must be at least ₹1")
    @Max(value = 100000, message = "Unit price cannot be more than ₹100000")
    @Column(name = "unit_price", nullable = false)
    private Integer unitPrice;   // in ₹

    public Product() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
}
