package Entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<User> users = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Supplier> suppliers = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

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

    public void addDBResources() {

        //products
        //public Product(String name, String description, Supplier supplier, Stock stock)
        products.add(new Product("Alface", "Alface americana", suppliers.get(0), new Stock(10, new BigDecimal("2.50"))));
        products.add(new Product("Cenoura", "Cenoura orgânica", suppliers.get(1), new Stock(20, new BigDecimal("1.50"))));
        products.add(new Product("Maçã", "Maçã verde", suppliers.get(2), new Stock(15, new BigDecimal("3.00"))));
        products.add(new Product("Laranja", "Laranja Lima", suppliers.get(3), new Stock(25, new BigDecimal("2.00"))));
        products.add(new Product("Batata", "Batata doce", suppliers.get(0), new Stock(30, new BigDecimal("1.20"))));


        //GENERIC USERS

        // users[totalUsers++] = Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
        // customers[totalCustomers++] = Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
//        addCustomer(new Customer("User", "user@user.com", "123123", "12345678912", "1234567890123456",
//                new Address("Customer", 887, "User", "User", "12345678", "User City", "BR")));

        // users[totalUsers++] = User newUser = new User(name, email, password);
//        addUser(new User("Admin", "adm@adm.com", "123123"));
    }
}