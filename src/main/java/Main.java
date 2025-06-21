import Entities.Customer;
import Entities.Store;
import Entities.User;
import Menus.MainMenu;
import Menus.AdminMenu;
import Menus.CustomerMenu;
import Services.AuthService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();
        AuthService authService = new AuthService(store);
        store.addDBResources();

        Scanner sc = new Scanner(System.in);

        int option;

        while (true) {
            User user = null;
            do {
                option = MainMenu.show(sc);

                switch (option) {
                    case 1 -> user = authService.login(sc);
                    case 2 -> authService.createNewAccount(sc);
                    case 0 -> {
                        System.out.println("Saindo...");
                        System.exit(0);
                        sc.close();
                    }
                    default -> System.out.println("Opção inválida");
                }
            } while (user == null);

            if (user instanceof Customer c) {
                CustomerMenu.show(sc, c, store);
            } else {
                AdminMenu.show(sc, store);
            }
        }
    }
}