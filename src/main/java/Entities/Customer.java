package Entities;

import Utils.ValidationUtils;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private String phoneNumber;
    private String creditCard;
    private Address address;
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, String email, String password,
                    String phoneNumber, String creditCard, Address address) {
        super(name, email, password);
        setPhoneNumber(phoneNumber);
        setCreditCard(creditCard);
        setAddress(address);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public Address getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        ValidationUtils.validateRequiredString(phoneNumber, "Telefone");
        if (!phoneNumber.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Telefone deve conter 10 ou 11 dígitos numéricos");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setCreditCard(String creditCard) {
        ValidationUtils.validateRequiredString(creditCard, "Cartão de crédito");
        if (!creditCard.matches("\\d{16}")) {
            throw new IllegalArgumentException("Cartão de crédito deve conter exatamente 16 dígitos númericos");
        }
        this.creditCard = creditCard;
    }

    public void setAddress(Address address) {
        if (address == null) {
            throw new IllegalArgumentException("Endereço não pode ser nulo");
        }
        this.address = address;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return (super.toString() + "\nTelefone: " + phoneNumber +
                "\nCartão de crédito: " + creditCard + "\nEndereço\n" + address);
    }
}
