public class Product {
    private String name;
    private String description;
    private Supplier supplier;
    private Stock stock;

    public Product(String name, String description, Supplier supplier, Stock stock){
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.stock = stock;
    }

    public String getName(){ return name; }

    public String getDescription(){ return  description; }

    public Supplier getSupplier(){ return supplier; }

    public Stock getStock(){ return stock; }

    public void setName(String name){ this.name = name; }

    public void setDescription(String description){ this.description = description; }

    public void setSupplier(Supplier supplier){ this.supplier = supplier; }

    public void setStock(Stock stock){ this.stock = stock; }
}
