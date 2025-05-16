package Entities;

public class Supplier{
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private Address address;

    public Supplier(String name, String description, String phoneNumber, String email, Address address){
        this.name = name;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String getName(){ return name; }

    public String getDescription(){ return description; }

    public String getPhoneNumber(){ return phoneNumber; }

    public String getEmail(){ return email; }

    public void setName(String name){ this.name = name; }

    public void setDescription(String description){ this.description = description; }

    public void setPhoneNumber(String phoneNumber){ this.phoneNumber = phoneNumber; }

    public void setEmail(String email){ this.email = email; }

    @Override
    public String toString(){
        String info = String.format("Nome: %s \nDescrição: %s \nTelefone: %s \nEmail: %s \n", name, description, phoneNumber, email);
        return info + "Endereço: " + address;
    }
}
