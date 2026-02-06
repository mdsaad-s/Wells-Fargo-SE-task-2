package com.wellsfargo.counselor.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "security")
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "security_id")
    private Long securityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(name = "security_name", nullable = false, length = 100)
    private String securityName;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Column(name = "purchase_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal purchasePrice;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "current_value", precision = 15, scale = 2)
    private BigDecimal currentValue;

    // Default constructor
    public Security() {
    }

    // Constructor with all fields except ID and relationships
    public Security(String securityName, String category, LocalDate purchaseDate, 
                    BigDecimal purchasePrice, Integer quantity, BigDecimal currentValue) {
        this.securityName = securityName;
        this.category = category;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.quantity = quantity;
        this.currentValue = currentValue;
    }

    // Getters and Setters
    public Long getSecurityId() {
        return securityId;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getSecurityName() {
        return securityName;
    }

    public void setSecurityName(String securityName) {
        this.securityName = securityName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(BigDecimal currentValue) {
        this.currentValue = currentValue;
    }

    // Helper method to calculate gain/loss
    public BigDecimal getGainLoss() {
        if (currentValue != null && purchasePrice != null && quantity != null) {
            BigDecimal totalPurchaseValue = purchasePrice.multiply(BigDecimal.valueOf(quantity));
            return currentValue.subtract(totalPurchaseValue);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public String toString() {
        return "Security{" +
                "securityId=" + securityId +
                ", securityName='" + securityName + '\'' +
                ", category='" + category + '\'' +
                ", purchaseDate=" + purchaseDate +
                ", purchasePrice=" + purchasePrice +
                ", quantity=" + quantity +
                ", currentValue=" + currentValue +
                '}';
    }
}