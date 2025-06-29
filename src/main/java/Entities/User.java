package Entities;

import Utils.ValidationUtils;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Customer.class, name = "customer"),
        @JsonSubTypes.Type(value = User.class, name = "user")
})
public class User {
    private String name;
    private String email;
    private String password;

    public User() {
    }

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
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido: deve conter '@' e '.'");
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

    @Override
    public String toString() {
        return "Nome: " + name + " Email: " + email;
    }
}
