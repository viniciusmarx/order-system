package Entities;

public class Product {
    private String name;
    private String description;
    private Supplier supplier;
    private Stock stock;

    public Product(String name, String description, Supplier supplier, Stock stock){
        if (name == null || name.isEmpty()){System.out.println("Nome preenchido incorretamente");}
        else if (description == null || description.isEmpty()){System.out.println("Descrição preenchida incorretamente");}
        else if (supplier == null){System.out.println("Fornecedor não preenchido");}
        else if (stock == null){System.out.println("Estoque não preenchido");}
        else{
            this.name = name;
            this.description = description;
            this.supplier = supplier;
            this.stock = stock;
        }
    }

    public String getName(){ return name; }
    public String getDescription(){ return  description; }
    public Supplier getSupplier(){ return supplier; }
    public Stock getStock(){ return stock; }


    public void setName(String name){
        if(name == null || name.isEmpty()){System.out.println("Nome preenchido incorretamente");} 
        else { this.name = name; }
    }
    public void setDescription(String description){ 
        if (description == null || description.isEmpty()){System.out.println("Descrição preenchida incorretamente");}
        else { this.description = description; }
    }
    public void setSupplier(Supplier supplier){
        if (supplier == null){System.out.println("Fornecedor não preenchido");}
        else { this.supplier = supplier; }
    }
    public void setStock(Stock stock){ 
        if (stock == null){System.out.println("Estoque não preenchido");}
        else { this.stock = stock; }
    }
}
