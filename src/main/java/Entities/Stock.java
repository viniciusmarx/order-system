package Entities;

import java.math.BigDecimal;

public class Stock {
    private int quantity;
    private BigDecimal price;

    public Stock(int quantity, BigDecimal price) {
        setQuantity(quantity);
        setPrice(price);
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço deve ser maior ou igual a zero");
        }
        this.price = price;
    }

    @Override
    public String toString(){
        return "Quantidade: " + quantity + ", Preço: " + price;
    }
}
