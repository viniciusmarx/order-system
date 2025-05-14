import java.math.BigDecimal;
import java.util.Scanner;

public class MySystem {
    private static Admin[] admins = new Admin[10];
    private static Customer[] customers = new Customer[10];
    private static Supplier[] suppliers = new Supplier[10];
    private static Product[] products = new Product[100];

    private static int totalUsers = 0;
    private static int totalCustomers = 0;
    private static int totalSuppliers = 0;
    private static int totalProducts = 0;

    public static void createNewAccount(Scanner sc){
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

        User.Role userRole = (role == 1) ? User.Role.ADMIN : User.Role.CUSTOMER;
        User newUser = new User(name, email, password, userRole);
        addUser(newUser);

        if(userRole == User.Role.CUSTOMER){
            System.out.println("Telefone: ");
            String phoneNumber = sc.nextLine();
            System.out.println("Cartão de crédito: ");
            String creditCard = sc.nextLine();

            Address address = createAddress(sc);
            Customer customer = new Customer(name, email, phoneNumber, creditCard, address);
            customers[totalCustomers++] = customer;
        }
        System.out.println("Conta criada com sucesso!");
    }

    public static User login(Scanner sc){
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

    public static void menuAdmin(Scanner sc){
        int option;
        do{
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1. Cadastrar Fornecedor");
            System.out.println("2. Cadastrar Produto");
            System.out.println("3. Listar Produtos");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção");
            option = Integer.parseInt(sc.nextLine());

            switch (option){
                case 1 -> registerSupplier(sc);
                case 2 -> registerProduct(sc);
                case 3 -> listProducts();
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida");
            }
        } while(option != 0);
    }

    public static void menuCustomer(Scanner sc, User loggedUser){
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
                case 2 -> showCustomerData(loggedUser);
                case 0 -> System.out.println("Saindo do menu...");
                default -> System.out.println("Opção inválida");
            }
        } while (option != 0);
    }

    public static void registerSupplier(Scanner sc){
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

    public static void registerProduct(Scanner sc){
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

    public static void listProducts(){
        System.out.println("--- Lista de Produtos ---");
        for(int i = 0; i < totalProducts; i++){
            Product p = products[i];
            System.out.println((i + 1) + " - " + p.getName() + p.getStock().getPrice() +
                " Estoque: " + p.getStock().getQuantity() + "Fornecedor: " + p.getSupplier().getName());
        }
    }

    private static void showCustomerData(User user){
        for(int i = 0; i < totalCustomers; i++){
            if(customers[i].getEmail().equals(user.getEmail())){
                Customer c = customers[i];
                System.out.println("Nome: " + c.getName());
                System.out.println("Telefone: " + c.getPhoneNumber());
                System.out.println("Cartão: " + c.getCreditCard());
                System.out.println("Endereço: " +  c.getAddress());
                return;
            }
        }
        System.out.println("Cliente não encontrado");
    }

    public static void addUser(User user){
        users[totalUsers++] = user;
    }

    public static Address createAddress(Scanner sc){
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
}