public class Product {
    private static int idCount = 1;
    private int id;
    private String name;
    private double price;
    private Supplier supplier;

    public Product(String name, double price, Supplier supplier){
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

    public double getPrice(){
        return price;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }
}
