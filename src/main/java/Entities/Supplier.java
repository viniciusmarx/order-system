package Entities;

import Utils.ValidationUtils;

public class Supplier {
    private static int nextId;
    private int id;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private Address address;

    public Supplier() {
        this.id = nextId++;
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

    public Address getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public static void setNextId(int nextId) {
        Supplier.nextId = nextId;
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
                "\nEndereço:\n" + address);
    }
}
