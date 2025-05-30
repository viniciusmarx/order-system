import Entities.Customer;
import Entities.Store;
import Entities.User;
import Menus.MainMenu;
import Menus.MenuAdmin;
import Menus.MenuCustomer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();
        store.addDBResources();

        Scanner sc = new Scanner(System.in);

        int option;

        while (true) {
            User user = null;
            do {
                option = MainMenu.show(sc);

                switch (option) {
                    case 1 -> user = store.login(sc);
                    case 2 -> store.createNewAccount(sc);
                    case 0 -> {
                        System.out.println("Saindo...");
                        System.exit(0);
                        sc.close();
                    }
                    default -> System.out.println("Opção inválida");
                }
            } while (user == null);

            if (user instanceof Customer c) {
                MenuCustomer.show(sc, c, store);
            } else {
                MenuAdmin.show(sc, store);
            }
        }
    }
}