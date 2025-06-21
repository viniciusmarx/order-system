package Entities;

import java.math.BigDecimal;

public class OrderItem {
    private String productName;
    private BigDecimal unitPrice;
    private int quantity;

    public OrderItem(Product product, int quantity){
        this.productName = product.getName();
        this.unitPrice = product.getStock().getPrice();
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice(){
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    public String getProductName(){
        return productName;
    }

    public BigDecimal getUnitPrice(){
        return unitPrice;
    }

    public int getQuantity(){
        return quantity;
    }
}
