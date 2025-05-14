public class Customer extends User{
    private String phoneNumber;
    private String creditCard;
    private Address address;

    public Customer(String name, String email, String password,
                    String phoneNumber, String creditCard, Address address){
        super(name, email, password);
        this.phoneNumber = phoneNumber;
        this.creditCard = creditCard;
        this.address = address;
    }

    public String getPhoneNumber(){ return phoneNumber; }

    public String getCreditCard() { return creditCard; }

    public Address getAddress(){ return address; }

    public void setCreditCard(String creditCard) { this.creditCard = creditCard; }
}
