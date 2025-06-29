package Menus;

import Entities.*;
import Services.AddressFactory;
import Services.OrderService;
import Services.StoreService;
import Utils.InputUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    public static void show(Scanner sc, StoreService storeService, OrderService orderService) {
        do {
            System.out.println("\n--- MENU ADMIN ---");
            System.out.println("1. Fornecedores");
            System.out.println("2. Produtos");
            System.out.println("3. Visualizar todo estoque");
            System.out.println("4. Visualizar ordens");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            String option = sc.nextLine();

            switch (option) {
                case "0" -> {
                    return;
                }
                case "1" -> handleSupplierMenu(sc, storeService);
                case "2" -> handleProductMenu(sc, storeService);
                case "3" -> storeService.viewAllStock();
                case "4" -> handleOrderManagement(sc, orderService);
                default -> System.out.println("Opção inválida");
            }
        } while (true);
    }

    private static void handleSupplierMenu(Scanner sc, StoreService storeService) {
        try {
            do {
                String option = menuOptions("Fornecedores", sc);
                switch (option) {
                    case "0" -> {
                        return;
                    }
                    case "1" -> {
                        Supplier supplier = new Supplier();
                        InputUtils.promptAndSetString(sc, "Nome", supplier::setName);
                        InputUtils.promptAndSetString(sc, "Descrição", supplier::setDescription);
                        InputUtils.promptAndSetString(sc, "Telefone", supplier::setPhoneNumber);
                        InputUtils.promptAndSetString(sc, "Email", supplier::setEmail);
                        supplier.setAddress(AddressFactory.createAddress(sc));
                        storeService.registerSupplier(supplier);
                    }
                    case "2" -> {
                        if (!storeService.hasSuppliers()) {
                            System.out.println("Não há nenhum fornecedor cadastrado");
                            continue;
                        }
                        storeService.listSuppliers();
                        System.out.println("Digite o código do fornecedor a ser alterado");
                        Supplier supplier = storeService.getSupplierById(Integer.parseInt(sc.nextLine()));
                        if (supplier == null) {
                            System.out.println("Fornecedor não encontrado");
                            continue;
                        }
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
                                case 1 -> InputUtils.promptAndSetString(sc, "Novo nome", supplier::setName);
                                case 2 -> InputUtils.promptAndSetString(sc, "Nova descrição", supplier::setDescription);
                                case 3 -> InputUtils.promptAndSetString(sc, "Novo telefone", supplier::setPhoneNumber);
                                case 4 -> InputUtils.promptAndSetString(sc, "Novo email", supplier::setEmail);
                                case 5 -> {
                                    System.out.println("Novo endereço: ");
                                    supplier.setAddress(AddressFactory.createAddress(sc));
                                }
                            }
                        } while (true);
                    }
                    case "3" -> {
                        storeService.listSuppliers();
                        System.out.print("Digite o código do fornecedor a ser removido: ");
                        Supplier supplier = storeService.getSupplierById(Integer.parseInt(sc.nextLine()));
                        storeService.removeSupplier(supplier);
                    }
                    case "4" -> {
                        if (!storeService.hasSuppliers()) {
                            System.out.println("Nenhum fornecedor cadastrado");
                            continue;
                        }
                        storeService.listSuppliers();
                    }
                    case "5" -> {
                        System.out.print("Digite o nome ou o código do fornecedor: ");
                        List<Supplier> suppliers = storeService.searchSuppliers(sc.nextLine());
                        if (suppliers.isEmpty()) {
                            System.out.println("Nenhum fornecedor encontrado");
                        } else {
                            for (Supplier s : suppliers) {
                                System.out.println("\n" + s);
                            }
                        }
                    }
                    default -> System.out.println("Opção inválida");
                }
            } while (true);
        } catch (NumberFormatException ex) {
            System.out.println("Insira um numeral");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleProductMenu(Scanner sc, StoreService storeService) {
        try {
            do {
                String option = menuOptions("Produtos", sc);
                switch (option) {
                    case "0" -> {
                        return;
                    }
                    case "1" -> {
                        if (!storeService.hasSuppliers()) {
                            System.out.println("Nenhum fornecedor cadastrado. Cadastre um fornecedor primeiro");
                            break;
                        }
                        Product product = new Product();
                        InputUtils.promptAndSetString(sc, "Nome", product::setName);
                        InputUtils.promptAndSetString(sc, "Descrição", product::setDescription);
                        storeService.listSuppliers();
                        System.out.print("Escolha um fornecedor: ");
                        int supplierId = Integer.parseInt(sc.nextLine());
                        Supplier supplier = storeService.getSupplierById(supplierId);
                        product.setSupplier(supplier);

                        System.out.print("Quantidade em estoque: ");
                        int quantity = Integer.parseInt(sc.nextLine());
                        System.out.print("Preço unitário: ");
                        BigDecimal price = new BigDecimal(sc.nextLine());
                        product.setStock(new Stock(quantity, price));
                        storeService.registerProduct(product);
                    }
                    case "2" -> {
                        if (!storeService.hasProducts()) {
                            System.out.println("Não há nenhum produto cadastrado");
                            continue;
                        }
                        storeService.listProducts();
                        System.out.println("Digite o código do produto a ser alterado");
                        Product product = storeService.getStore().foundProduct(Integer.parseInt(sc.nextLine()));
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
                                    InputUtils.promptAndSetString(sc, "Novo nome", product::setName);
                                    storeService.save();
                                }
                                case 2 -> {
                                    InputUtils.promptAndSetString(sc, "Nova descrição", product::setDescription);
                                    storeService.save();
                                }
                                case 3 -> {
                                    storeService.listSuppliers();
                                    System.out.print("Escolha um novo fornecedor: ");
                                    Supplier newSupplier = storeService.getSupplierById(Integer.parseInt(sc.nextLine()));
                                    product.setSupplier(newSupplier);
                                    storeService.save();
                                }
                                case 4 -> {
                                    boolean backMenu = false;
                                    while (!backMenu) {
                                        System.out.println("\n--- Atualizar Estoque ---");
                                        System.out.println("1. Alterar preço");
                                        System.out.println("2. Alterar quantidade");
                                        System.out.println("0. Voltar");
                                        System.out.print("Escolha uma opção: ");
                                        String stockOption = sc.nextLine();
                                        switch (stockOption) {
                                            case "0" -> backMenu = true;
                                            case "1" -> {
                                                System.out.print("Novo preço: ");
                                                BigDecimal price = new BigDecimal(sc.nextLine());
                                                product.getStock().setPrice(price);
                                                storeService.save();
                                                System.out.println("Preço atualizado");
                                            }
                                            case "2" -> {
                                                System.out.print("Nova quantidade: ");
                                                product.getStock().setQuantity(Integer.parseInt(sc.nextLine()));
                                                storeService.save();
                                                System.out.println("Quantidade atualizada com sucesso");
                                            }
                                        }
                                    }
                                }
                                default -> System.out.println("Opção inválida");
                            }
                        }
                    }
                    case "3" -> {
                        if (!storeService.hasProducts()) {
                            System.out.println("Nenhum produto cadastrado");
                            continue;
                        }
                        storeService.listProducts();
                        System.out.print("Digite o código do produto a ser removido: ");
                        Product product = storeService.getProductById(Integer.parseInt(sc.nextLine()));
                        storeService.removeProduct(product);
                    }
                    case "4" -> {
                        if (!storeService.hasProducts()) {
                            System.out.println("Nenhum produto cadastrado");
                            continue;
                        }
                        storeService.listProducts();
                    }
                    case "5" -> {
                        System.out.print("Digite o nome ou o código do produto: ");
                        List<Product> products = storeService.searchProducts(sc.nextLine());
                        if (products.isEmpty()) {
                            System.out.println("Nenhum produto encontrado");
                        } else {
                            for (Product p : products) {
                                System.out.println("\n" + p);
                            }
                        }
                    }
                    default -> System.out.println("Opção inválida");
                }
            }
            while (true);
        } catch (NumberFormatException ex) {
            System.out.println("Insira um numeral");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleOrderManagement(Scanner sc, OrderService orderService) {
        List<Order> orders = orderService.getAllOrders();

        if (orders.isEmpty()) {
            System.out.println("Nenhum pedido encontrado");
            return;
        }

        while (true) {
            System.out.println("\nConsultar por:");
            System.out.println("1. Número do pedido");
            System.out.println("2. Data de realização");
            System.out.println("3. Voltar");
            String option = sc.nextLine();

            switch (option) {
                case "1" -> handleOrdersByNumber(sc, orders, orderService);
                case "2" -> handleOrdersByDate(sc, orders);
                case "3" -> {
                    return;
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void handleOrdersByNumber(Scanner sc, List<Order> orders, OrderService orderService) {
        System.out.print("Número do pedido: ");
        try {
            int number = Integer.parseInt(sc.nextLine());
            Order selectedOrder = orders.stream()
                    .filter(o -> o.getNumber() == number)
                    .findFirst().orElse(null);

            if (selectedOrder == null) {
                System.out.println("Pedido não encontrado");
                return;
            }

            printOrderDetails(selectedOrder);

            if (selectedOrder.getStatus() != OrderStatus.PENDING) {
                return;
            }

            System.out.println("\nAlterar status do pedido:");
            System.out.println("1. Enviar pedido");
            System.out.println("2. Cancelar pedido");
            System.out.println("3. Voltar");
            String statusOption = sc.nextLine();

            switch (statusOption) {
                case "1" -> orderService.updateOrderStatus(selectedOrder, OrderStatus.SHIPPED);
                case "2" -> orderService.updateOrderStatus(selectedOrder, OrderStatus.CANCELLED);
                case "3" -> System.out.println("Voltando...");
                default -> System.out.println("Opção inválida.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Número inválido");
        }
    }

    private static void handleOrdersByDate(Scanner sc, List<Order> orders) {
        System.out.print("Data (yyyy-MM-dd): ");
        try {
            LocalDate date = LocalDate.parse(sc.nextLine());
            List<Order> result = orders.stream()
                    .filter(o -> o.getOrderDate().equals(date)).toList();

            if (result.isEmpty()) {
                System.out.println("Nenhum pedido encontrado");
            } else {
                result.forEach(AdminMenu::printOrderDetails);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida");
        }
    }

    private static void printOrderDetails(Order order) {
        System.out.println("\n--- Pedido #" + order.getNumber() + " ---");
        System.out.println("Data do pedido: " + order.getOrderDate());
        System.out.println("Data de entrega: " +
                (order.getDeliveryDate() != null ? order.getDeliveryDate() : "Ainda não enviado"));
        System.out.println("Status: " + order.getStatus());
        System.out.printf("Valor total (com ICMS): R$ %.2f\n", order.getTotalOrderValue());
        System.out.println("Itens:");
        for (OrderItem item : order.getItems()) {
            System.out.printf(" - %s | Qtde: %d | Unitário: R$ %.2f | Total: R$ %.2f\n",
                    item.getProductName(), item.getQuantity(),
                    item.getUnitPrice(), item.getTotalPrice());
        }
    }

    private static String menuOptions(String name, Scanner sc) {
        System.out.println("\n---" + name + "---");
        System.out.println("1. Cadastrar");
        System.out.println("2. Alterar");
        System.out.println("3. Remover");
        System.out.println("4. Listar");
        System.out.println("5. Pesquisar");
        System.out.println("0. Voltar");
        System.out.print("Escolha: ");
        return sc.nextLine();
    }
}
