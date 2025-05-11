public abstract class User {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private Address address;
    private int role; // 0 = user, 1 = admin

    public User(String name, String email, String password, 
                String phoneNumber, Address address){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public User(String name, String email, String password, 
                String phoneNumber, Address address, int role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
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

    public int getRole(){
        return this.role == 0 ? 0 : 1;
    }

    public String toString(){
        return String.format("Nome: %s \nEmail: %s \nTelefone: %s \n", name, email, phoneNumber);
    }
}
