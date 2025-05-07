import java.math.BigDecimal;

public class Product {
    private static int idCount = 1;
    private int id;
    private String name;
    private BigDecimal price;
    private Supplier supplier;

    public Product(String name, BigDecimal price, Supplier supplier){
        this.id = idCount++;
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal newPrice){
        price = newPrice;
    }
}
