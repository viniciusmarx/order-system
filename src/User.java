public abstract class User {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;

    public User(String name, String email, String password, String phoneNumber, Address address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getPassword(){
        return password;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getAddress(){
        return address.toString();
    }

    public String toString(){
        return String.format("Nome: %s \nEmail: %s \nTelefone: %s \n", name, email, phoneNumber);
    }
}
