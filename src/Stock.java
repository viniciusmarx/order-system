import java.math.BigDecimal;

public class Stock {
    private int quantity;
    private BigDecimal price;

    public Stock(int quantity, BigDecimal price){
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity(){ return quantity; }

    public BigDecimal getPrice(){ return price; }

    public void setQuantity(int quantity){ this.quantity = quantity; }

    public void setPrice(BigDecimal price){ this.price = price; }

}
