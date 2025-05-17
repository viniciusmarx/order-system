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
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String password = sc.nextLine();

        System.out.println("- Tipo de conta - ");
        System.out.println("1 - Admin");
        System.out.println("2 - Cliente");
        System.out.print("Escolha: ");
        int role = Integer.parseInt(sc.nextLine());

        if(role == 2){
            System.out.print("Telefone: ");
            String phoneNumber = sc.nextLine();
            System.out.print("Cartão de crédito: ");
            String creditCard = sc.nextLine();

            Address address = createAddress(sc);
            Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
            users[totalUsers++] = customer;
            customers[totalCustomers++] = customer;
        } else{
            User newUser = new User(name, email, password);
            users[totalUsers++] = newUser;
        }
    }

    public User login(Scanner sc){
        System.out.println("--- LOGIN ---");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Senha: ");
        String password = sc.nextLine();

        for(int i = 0; i < totalCustomers; i++){
            if(customers[i].getEmail().equals(email) && customers[i].getPassword().equals(password)){
                System.out.println("Bem-vindo(a), " + customers[i].getName());
                return customers[i];
            }
        }
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
            System.out.print("Escolha uma opção: ");
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
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Descrição: ");
        String description = sc.nextLine();
        System.out.print("Telefone: ");
        String phoneNumber = sc.nextLine();
        System.out.print("Email: ");
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
        System.out.print("Nome: ");
        String name = sc.nextLine();
        System.out.print("Description: ");
        String description = sc.nextLine();

        System.out.println("Lista de Fornecedores: ");
        for(int i = 0; i < totalSuppliers; i++){
            System.out.println((i + 1) + " - " + suppliers[i].getName());
        }

        System.out.print("Escolha um fornecedor:");
        int idSupplier = Integer.parseInt(sc.nextLine()) - 1;
        Supplier supplier = suppliers[idSupplier];

        System.out.print("Quantidade em estoque: ");
        int quantity = Integer.parseInt(sc.nextLine());

        System.out.print("Preço unitário: ");
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
            System.out.println(i+1);
            System.out.println(p);
            // System.out.println((i + 1) + " - " + p.getName() + p.getStock().getPrice() +
            //     " Estoque: " + p.getStock().getQuantity() + "Fornecedor: " + p.getSupplier().getName());
        }
    }

    public void listSuppliers(){
        System.out.println("--- Lista de Fornecedores ---");
        for(int i = 0; i < totalSuppliers; i++){
            Supplier s = suppliers[i];
            System.out.println((i + 1) + " - " + s.getName() + " - " + s.getDescription());
        }
    }

    public void showSupplierData(Supplier s){
        System.out.println(s);
    }

    private void showCustomerData(Customer c){
        System.out.println(c);
    }

    public Address createAddress(Scanner sc){
        System.out.println("- Endereço -");
        System.out.print("Rua: ");
        String street = sc.nextLine();
        System.out.print("Número: ");
        int number = Integer.parseInt(sc.nextLine());
        System.out.print("Complemento: ");
        String complement = sc.nextLine();
        System.out.print("CEP: ");
        String zipCode = sc.nextLine();
        System.out.print("Cidade: ");
        String city = sc.nextLine();
        System.out.print("Bairro: ");
        String neighborhood = sc.nextLine();
        System.out.print("Estado: ");
        String state = sc.nextLine();

        return new Address(street, number, complement, zipCode, city, neighborhood, state);
    }

    public void addUser(User newUser){
        users[totalUsers++] = newUser;
    }

    public void addCustomer(Customer newCustomer){
        users[totalUsers++] = newCustomer;
        customers[totalCustomers++] = newCustomer;
    }

    public void addDBResources(){

        //Suppliers
        suppliers[totalSuppliers++] = new Supplier("FF Frutas", "Vendas diretas de frutas", "5399543421", "fffrutas@supp.com", 
                                        new Address("Rua Fantasia", 12323, "", "Centro", "12345678", "São Paulo", "SP"));
        suppliers[totalSuppliers++] = new Supplier("Carqueja", "Deposito de verduras e insumos para chá", "5399543421", "carq@yahoo.com", 
                                        new Address("Rua ilusão", 456, "Fundos", "Tijuca", "87654321", "Rio de Janeiro", "RJ"));
        suppliers[totalSuppliers++] = new Supplier("Canoas Yerbas", "Distribuidora de produtos naturais", "5399543421", "yerbcanoas@gmail.com", 
                                        new Address("Rua do sol", 123, "Box 13", "Matias Velho", "12345678", "Canoas", "RS"));

        //Customers
        users[totalUsers++] = new User("Joao Pereira", "jpere@mail.com", "123456");
        customers[totalCustomers++] = new Customer("Joao Pereira", "jpere@mail.com", "123456", "91122254", "9299321645312543", 
                                        new Address("Cruz de Malta", 1205, "", "Interior", "93411000", "Araranguá", "PR"));


        

        //GENERIC USERS

        // users[totalUsers++] = Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
        // customers[totalCustomers++] = Customer customer = new Customer(name, email, password, phoneNumber, creditCard, address);
        users[totalUsers++] = new User("Customer", "cust@customer.com", "123123");
        customers[totalCustomers++] =  new Customer("Customer", "cust@customer.com", "123123", "123456789", "1234567890123456", 
                                        new Address("Customer", 001, "TESTE", "Customer", "12345678", "Customer City", "BR"));

        // users[totalUsers++] = User newUser = new User(name, email, password);
        users[totalUsers++] = new User("Admin", "admin@admin.com" , "123123");
        
        
 }
}