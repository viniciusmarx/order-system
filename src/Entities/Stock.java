package Entities;

import java.math.BigDecimal;

public class Stock {
    private int quantity;
    private BigDecimal price;

    public Stock(int quantity, BigDecimal price) {
        if (quantity < 0) {
            System.out.println("Quantidade preenchida incorretamente");
        } else if (price.compareTo(price) < 0) {
            System.out.println("Preço preenchido incorretamente");
        } else {
            this.quantity = quantity;
            this.price = price;
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            System.out.println("Quantidade preenchida incorretamente");
        } else {
            this.quantity = quantity;
        }
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(price) < 0) {
            System.out.println("Preço preenchido incorretamente");
        } else {
            this.price = price;
        }
    }
}
