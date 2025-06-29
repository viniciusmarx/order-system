package Entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int number;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private OrderStatus status;
    private Customer customer;
    private List<OrderItem> items = new ArrayList<>();
    private BigDecimal totalOrderValue;
    private static final BigDecimal ICMS = new BigDecimal("0.17");

    public Order() {
    }

    public Order(int number, Customer customer, List<OrderItem> items, BigDecimal totalOrderValue) {
        this.number = number;
        this.customer = customer;
        this.items = items;
        this.totalOrderValue = totalOrderValue;
        this.orderDate = LocalDate.now();
        this.status = OrderStatus.PENDING;
        this.deliveryDate = null;
    }

    public static BigDecimal calculateTotalValueWithIcms(List<OrderItem> items) {
        BigDecimal subtotal = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal icms = subtotal.multiply(ICMS);
        return subtotal.add(icms).setScale(2, RoundingMode.HALF_UP);
    }

    public int getNumber() {
        return number;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public BigDecimal getTotalOrderValue() {
        return totalOrderValue;
    }
}
