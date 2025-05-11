//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.util.Scanner;

public class Main {

    private static Scanner sc;
    private static MySystem system;
    public static void main(String[] args) {

        Main main = new Main();
        User user = main.login();
        main.showMenu(user);
    }

    public void showMenu(User user){
        sc = new Scanner(System.in);
        int option = 0;
        do{
            System.out.println("---------------------------------------------");
            System.out.println("1 - Produts");
            if(user.getRole() == 1 ){
                System.out.println("2 - Suppliers");
                System.out.println("3 - Inventory");
            }
            System.out.println("0 - Exit");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            switch(option){
                case 1:
                    // Implementar Produtos
                    break;
                case 2:
                    menuCustomers(sc);
                    break;
                case 3:
                    menuInventory(sc);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while(option != 0);
        System.out.println("---------------------------------------------");
    }

    public void menuCustomers(Scanner sc){
        int option = 0;
        do{
            System.out.println("---------------------------------------------");
            System.out.println("1 - Add Customer");
            System.out.println("2 - Edit Customer");
            System.out.println("3 - Remove Customer");
            System.out.println("4 - List Customers");
            System.out.println("0 - Back");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            switch(option){
                case 1:
                    System.out.println("cadastro de fornecedor");
                    menuCustomers(sc);
                    // Implementar Cadastro de Fornecedor
                    break;
                case 2:
                    System.out.println("edição de fornecedor");
                    menuCustomers(sc);
                    // Implementar Edição de Fornecedor
                    break;
                case 3:
                    System.out.println("remoção de fornecedor");
                    menuCustomers(sc);
                    // Implementar Remoção de Fornecedor
                    break;
                case 4:
                    System.out.println("listagem de fornecedor");
                    menuCustomers(sc);
                    // Implementar Listagem de Fornecedores
                    break;
                case 0:
                    System.out.println("Returning ...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    menuCustomers(sc);
            }
            return;
        }while(option != 0);
    }

    public void menuInventory(Scanner sc){
        int option = 0;
        do{
            System.out.println("---------------------------------------------");
            System.out.println("1 - Add Product");
            System.out.println("2 - Edit Product");
            System.out.println("3 - Remove Product");
            System.out.println("4 - List Products");
            System.out.println("0 - Back");
            System.out.print("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            switch(option){
                case 1:
                    System.out.println("cadastro de produto");
                    menuInventory(sc);
                    // Implementar Cadastro de produto
                    break;
                case 2:
                    System.out.println("edição de produto");
                    menuInventory(sc);
                    // Implementar Edição de produto
                    break;
                case 3:
                    System.out.println("remoção de produto");
                    menuInventory(sc);
                    // Implementar Remoção de produto
                    break;
                case 4:
                    System.out.println("listagem de produto");
                    menuInventory(sc);
                    // Implementar Listagem de produto
                    break;
                case 0:
                    System.out.println("Returning ...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    menuInventory(sc);
            }
            return;
        }while(option != 0);
    }

    public User login(){
        system = new MySystem();
        sc = new Scanner(System.in);
        User loggedIn;
        int count = 0;

        do{
            System.out.println("---------------------------------------------");
            System.out.print("Write your email: ");
            String email = sc.nextLine();
            System.out.print("Write your password: ");
            String password = sc.nextLine();

            loggedIn = system.login(email, password);
            if(loggedIn == null){
                count++;
                if(count == 3){
                    System.out.println("You have exceeded the maximum number of login attempts. Try again later");
                    System.exit(0);
                }
            }
        } while(loggedIn == null);

        System.out.println("---------------------------------------------");
        sc.close();
        return loggedIn;
    }
}