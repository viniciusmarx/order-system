package Entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Store {
    private final List<User> users = new ArrayList<User>();
    private final List<Customer> customers = new ArrayList<Customer>();
    private final List<Supplier> suppliers = new ArrayList<Supplier>();
    private final List<Product> products = new ArrayList<Product>();

    public List<User> getUsers() {
        return users;
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

    public void registerSupplier(Supplier supplier) {
        suppliers.add(supplier);
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    public void removeSupplier(Supplier supplier) {
        if (canDeleteSupplier(supplier)) {
            suppliers.remove(supplier);
            System.out.println("Fornecedor removido com sucesso");
        }
    }

    private boolean canDeleteSupplier(Supplier supplier) {
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

    public void registerProduct(Product product) {
        products.add(product);
        System.out.println("Produto cadastrado com sucesso!");
    }

    public Product foundProduct(int productId) {
        for (Product p : products) {
            if (p.getId() == productId) {
                return p;
            }
        }
        throw new IllegalArgumentException("Produto com Id " + productId + " não encontrado");
    }

    public void removeProduct(Product product) {
        products.remove(product);
        System.out.println("Produto removido com sucesso");
    }

    public void listProducts() {
        System.out.println("--- Lista de Produtos ---");
        for (Product product : products) {
            System.out.println("\n" + product);
        }
    }

    public void listSuppliers() {
        System.out.println("--- Lista de Fornecedores ---");
        for (Supplier s : suppliers) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getDescription());
        }
    }

    public void viewAllStock() {
        System.out.println("--- Estoque Geral ---");

        if (products.isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        for (Product p : products) {
            System.out.printf("Id: %d | Nome: %s | Preço: R$%.2f | Quantidade: %d\n",
                    p.getId(), p.getName(), p.getStock().getPrice(), p.getStock().getQuantity());
        }
    }

    public void showProductByName(String search) {
        showByName(search, products, Product::getName, Product::getId, "produto");
    }

    public void showSupplierByName(String search) {
        showByName(search, suppliers, Supplier::getName, Supplier::getId, "fornecedor");
    }

    private <T> void showByName(String search, List<T> list, Function<T, String> nameExtractor, Function<T, Integer> idExtractor, String entityType) {
        boolean found = false;
        for (T item : list) {
            String name = nameExtractor.apply(item);
            if (name != null && name.toLowerCase().contains(search.toLowerCase())) {
                System.out.println("\n" + item);
                found = true;
            }

            String id = idExtractor.apply(item).toString();
            if (id.equals(search)) {
                System.out.println("\n" + item);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println(entityType + " não encontrado");
        }
    }

    public void addUser(User newUser) {
        users.add(newUser);
    }

    public void addCustomer(Customer newCustomer) {
        users.add(newCustomer);
        customers.add(newCustomer);
    }

    public void addDBResources() {

        //Suppliers
        suppliers.add(new Supplier("FF Frutas", "Vendas diretas de frutas", "5399543421", "fffrutas@supp.com",
                new Address("Rua Fantasia", 12323, "", "Centro", "12345678", "São Paulo", "SP")));
        suppliers.add(new Supplier("Carqueja", "Deposito de verduras e insumos para chá", "5399543421", "carq@yahoo.com",
                new Address("Rua ilusão", 456, "Fundos", "Tijuca", "87654321", "Rio de Janeiro", "RJ")));
        suppliers.add(new Supplier("Canoas Yerbas", "Distribuidora de produtos naturais", "5399543421", "yerbcanoas@gmail.com",
                new Address("Rua do sol", 123, "Box 13", "Matias Velho", "12345678", "Canoas", "RS")));
        suppliers.add(new Supplier("Cersul", "Comercio de Cereais", "5399543421", "cersul@gmail.com",
                new Address("Rodovia do sol", 1083, "Pavilhão 1", "Interior", "95185000", "Carlos Barbosa", "RS")));

        //Customers
        addCustomer(new Customer("Joao Pereira", "jpere@mail.com", "123456", "9112225499", "9299321645312543",
                new Address("Cruz de Malta", 1205, "", "Interior", "93411000", "Araranguá", "PR")));

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
        addCustomer(new Customer("User", "user@user.com", "123123", "12345678912", "1234567890123456",
                new Address("Customer", 887, "User", "User", "12345678", "User City", "BR")));

        // users[totalUsers++] = User newUser = new User(name, email, password);
        addUser(new User("Admin", "adm@adm.com", "123123"));
    }
}