package Menus;

import Entities.Product;
import Entities.Stock;
import Entities.Store;
import Entities.Supplier;
import Services.AddressFactory;

import java.math.BigDecimal;
import java.util.Scanner;

public class AdminMenu {

    public static void show(Scanner sc, Store store) {
        int option;
        do {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1. Fornecedores");
            System.out.println("2. Produtos");
            System.out.println("3. Visualizar todo estoque");
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
                option = menuOptions("Fornecedores", sc);
                switch (option) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        Supplier supplier = new Supplier();
                        System.out.print("Nome: ");
                        supplier.setName(sc.nextLine());
                        System.out.print("Descrição: ");
                        supplier.setDescription(sc.nextLine());
                        System.out.print("Telefone: ");
                        supplier.setPhoneNumber(sc.nextLine());
                        System.out.print("Email: ");
                        supplier.setEmail(sc.nextLine());
                        supplier.setAddress(AddressFactory.createAddress(sc));
                        store.registerSupplier(supplier);
                    }
                    case 2 -> {
                        store.listSuppliers();
                        System.out.println("Digite o código do fornecedor a ser alterado");
                        Supplier supplier = store.foundSupplier(Integer.parseInt(sc.nextLine()));
                        do {
                            System.out.println("\n--- Escolha o que você deseja atualizar ---");
                            System.out.println("1. Nome");
                            System.out.println("2. Descrição");
                            System.out.println("3. Telefone");
                            System.out.println("4. Email");
                            System.out.println("5. Endereço");
                            System.out.println("0. Voltar");
                            int updateOption = Integer.parseInt(sc.nextLine());
                            switch (updateOption) {
                                case 0 -> {
                                    return;
                                }
                                case 1 -> {
                                    System.out.print("Novo nome: ");
                                    supplier.setName(sc.nextLine());
                                }
                                case 2 -> {
                                    System.out.print("Nova descrição: ");
                                    supplier.setDescription(sc.nextLine());
                                }
                                case 3 -> {
                                    System.out.print("Novo telefone: ");
                                    supplier.setPhoneNumber(sc.nextLine());
                                }
                                case 4 -> {
                                    System.out.print("Novo email: ");
                                    supplier.setEmail(sc.nextLine());
                                }
                                case 5 -> {
                                    System.out.println("Novo endereço: ");
                                    supplier.setAddress(AddressFactory.createAddress(sc));
                                }
                            }
                        } while (true);
                    }
                    case 3 -> {
                        store.listSuppliers();
                        System.out.print("Digite o código do fornecedor a ser removido: ");
                        Supplier supplier = store.foundSupplier(Integer.parseInt(sc.nextLine()));
                        store.removeSupplier(supplier);
                    }
                    case 4 -> store.listSuppliers();
                    case 5 -> {
                        System.out.print("Digite o nome ou o código do fornecedor: ");
                        store.showSupplierByName(sc.nextLine());
                    }
                    default -> System.out.println("Opção inválida");
                }
            } while (true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleProductMenu(Scanner sc, Store store) {
        try {
            int option;
            do {
                option = menuOptions("Produtos", sc);
                switch (option) {
                    case 0 -> {
                        return;
                    }
                    case 1 -> {
                        if (store.getSuppliers().isEmpty()) {
                            System.out.println("Nenhum fornecedor cadastrado. Cadastre um fornecedor primeiro");
                            break;
                        }
                        Product product = new Product();
                        System.out.print("Nome: ");
                        product.setName(sc.nextLine());

                        System.out.print("Descrição: ");
                        product.setDescription(sc.nextLine());

                        store.listSuppliers();
                        System.out.print("Escolha um fornecedor: ");
                        int supplierId = Integer.parseInt(sc.nextLine());
                        Supplier supplier = store.foundSupplier(supplierId);
                        product.setSupplier(supplier);

                        System.out.print("Quantidade em estoque: ");
                        int quantity = Integer.parseInt(sc.nextLine());
                        System.out.print("Preço unitário: ");
                        BigDecimal price = new BigDecimal(sc.nextLine());
                        product.setStock(new Stock(quantity, price));
                        store.registerProduct(product);
                    }
                    case 2 -> {
                        store.listProducts();
                        System.out.println("Digite o código do produto a ser alterado");
                        Product product = store.foundProduct(Integer.parseInt(sc.nextLine()));
                        while (true) {
                            System.out.println("\n--- Escolha o que você deseja atualizar ---");
                            System.out.println("1. Nome");
                            System.out.println("2. Descrição");
                            System.out.println("3. Fornecedor");
                            System.out.println("4. Estoque");
                            System.out.println("0. Voltar");
                            int updateOption = Integer.parseInt(sc.nextLine());
                            switch (updateOption) {
                                case 0 -> {
                                    return;
                                }
                                case 1 -> {
                                    System.out.print("Novo nome: ");
                                    product.setName(sc.nextLine());
                                }
                                case 2 -> {
                                    System.out.print("Nova descrição: ");
                                    product.setDescription(sc.nextLine());
                                }
                                case 3 -> {
                                    store.listSuppliers();
                                    System.out.print("Escolha um novo fornecedor: ");
                                    Supplier newSupplier = store.foundSupplier(Integer.parseInt(sc.nextLine()));
                                    product.setSupplier(newSupplier);
                                }
                                case 4 -> {
                                    boolean backMenu = false;
                                    while (!backMenu) {
                                        System.out.println("\n--- Atualizar Estoque ---");
                                        System.out.println("1. Alterar preço");
                                        System.out.println("2. Alterar quantidade");
                                        System.out.println("0. Voltar");
                                        System.out.print("Escolha uma opção: ");
                                        int stockOption = Integer.parseInt(sc.nextLine());
                                        switch (stockOption) {
                                            case 0 -> backMenu = true;
                                            case 1 -> {
                                                System.out.print("Novo preço: ");
                                                BigDecimal price = new BigDecimal(sc.nextLine());
                                                product.getStock().setPrice(price);
                                                System.out.println("Preço atualizado");
                                            }
                                            case 2 -> {
                                                System.out.print("Nova quantidade: ");
                                                int quantity = Integer.parseInt(sc.nextLine());
                                                product.getStock().setQuantity(Integer.parseInt(sc.nextLine()));
                                                System.out.println("Quantidade atualizada com sucesso");
                                            }
                                        }
                                    }
                                }
                                default -> System.out.println("Opção inválida");
                            }
                        }
                    }
                    case 3 -> {
                        if (store.getProducts().isEmpty()){
                            System.out.println("Nenhum produto cadastrado");
                            break;
                        }
                        store.listProducts();
                        System.out.print("Digite o código do produto a ser removido: ");
                        Product product = store.foundProduct(Integer.parseInt(sc.nextLine()));
                        store.removeProduct(product);
                    }
                    case 4 -> store.listProducts();
                    case 5 -> {
                        System.out.print("Digite o nome ou o código do produto: ");
                        store.showProductByName(sc.nextLine());
                    }
                    default -> System.out.println("Opção inválida");
                }
            }
            while (true);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleStockMenu(Scanner sc, Store store) {
        int option;
        do {
            option = menuOptions("Estoque", sc);
            switch (option) {
                case 0 -> {
                    return;
                }
                case 1 -> store.viewAllStock();
                default -> System.out.println("Opção inválida");
            }
        } while (true);
    }

    private static int menuOptions(String name, Scanner sc) {
        System.out.println("\n---" + name + "---");
        System.out.println("1. Cadastrar");
        System.out.println("2. Alterar");
        System.out.println("3. Remover");
        System.out.println("4. Listar");
        System.out.println("5. Pesquisar");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        return Integer.parseInt(sc.nextLine());
    }
}
