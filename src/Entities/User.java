package Entities;

import Utils.ValidationUtils;

public class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        ValidationUtils.validateRequiredString(name, "Nome");
        this.name = name;
    }

    public void setEmail(String email) {
        ValidationUtils.validateRequiredString(email, "Email");
        if (!email.endsWith(".com")) {
            throw new IllegalArgumentException("Email preenchido incorretamente");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("Email precisa conter @");
        }
        this.email = email;
    }

    public void setPassword(String password) {
        ValidationUtils.validateRequiredString(password, "Senha");
        if (password.length() < 6) {
            throw new IllegalArgumentException("Senha precisa ter no mínimo 6 digitos");
        }
        this.password = password;
    }

    public String toString() {
        return String.format("Nome: %s \nEmail: %s \n", name, email);
    }
}
