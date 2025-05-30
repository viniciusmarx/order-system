package Entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Store {
    private final List<User> users = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<Supplier> suppliers = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();

    public void createNewAccount(Scanner sc) {
        try {
            System.out.println("--- Cadastro de Novo Usuário ---");
            System.out.print("Nome: ");
            String name = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            if (isEmailRegistered(email)) {
                return;
            }
            System.out.print("Senha: ");
            String password = sc.nextLine();

            System.out.println("- Tipo de conta - ");
            System.out.println("1 - Admin");
            System.out.println("2 - Cliente");
            System.out.print("Escolha: ");
            int role = Integer.parseInt(sc.nextLine());

            if (role == 2) {
                System.out.print("Telefone: ");
                String phoneNumber = sc.nextLine();
                System.out.print("Cartão de crédito: ");
                String creditCard = sc.nextLine();

                Address address = createAddress(sc);
                addCustomer(new Customer(name, email, password, phoneNumber, creditCard, address));
            } else {
                addUser(new User(name, email, password));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public User login(Scanner sc) {
        System.out.println("--- LOGIN ---");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String password = sc.nextLine();

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("Bem-vindo(a), " + u.getName());
                return u;
            }
        }
        System.out.println("Email ou senha inválidos");
        return null;
    }

    private boolean isEmailRegistered(String email) {
        for (User u : users) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email já cadastrado");
                return true;
            }
        }
        return false;
    }

    public void registerSupplier(Scanner sc) {
        try {
            Supplier supplier = new Supplier();
            System.out.println("--- Cadastro de fornecedor ---");
            System.out.print("Nome: ");
            supplier.setName(sc.nextLine());

            System.out.print("Descrição: ");
            supplier.setDescription(sc.nextLine());

            System.out.print("Telefone: ");
            supplier.setPhoneNumber(sc.nextLine());

            System.out.print("Email: ");
            supplier.setEmail(sc.nextLine());

            supplier.setAddress(createAddress(sc));

            suppliers.add(supplier);
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Supplier foundSupplier(Scanner sc, String operation) {
        System.out.print("Digite o código do fornecedor a ser " + operation + ": ");
        int index = Integer.parseInt(sc.nextLine());

        if (index <= 0 || index > suppliers.size()) {
            throw new IllegalArgumentException("Fornecedor inválido");
        }
        return suppliers.get(index);
    }

    public Product foundProduct(Scanner sc, String operation) {
        System.out.print("Digite o código do produto a ser " + operation + ": ");
        int id = Integer.parseInt(sc.nextLine());

        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void updateSupplier(Scanner sc) {
        try {
            listSuppliers();
            Supplier supplier = foundSupplier(sc, "alterado");
            int option;
            do {
                System.out.println("\n--- Atualizar Fornecedor");
                System.out.println("1. Nome");
                System.out.println("2. Descrição");
                System.out.println("3. Telefone");
                System.out.println("4. Email");
                System.out.println("5. Endereço");
                System.out.println("0. Voltar");
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        System.out.print("Novo nome: ");
                        supplier.setName(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nova descrição: ");
                        supplier.setDescription(sc.nextLine());
                    }
                    case 3 -> {
                        System.out.print("Novo telefone: ");
                        supplier.setPhoneNumber(sc.nextLine());
                    }
                    case 4 -> {
                        System.out.print("Novo email: ");
                        supplier.setEmail(sc.nextLine());
                    }
                    case 5 -> {
                        System.out.println("Novo endereço: ");
                        supplier.setAddress(createAddress(sc));
                    }
                }
            } while (true);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
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

    public void removeSupplier(Supplier supplier) {
        suppliers.remove(supplier);
    }

    public void registerProduct(Scanner sc) {
        try {
            if (suppliers.isEmpty()) {
                System.out.println("Nenhum fornecedor cadastrado. Cadastre um fornecedor primeiro");
                return;
            }
            Product product = new Product();
            System.out.println("--- Cadastro de Produto ---");
            System.out.print("Nome: ");
            product.setName(sc.nextLine());

            System.out.print("Descrição: ");
            product.setDescription(sc.nextLine());

            listSuppliers();
            System.out.print("Escolha um fornecedor: ");
            int index = Integer.parseInt(sc.nextLine()) - 1;
            if (index < 0 || index >= suppliers.size()) {
                throw new IllegalArgumentException("Fornecedor inválido");
            }
            product.setSupplier(suppliers.get(index));

            System.out.print("Quantidade em estoque: ");
            int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Preço unitário: ");
            BigDecimal price = new BigDecimal(sc.nextLine());
            product.setStock(new Stock(quantity, price));

            products.add(product);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProduct(Scanner sc) {
        try {
            listProducts();
            Product product = foundProduct(sc, "alterado");
            if (product == null) {
                System.out.println("Produto não encontrado");
                return;
            }
            int option;
            do {
                System.out.println("\n--- Atualizar Produto");
                System.out.println("1. Nome");
                System.out.println("2. Descrição");
                System.out.println("3. Fornecedor");
                System.out.println("4. Estoque");
                System.out.println("0. Voltar");
                option = Integer.parseInt(sc.nextLine());
                switch (option) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        System.out.print("Novo nome: ");
                        product.setName(sc.nextLine());
                    }
                    case 2 -> {
                        System.out.print("Nova descrição: ");
                        product.setDescription(sc.nextLine());
                    }
                    case 3 -> {
                        listSuppliers();
                        System.out.print("Escolha um novo fornecedor: ");
                        int supplierIndex = Integer.parseInt(sc.nextLine()) - 1;
                        if (supplierIndex < 0 || supplierIndex >= suppliers.size()) {
                            System.out.println("Fornecedor inválido");
                        } else {
                            product.setSupplier(suppliers.get(supplierIndex));
                        }
                    }
                    case 4 -> updateStock(sc, product.getStock());
                    default -> System.out.println("Opção inválida");
                }
            } while (true);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateStock(Scanner sc, Stock stock) {
        boolean backMenu = false;
        while (!backMenu) {
            System.out.println("\n--- Atualizar Estoque ---");
            System.out.println("1. Alterar preço");
            System.out.println("2. Alterar quantidade");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            int option = Integer.parseInt(sc.nextLine());
            switch (option) {
                case 0 -> backMenu = true;
                case 1 -> {
                    try {
                        System.out.print("Novo preço: ");
                        BigDecimal price = new BigDecimal(sc.nextLine());
                        stock.setPrice(price);
                        System.out.println("Preço atualizado com sucesso");
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido para o preço");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try {
                        System.out.print("Nova quantidade: ");
                        int quantity = Integer.parseInt(sc.nextLine());
                        stock.setQuantity(quantity);
                        System.out.println("Quantidade atualizada com sucesso");
                    } catch (NumberFormatException e) {
                        System.out.println("Valor inválido para a quantidade");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    public void removeProduct(Scanner sc) {
        if (products.isEmpty()) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }
        listProducts();
        Product product = foundProduct(sc, "removido");
        if (product == null) {
            System.out.println("Produto não encontrado");
            return;
        }
        products.remove(product);
        System.out.println("Produto removido com sucesso");
    }

    public void listProducts() {
        System.out.println("--- Lista de Produtos ---");
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
            if (i < products.size() - 1) {
                System.out.println("----------------");
            }
        }
    }

    public void listSuppliers() {
        System.out.println("--- Lista de Fornecedores ---");
        for (int i = 0; i < suppliers.size(); i++) {
            Supplier s = suppliers.get(i);
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getDescription());
        }
    }

    public Address createAddress(Scanner sc) {
        Address address = new Address();
        try {
            System.out.println("- Endereço -");
            System.out.print("Rua: ");
            address.setStreet(sc.nextLine());
            System.out.print("Número: ");
            address.setNumber(Integer.parseInt(sc.nextLine()));
            System.out.print("Complemento: ");
            address.setComplement(sc.nextLine());
            System.out.print("CEP: ");
            address.setZipCode(sc.nextLine());
            System.out.print("Cidade: ");
            address.setCity(sc.nextLine());
            System.out.print("Bairro: ");
            address.setNeighborhood(sc.nextLine());
            System.out.print("Estado: ");
            address.setState(sc.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return address;
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

    public void showProductByName(Scanner sc) {
        showByName(sc, products, Product::getName, Product::getId,  "produto");
    }

    public void showSupplierByName(Scanner sc) {
        showByName(sc, suppliers, Supplier::getName, Supplier::getId, "fornecedor");
    }

    private <T> void showByName(Scanner sc, List<T> list, Function<T, String> nameExtractor, Function<T, Integer> idExtractor, String entityType) {
        System.out.print("Digite nome ou id do " + entityType + ": ");
        String search = sc.nextLine();
        System.out.println("");

        boolean found = false;
        for (T item : list) {
            String name = nameExtractor.apply(item);
            if (name != null && name.toLowerCase().contains(search.toLowerCase())) {
                System.out.println(item);
                System.out.println("----------------");
                found = true;
            }

            String num = idExtractor.apply(item).toString();
            if(num != null && num.toLowerCase().contains(search))
            {
                System.out.println(item);
                System.out.println("----------------");
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