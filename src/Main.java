import Entities.Customer;
import Entities.Store;
import Entities.User;
import Menus.MainMenu;
import Menus.MenuAdmin;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();

        Scanner sc = new Scanner(System.in);

        int option;
        User user = null;

        do {
            option = MainMenu.show(sc);

            switch (option){
                case 1 -> user = store.login(sc);
                case 2 -> store.createNewAccount(sc);
                case 0 -> {
                    System.out.println("Saindo...");
                    System.exit(0);
                }
                default -> System.out.println("Opção inválida");
            }
        } while (user == null);

        if(user instanceof Customer){
            Customer customer = (Customer) user;
            store.menuCustomer(sc, customer);
        } else {
            MenuAdmin.show(sc, store);
        }
        sc.close();
    }
}