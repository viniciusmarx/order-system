package Menus;

import Entities.*;
import Services.OrderService;
import Services.StoreService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    public static void show(Scanner sc, Customer customer, StoreService storeService, OrderService orderService) {
        while (true) {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Buscar produto");
            System.out.println("2. Visualizar todos produtos");
            System.out.println("3. Ver carrinho");
            System.out.println("4. Limpar carrinho");
            System.out.println("5. Finalizar pedido");
            System.out.println("6. Consultar pedidos");
            System.out.println("7. Consultar meus dados");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            String option = sc.nextLine();
            switch (option) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    Product product = searchProduct(sc, storeService);
                    if (product != null) {
                        addProductToCart(sc, product, customer.getCart());
                    }
                }
                case "2" -> storeService.listProducts();
                case "3" -> {
                    if (customer.getCart().isEmpty()) {
                        System.out.println("\nCarrinho vazio");
                        continue;
                    }
                    System.out.println("\n --- Meu carrinho ---");
                    showCart(customer.getCart());
                }
                case "4" -> customer.getCart().clear();
                case "5" -> finalizeOrder(sc, customer, orderService);
                case "6" -> checkOrders(sc, customer, orderService);
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static Product searchProduct(Scanner sc, StoreService storeService) {
        System.out.println("Digite o código do produto ou uma parte de seu nome ou descrição:");
        String search = sc.nextLine();

        List<Product> products = storeService.searchProducts(search);
        if (products.isEmpty()) {
            System.out.println("Nenhum produto encontrado");
            return null;
        }

        for (Product p : products) {
            int stock = p.getStock().getQuantity();
            String status = stock == 0 ? "[INDISPONÍVEL]" : "";
            System.out.printf("%d - %s (%s) - R$ %s %s\n",
                    p.getId(),
                    p.getName(),
                    p.getDescription(),
                    p.getStock().getPrice(),
                    status);
        }

        try {
            System.out.print("Digite o código do produto: ");
            int productId = Integer.parseInt(sc.nextLine());
            Product selectedProduct = products.stream()
                    .filter(p -> p.getId() == productId)
                    .findFirst().orElse(null);

            if (selectedProduct == null) {
                System.out.println("\nProduto não encontrado na lista");
                return null;
            }

            if (selectedProduct.getAvailableStock() == 0) {
                System.out.println("\nProduto indisponível");
                return null;
            }

            return selectedProduct;

        } catch (NumberFormatException e) {
            System.out.println("\nCódigo inválido");
            return null;
        }
    }

    private static void addProductToCart(Scanner sc, Product product, Cart cart) {
        try {
            System.out.print("Digite a quantidade desejada: ");
            int quantity = Integer.parseInt(sc.nextLine());

            if (quantity <= 0) {
                System.out.println("Quantidade deve ser maior que zero");
                return;
            }

            if (quantity > product.getAvailableStock()) {
                System.out.printf("Estoque insuficiente. Máximo disponível: %d\n", product.getAvailableStock());
                System.out.println("Deseja comprar essa quantidade? (s/n)");
                String option = sc.nextLine().trim();
                if (option.equalsIgnoreCase("s")) {
                    quantity = product.getAvailableStock();
                } else {
                    return;
                }
            }

            System.out.printf("\nValor total: %.2f", product.getUnitPrice().multiply(BigDecimal.valueOf(quantity)));
            System.out.println("\nConfirmar adição ao carrinho? (s/n)");
            String confirm = sc.nextLine().trim();

            if (confirm.equalsIgnoreCase("s")) {
                OrderItem item = new OrderItem(product, quantity, product.getUnitPrice());
                cart.addItem(item);
                System.out.println("Item adicionado ao carrinho!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Quantidade inválida");
        }
    }

    private static void showCart(Cart cart) {
        int index = 1;
        for (OrderItem item : cart.getItems()) {
            System.out.printf("[%d] %s - Quantidade: %d Valor: R$ %.2f\n",
                    index++,
                    item.getProductName(),
                    item.getQuantity(),
                    item.getTotalPrice());
        }
    }

    private static void finalizeOrder(Scanner sc, Customer customer, OrderService orderService) {
        Cart cart = customer.getCart();

        if (cart.isEmpty()) {
            System.out.println("Carrinho está vazio. Adicione produtos antes");
            return;
        }

        System.out.println("\n--- Resumo do pedido ---");
        showCart(cart);

        BigDecimal totalValueWithIcms = Order.calculateTotalValueWithIcms(cart.getItems());

        System.out.println("Total com ICMS: R$ " + totalValueWithIcms);
        System.out.println("Confirmar pedido? (s/n)");
        String confirm = sc.nextLine();

        if (!confirm.equalsIgnoreCase("s")) {
            return;
        }

        Order order = orderService.finalizeOrder(customer);
        if (order != null) {
            System.out.println("Pedido realizado com sucesso! Número: " + order.getNumber());
            cart.clear();
        }
    }

    private static void checkOrders(Scanner sc, Customer customer, OrderService orderService) {
        List<Order> orders = orderService.getOrdersByCustomer(customer);

        if (orders.isEmpty()) {
            System.out.println("\nVocê ainda não possui pedidos");
            return;
        }

        while (true) {
            System.out.println("\nEscolha a forma de consulta:");
            System.out.println("1. Por número do pedido");
            System.out.println("2. Por intervalo de datas");
            System.out.println("0. Voltar");
            String option = sc.nextLine();

            switch (option) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    try {
                        System.out.print("Digite o número do pedido: ");
                        int number = Integer.parseInt(sc.nextLine());
                        orders.stream().filter(o -> o.getNumber() == number).findFirst()
                                .ifPresentOrElse(CustomerMenu::printOrderDetails, () -> System.out.println("Pedido não encontrado"));
                    } catch (NumberFormatException e) {
                        System.out.println("Número inválido. Tente novamente");
                    }
                }
                case "2" -> {
                    try {
                        System.out.print("Data inicial (yyyy-MM-dd): ");
                        LocalDate start = LocalDate.parse(sc.nextLine());
                        System.out.print("Data final (yyyy-MM-dd): ");
                        LocalDate end = LocalDate.parse(sc.nextLine());

                        List<Order> filtered = orders.stream()
                                .filter(o -> !o.getOrderDate().isBefore(start) && !o.getOrderDate().isAfter(end)).toList();

                        if (filtered.isEmpty()) {
                            System.out.println("Nenhum pedido encontrado nesse intervalo");
                        } else {
                            filtered.forEach(CustomerMenu::printOrderDetails);
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de data inválido");
                    }
                }
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void printOrderDetails(Order order) {
        System.out.println("\n--- Pedido #" + order.getNumber());
        System.out.println("Data do pedido: " + order.getOrderDate());
        System.out.println("Data de entrega: " +
                (order.getDeliveryDate() != null ? order.getDeliveryDate() : "Ainda não enviado"));
        System.out.println("Status: " + order.getStatus());
        System.out.println("Itens:");
        for (OrderItem item : order.getItems()) {
            System.out.printf("- %s | Qtde: %d | Preço: R$ %.2f | Total: R$ %.2f\n",
                    item.getProductName(), item.getQuantity(), item.getUnitPrice(), item.getTotalPrice());
        }
        System.out.printf("Valor total (com ICMS): R$ %.2f\n", order.getTotalOrderValue());
    }
}
