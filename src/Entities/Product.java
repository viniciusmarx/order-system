package Entities;

import Utils.ValidationUtils;

public class Product {
    private static int nextId = 1;
    private int id;
    private String name;
    private String description;
    private Supplier supplier;
    private Stock stock;

    public Product() {
        this.id = nextId++;
    }

    public Product(String name, String description, Supplier supplier, Stock stock) {
        this.id = nextId++;
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Stock getStock() {
        return stock;
    }

    public void setName(String name) {
        ValidationUtils.validateRequiredString(name, "Nome");
        this.name = name;
    }

    public void setDescription(String description) {
        ValidationUtils.validateRequiredString(description, "Descrição");
        this.description = description;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return ("ID: " + id +
                "\nNome: " + name +
                "\nDescrição: " + description +
                "\nPreço: " + stock.getPrice() +
                "\nEstoque: " + stock.getQuantity() +
                "\nFornecedor: " + supplier.getName());
    }
}
