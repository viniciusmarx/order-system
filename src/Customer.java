public class Customer{
    private String name;
    private String email;
    private String phoneNumber;
    private String creditCard;
    private Address address;

    public Customer(String name, String email,  String phoneNumber,
                    String creditCard, Address address){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        this.address = address;
    }

    public String getName(){ return name; }

    public String getEmail(){ return  email; }

    public String getPhoneNumber(){ return phoneNumber; }

    public String getCreditCard() { return creditCard; }

    public Address getAddress(){ return address; }

    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }
}
