package Menus;

import Entities.Store;
import Entities.Supplier;

import java.util.Scanner;

public class MenuAdmin {

    private static String[] supplierOptions = {
            "Cadastrar fornecedor", "Alterar fornecedor", "Remover fornecedor", "Listar fornecedores", "Pesquisar fornecedor"
    };
    private static String[] productOptions = {
            "Cadastrar produto", "Alterar produto", "Remover produto", "Listar produtos", "Pesquisar produto"
    };
    private static String[] stockOptions = {
            "Visualizar todo estoque"
    };

    public static void show(Scanner sc, Store store) {

        int option;
        do {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1. Fornecedores");
            System.out.println("2. Produtos");
            System.out.println("3. Estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 0 -> {
                    return;
                }
                case 1 -> handleSupplierMenu(sc, store);
                case 2 -> handleProductMenu(sc, store);
                case 3 -> handleStockMenu(sc, store);
                default -> System.out.println("Opção inválida");
            }
        } while (true);
    }

    private static void handleSupplierMenu(Scanner sc, Store store) {
        try {
            int option;
            do {
                option = menuOptions(supplierOptions, sc);
                switch (option) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> store.registerSupplier(sc);
                    case 2 -> store.updateSupplier(sc);
                    case 3 -> {
                        store.listSuppliers();
                        Supplier supplier = store.foundSupplier(sc, "removido");
                        if (store.canDeleteSupplier(supplier)) {
                            store.removeSupplier(supplier);
                            System.out.println("Fornecedor removido com sucesso");
                        } else {
                            System.out.println("Fornecedor não pode ser removido: está vinculado a um ou mais produtos");
                        }
                    }
                    case 4 -> store.listSuppliers();
                    case 5 -> store.showSupplierByName(sc);
                    default -> System.out.println("Opção inválida");
                }
            } while (true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleProductMenu(Scanner sc, Store store) {
        int option;
        do {
            option = menuOptions(productOptions, sc);
            switch (option) {
                case 0 -> {
                    return;
                }
                case 1 -> store.registerProduct(sc);
                case 2 -> store.updateProduct(sc);
                case 3 -> store.removeProduct(sc);
                case 4 -> store.listProducts();
                case 5 -> store.showProductByName(sc);
                default -> System.out.println("Opção inválida");
            }
        } while (true);
    }

    private static void handleStockMenu(Scanner sc, Store store) {
        int option;
        do {
            option = menuOptions(stockOptions, sc);
            switch (option) {
                case 0 -> {
                    return;
                }
                case 1 -> store.viewAllStock();
                default -> System.out.println("Opção inválida");
            }
        } while (true);
    }

    private static int menuOptions(String[] options, Scanner sc) {
        System.out.println("------------------------------------");
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s\n", i + 1, options[i]);
        }
        System.out.println("0. Voltar");
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(sc.nextLine());
    }
}
