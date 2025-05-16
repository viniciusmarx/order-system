package Entities;

import java.math.BigDecimal;
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

    public void createNewAccount(Scanner sc){
        System.out.println("--- Cadastro de Novo Usuário");
        System.out.println("Nome: ");
        String name = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Senha: ");
        String password = sc.nextLine();

        System.out.println("Tipo de conta: ");
        System.out.println("1 - Admin");
        System.out.println("2 - Cliente");
        int role = Integer.parseInt(sc.nextLine());

        if(role == 2){
            System.out.println("Telefone: ");
            String phoneNumber = sc.nextLine();
            System.out.println("Cartão de crédito: ");
            String creditCard = sc.nextLine();

            Address address = createAddress(sc);
            Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
            users[totalUsers++] = customer;
            customers[totalCustomers++] = customer;
        } else{
            User newUser = new User(name, email, password);
            users[totalUsers++] = newUser;
        }
        System.out.println("Conta criada com sucesso!");
    }

    public User login(Scanner sc){
        System.out.println("--- LOGIN ---");
        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Senha: ");
        String password = sc.nextLine();

        for(int i = 0; i < totalUsers; i++){
            if(users[i].getEmail().equals(email) && users[i].getPassword().equals(password)){
                System.out.println("Bem-vindo(a), " + users[i].getName());
                return users[i];
            }
        }
        System.out.println("Email ou senha inválidos");
        return null;
    }

    public void menuCustomer(Scanner sc, Customer customer){
        int option;
        do{
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Ver produtos disponíveis");
            System.out.println("2. Ver meus dados");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option){
                case 1 -> listProducts();
                case 2 -> showCustomerData(customer);
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida");
            }
        } while (option != 0);
    }

    public void registerSupplier(Scanner sc){
        System.out.println("--- Cadastro de fornecedor ---");
        System.out.println("Nome: ");
        String name = sc.nextLine();
        System.out.println("Descrição: ");
        String description = sc.nextLine();
        System.out.println("Telefone: ");
        String phoneNumber = sc.nextLine();
        System.out.println("Email: ");
        String email = sc.nextLine();

        Address address = createAddress(sc);
        Supplier supplier = new Supplier(name, description, phoneNumber, email, address);

        suppliers[totalSuppliers++] = supplier;
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    public void registerProduct(Scanner sc){
        if(totalSuppliers == 0){
            System.out.println("Nenhum fornecedor cadastrado. Cadastre um fornecedor primeiro");
            return;
        }

        System.out.println("--- Cadastro de Produto ---");
        System.out.println("Nome: ");
        String name = sc.nextLine();
        System.out.println("Description: ");
        String description = sc.nextLine();

        System.out.println("Escolha um fornecedor:");
        for(int i = 0; i < totalSuppliers; i++){
            System.out.println((i + 1) + " - " + suppliers[i].getName());
        }

        int idSupplier = Integer.parseInt(sc.nextLine()) - 1;
        Supplier supplier = suppliers[idSupplier];

        System.out.println("Quantidade em estoque: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.println("Preço unitário: ");
        BigDecimal price = new BigDecimal(sc.nextLine());

        Stock stock = new Stock(quantity, price);
        Product product = new Product(name, description, supplier, stock);
        products[totalProducts++] = product;
        System.out.println("Produto cadstrado com sucesso!");
    }

    public void listProducts(){
        System.out.println("--- Lista de Produtos ---");
        for(int i = 0; i < totalProducts; i++){
            Product p = products[i];
            System.out.println((i + 1) + " - " + p.getName() + p.getStock().getPrice() +
                " Estoque: " + p.getStock().getQuantity() + "Fornecedor: " + p.getSupplier().getName());
        }
    }

    private void showCustomerData(Customer c){
        System.out.println("Nome: " + c.getName());
        System.out.println("Telefone: " + c.getPhoneNumber());
        System.out.println("Cartão: " + c.getCreditCard());
        System.out.println("Endereço: " +  c.getAddress());

    }

    public Address createAddress(Scanner sc){
        System.out.println("Endereço:");
        System.out.println("Rua: ");
        String street = sc.nextLine();
        System.out.println("Número: ");
        int number = Integer.parseInt(sc.nextLine());
        System.out.println("Complemento: ");
        String complement = sc.nextLine();
        System.out.println("CEP: ");
        String zipCode = sc.nextLine();
        System.out.println("Cidade: ");
        String city = sc.nextLine();
        System.out.println("State: ");
        String state = sc.nextLine();

        return new Address(street, number, complement, zipCode, city, state);
    }

    public void addUser(User newUser){
        users[totalUsers++] = newUser;
    }

    public void addCustomer(Customer newCustomer){
        users[totalUsers++] = newCustomer;
        customers[totalCustomers++] = newCustomer;
    }
}