package Entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<User> users = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Supplier> suppliers = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public boolean isEmailRegistered(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean canDeleteSupplier(Supplier supplier) {
        for (Product p : products) {
            if (p != null && p.getSupplier().equals(supplier)) {
                System.out.println("Fornecedor está vinculado a um ou mais produtos e não pode ser removido");
                return false;
            }
        }
        return true;
    }

    public Supplier foundSupplier(int supplierId) {
        for (Supplier supplier : suppliers) {
            if (supplier.getId() == supplierId) {
                return supplier;
            }
        }
        throw new IllegalArgumentException("Fornecedor com Id " + supplierId + " não encontrado");
    }

    public Product foundProduct(int productId) {
        for (Product p : products) {
            if (p.getId() == productId) {
                return p;
            }
        }
        throw new IllegalArgumentException("Produto com Id " + productId + " não encontrado");
    }
}