package Menus;

import Entities.Customer;
import Entities.Store;

import java.util.Scanner;

public class MenuCustomer {

    public static void show(Scanner sc, Customer customer, Store store) {

        int option;
        do {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Ver produtos disponíveis");
            System.out.println("2. Ver meus pedidos");
            System.out.println("3. Ver meus dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 0 -> {
                    return;
                }
//                case 1 -> handleSupplierMenu(sc, store);
//                case 2 -> handleProductMenu(sc, store);
//                case 3 -> handleStockMenu(sc, store);
                default -> System.out.println("Opção inválida");
            }
        } while (true);
    }
}
