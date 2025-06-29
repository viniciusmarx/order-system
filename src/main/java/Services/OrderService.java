package Services;

import Entities.*;
import Persistence.StorePersistence;
import Repository.StoreRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final StoreRepository storeRepository;

    public OrderService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    private void registerOrder(Order order) {
        storeRepository.getStore().getOrders().add(order);
        storeRepository.save();
    }

    public Order finalizeOrder(Customer customer) {
        List<OrderItem> items = customer.getCart().getItems();

        List<OrderItem> invalidItems = validateStock(items);

        if (!invalidItems.isEmpty()) {
            System.out.println("Pedido não pode ser finalizado. Estoque insuficiente para:");
            for (OrderItem item : invalidItems) {
                Product p = getProductById(item.getProductId());
                System.out.printf("- %s (Disponível %d, Solicitado: %d)\n",
                        p.getName(), p.getAvailableStock(), item.getQuantity());
            }
            return null;
        }

        applyStockChanges(items);

        Order order = new Order(generateOrderNumber(), customer, new ArrayList<>(items), Order.calculateTotalValueWithIcms(items));

        registerOrder(order);

        return order;
    }

    private List<OrderItem> validateStock(List<OrderItem> orderItems) {
        List<OrderItem> invalidItems = new ArrayList<>();
        for (OrderItem item : orderItems) {
            Product p = getProductById(item.getProductId());
            if (p.getAvailableStock() < item.getQuantity()) {
                invalidItems.add(item);
            }
        }
        return invalidItems;
    }

    private Product getProductById(int id) {
        return storeRepository.getStore().getProducts().stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private void applyStockChanges(List<OrderItem> orderItems) {
        for (OrderItem item : orderItems) {
            Product p = getProductById(item.getProductId());
            int newQuantity = p.getAvailableStock() - item.getQuantity();
            p.getStock().setQuantity(newQuantity);
        }
    }

    private void restoreStock(List<OrderItem> items) {
        for (OrderItem item : items) {
            Product product = getProductById(item.getProductId());
            if (product != null) {
                int restoredQuantity = product.getAvailableStock() + item.getQuantity();
                product.getStock().setQuantity(restoredQuantity);
            }
        }
    }

    private int generateOrderNumber() {
        return storeRepository.getStore().getOrders().size() + 1;
    }

    public List<Order> getAllOrders() {
        return storeRepository.getStore().getOrders();
    }

    public List<Order> getOrdersByCustomer(Customer customer) {
        return storeRepository.getStore().getOrders().stream()
                .filter(o -> o.getCustomer().getEmail().equalsIgnoreCase(customer.getEmail()))
                .toList();
    }

    public void updateOrderStatus(Order order, OrderStatus newStatus) {
        order.setStatus(newStatus);

        if (newStatus == OrderStatus.SHIPPED) {
            order.setDeliveryDate(LocalDate.now());
        } else if (newStatus == OrderStatus.CANCELLED) {
            order.setDeliveryDate(null);
            restoreStock(order.getItems());
        }

        storeRepository.save();
    }
}
