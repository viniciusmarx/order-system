package Entities;

import Utils.ValidationUtils;

public class Customer extends User {
    private String phoneNumber;
    private String creditCard;
    private Address address;

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
        if (phoneNumber.length() > 11) {
            throw new IllegalArgumentException("Número de telefone não pode ter mais que 11 digitos");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setCreditCard(String creditCard) {
        ValidationUtils.validateRequiredString(creditCard, "Cartão de crédito");
        if (creditCard.length() < 16) {
            throw new IllegalArgumentException("Número de cartão de crédito precisa ter 16 digitos");
        }
        this.creditCard = creditCard;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        String info = super.toString();
        info += String.format("Telefone: %s \nCartão de crédito: %s \n", phoneNumber, creditCard);
        return info + "Endereço: \n" + address;
    }
}
