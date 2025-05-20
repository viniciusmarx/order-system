package Entities;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

public class Store {
    private User[] users = new User[10];
    private Customer[] customers = new Customer[10];
    private Supplier[] suppliers = new Supplier[10];
    private Product[] products = new Product[100];

    private int totalUsers = 0;
    private int totalCustomers = 0;
    private int totalSuppliers = 0;
    private int totalProducts = 0;

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
                Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
                addCustomer(customer);
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

        for (int i = 0; i < totalUsers; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                System.out.println("Bem-vindo(a), " + users[i].getName());
                return users[i];
            }
        }
        System.out.println("Email ou senha inválidos");
        return null;
    }

    private boolean isEmailRegistered(String email) {
        for (int i = 0; i < totalUsers; i++) {
            if (users[i].getEmail().equalsIgnoreCase(email)) {
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
            String name = sc.nextLine();
            supplier.setName(name);

            System.out.print("Descrição: ");
            String description = sc.nextLine();
            supplier.setDescription(description);

            System.out.print("Telefone: ");
            String phoneNumber = sc.nextLine();
            supplier.setPhoneNumber(phoneNumber);

            System.out.print("Email: ");
            String email = sc.nextLine();
            supplier.setEmail(email);

            Address address = createAddress(sc);
            supplier.setAddress(address);

            suppliers[totalSuppliers++] = supplier;
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public Supplier foundSupplier(Scanner sc, String operation) {
        System.out.print("Digite o código do fornecedor a ser " + operation + ": ");
        int supplierPosition = Integer.parseInt(sc.nextLine());

        if (supplierPosition <= 0 || supplierPosition > totalSuppliers) {
            throw new IllegalArgumentException("Fornecedor inválido");
        }
        return suppliers[supplierPosition - 1];
    }

    public Product foundProduct(Scanner sc, String operation) {
        System.out.print("Digite o código do produto a ser " + operation + ": ");
        int productId = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < totalProducts; i++) {
            if (productId == products[i].getId()) {
                return products[i];
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
                        String name = sc.nextLine();
                        supplier.setName(name);
                    }
                    case 2 -> {
                        System.out.print("Nova descrição: ");
                        String description = sc.nextLine();
                        supplier.setDescription(description);
                    }
                    case 3 -> {
                        System.out.print("Novo telefone: ");
                        String phoneNumber = sc.nextLine();
                        supplier.setPhoneNumber(phoneNumber);
                    }
                    case 4 -> {
                        System.out.print("Novo email: ");
                        String email = sc.nextLine();
                        supplier.setEmail(email);
                    }
                    case 5 -> {
                        System.out.println("Novo endereço: ");
                        Address address = createAddress(sc);
                        supplier.setAddress(address);
                    }
                }
            } while (true);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean canDeleteSupplier(Supplier supplier) {
        for (Product product : products) {
            if (product != null && product.getSupplier().equals(supplier)) {
                System.out.println("Fornecedor está vinculado a um ou mais produtos e não pode ser removido");
                return false;
            }
        }
        return true;
    }

    public void removeSupplier(Supplier supplier) {
        int index = -1;
        for (int i = 0; i < totalSuppliers; i++) {
            if (suppliers[i] == supplier) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < totalSuppliers - 1; i++) {
                suppliers[i] = suppliers[i + 1];
            }
            suppliers[--totalSuppliers] = null;
        }
    }

    public void registerProduct(Scanner sc) {
        try {
            if (totalSuppliers == 0) {
                System.out.println("Nenhum fornecedor cadastrado. Cadastre um fornecedor primeiro");
                return;
            }
            Product product = new Product();
            System.out.println("--- Cadastro de Produto ---");
            System.out.print("Nome: ");
            String name = sc.nextLine();
            product.setName(name);

            System.out.print("Descrição: ");
            String description = sc.nextLine();
            product.setDescription(description);

            listSuppliers();
            System.out.print("Escolha um fornecedor: ");
            int idSupplier = Integer.parseInt(sc.nextLine()) - 1;
            if (idSupplier < 0 || idSupplier >= totalSuppliers) {
                throw new IllegalArgumentException("Fornecedor inválido");
            }
            Supplier supplier = suppliers[idSupplier];
            product.setSupplier(supplier);

            System.out.print("Quantidade em estoque: ");
            int quantity = Integer.parseInt(sc.nextLine());
            System.out.print("Preço unitário: ");
            BigDecimal price = new BigDecimal(sc.nextLine());
            Stock stock = new Stock(quantity, price);
            product.setStock(stock);

            products[totalProducts++] = product;
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
            int fieldOption;
            do {
                System.out.println("\n--- Atualizar Produto");
                System.out.println("1. Nome");
                System.out.println("2. Descrição");
                System.out.println("3. Fornecedor");
                System.out.println("4. Estoque");
                System.out.println("0. Voltar");
                fieldOption = Integer.parseInt(sc.nextLine());

                switch (fieldOption) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        System.out.print("Novo nome: ");
                        String name = sc.nextLine();
                        product.setName(name);
                    }
                    case 2 -> {
                        System.out.print("Nova descrição: ");
                        String description = sc.nextLine();
                        product.setDescription(description);
                    }
                    case 3 -> {
                        listSuppliers();
                        System.out.print("Escolha um novo fornecedor: ");
                        int supplierIndex = Integer.parseInt(sc.nextLine()) - 1;
                        if (supplierIndex < 0 || supplierIndex >= totalSuppliers) {
                            System.out.println("Fornecedor inválido");
                        } else {
                            product.setSupplier(suppliers[supplierIndex]);
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
        if (totalProducts == 0) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        for (int i = 0; i < totalProducts; i++) {
            System.out.println("Id: " + products[i].getId() + " Nome: " + products[i].getName());
        }
        System.out.print("Digite o Id do produto que deseja remover");
        int productId = Integer.parseInt(sc.nextLine());

        int index = returnProductIndex(productId);
        if (index == -1) {
            System.out.println("Produto não encontrado");
            return;
        }

        for (int i = index; i < totalProducts - 1; i++) {
            products[i] = products[i + 1];
        }
        products[--totalProducts] = null;
    }

    private int returnProductIndex(int id) {
        for (int i = 0; i < totalProducts; i++) {
            if (products[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void listProducts() {
        System.out.println("--- Lista de Produtos ---");
        for (int i = 0; i < totalProducts; i++) {
            Product p = products[i];
            System.out.println(p);
            if (i != totalProducts-1) {System.out.println("----------------");}
        }
    }

    public void listSuppliers() {
        System.out.println("--- Lista de Fornecedores ---");
        for (int i = 0; i < totalSuppliers; i++) {
            Supplier s = suppliers[i];
            System.out.println((i + 1) + " - " + s.getName() + " - " + s.getDescription());
        }
    }

    public Address createAddress(Scanner sc) {
        Address address = new Address();
        try {
            System.out.println("- Endereço -");
            System.out.print("Rua: ");
            String street = sc.nextLine();
            address.setStreet(street);
            System.out.print("Número: ");
            int number = Integer.parseInt(sc.nextLine());
            address.setNumber(number);
            System.out.print("Complemento: ");
            String complement = sc.nextLine();
            address.setComplement(complement);
            System.out.print("CEP: ");
            String zipCode = sc.nextLine();
            address.setZipCode(zipCode);
            System.out.print("Cidade: ");
            String city = sc.nextLine();
            address.setCity(city);
            System.out.print("Bairro: ");
            String neighborhood = sc.nextLine();
            address.setNeighborhood(neighborhood);
            System.out.print("Estado: ");
            String state = sc.nextLine();
            address.setState(state);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return address;
    }

    public void viewAllStock() {
        System.out.println("--- Estoque Geral ---");

        if (totalProducts == 0) {
            System.out.println("Nenhum produto cadastrado");
            return;
        }

        for (Product p : products) {
            System.out.printf("Id: %d | Nome: %s | Preço: R$%.2f | Quantidade: %d\n",
                    p.getId(), p.getName(), p.getStock().getPrice(), p.getStock().getQuantity());
        }
    }

    public void addUser(User newUser) {
        users[totalUsers++] = newUser;
    }

    public void addCustomer(Customer newCustomer) {
        users[totalUsers++] = newCustomer;
        customers[totalCustomers++] = newCustomer;
    }

    public void addDBResources() {

        //Suppliers
        suppliers[totalSuppliers++] = new Supplier("FF Frutas", "Vendas diretas de frutas", "5399543421", "fffrutas@supp.com",
                new Address("Rua Fantasia", 12323, "", "Centro", "12345678", "São Paulo", "SP"));
        suppliers[totalSuppliers++] = new Supplier("Carqueja", "Deposito de verduras e insumos para chá", "5399543421", "carq@yahoo.com",
                new Address("Rua ilusão", 456, "Fundos", "Tijuca", "87654321", "Rio de Janeiro", "RJ"));
        suppliers[totalSuppliers++] = new Supplier("Canoas Yerbas", "Distribuidora de produtos naturais", "5399543421", "yerbcanoas@gmail.com",
                new Address("Rua do sol", 123, "Box 13", "Matias Velho", "12345678", "Canoas", "RS"));
        suppliers[totalSuppliers++] = new Supplier("Cersul", "Comercio de Cereais", "5399543421", "cersul@gmail.com",
                new Address("Rodovia do sol", 1083, "Pavilhão 1", "Interior", "95185000", "Carlos Barbosa", "RS"));

        //Customers
        users[totalUsers++] = new User("Joao Pereira", "jpere@mail.com", "123456");
        customers[totalCustomers++] = new Customer("Joao Pereira", "jpere@mail.com", "123456", "9112225499", "9299321645312543",
                new Address("Cruz de Malta", 1205, "", "Interior", "93411000", "Araranguá", "PR"));

        //products
        //public Product(String name, String description, Supplier supplier, Stock stock)
        products[totalProducts++] = new Product("Alface", "Alface americana", suppliers[0], new Stock(10, new BigDecimal("2.50")));
        products[totalProducts++] = new Product("Cenoura", "Cenoura orgânica", suppliers[1], new Stock(20, new BigDecimal("1.50")));
        products[totalProducts++] = new Product("Maçã", "Maçã verde", suppliers[2], new Stock(15, new BigDecimal("3.00")));
        products[totalProducts++] = new Product("Laranja", "Laranja Lima", suppliers[3], new Stock(25, new BigDecimal("2.00")));
        products[totalProducts++] = new Product("Batata", "Batata doce", suppliers[0], new Stock(30, new BigDecimal("1.20")));




        //GENERIC USERS

        // users[totalUsers++] = Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
        // customers[totalCustomers++] = Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
        users[totalUsers++] = new User("User", "user@user.com", "123123");
        customers[totalCustomers++] = new Customer("User", "user@user.com", "123123", "12345678912", "1234567890123456",
                new Address("Customer", 001, "User", "User", "12345678", "User City", "BR"));

        // users[totalUsers++] = User newUser = new User(name, email, password);
        users[totalUsers++] = new User("Admin", "adm@adm.com", "123123");
    }
}