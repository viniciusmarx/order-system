import Entities.Customer;
import Entities.Store;
import Entities.User;
import Menus.MainMenu;
import Menus.AdminMenu;
import Menus.CustomerMenu;
import Persistence.StorePersistence;
import Repository.StoreRepository;
import Services.AuthService;
import Services.OrderService;
import Services.StoreService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Store store = StorePersistence.load();
        StoreRepository repository = new StoreRepository(store);
        StoreService storeService = new StoreService(repository);
        OrderService orderService = new OrderService(repository);
        AuthService authService = new AuthService(storeService);

        int option;

        while (true) {
            User user = null;

            while (user == null) {
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
            }

            if (user instanceof Customer c) {
                CustomerMenu.show(sc, c, storeService, orderService);
            } else {
                AdminMenu.show(sc, storeService, orderService);
            }
        }
    }
}