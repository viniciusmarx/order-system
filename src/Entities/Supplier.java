package Entities;

import Utils.ValidationUtils;

public class Supplier {
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private Address address;

    public Supplier() {
    }

    public Supplier(String name, String description, String phoneNumber, String email, Address address) {
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress(){
        return address;
    }

    public void setName(String name) {
        ValidationUtils.validateRequiredString(name, "Nome");
        this.name = name;
    }

    public void setDescription(String description) {
        ValidationUtils.validateRequiredString(description, "Descrição");
        this.description = description;
    }

    public void setPhoneNumber(String phoneNumber) {
        ValidationUtils.validateRequiredString(phoneNumber, "Telefone");
        if (!phoneNumber.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Telefone deve conter 10 ou 11 dígitos númericos");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        ValidationUtils.validateRequiredString(email, "Email");
        if (!email.contains(".") || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return ("Nome: " + name +
                "\nDescrição: " + description +
                "\nTelefone: " + phoneNumber +
                "\nEmail: " + email +
                "\n - Endereço\n" + address);
    }
}
