public class Customer extends User{
    private String creditCard;

    public Customer(String name, String email, String phoneNumber, Address address, String creditCard){
        super(name, email, phoneNumber, address);
        this.creditCard = creditCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    @Override
    public String toString(){
        return super.toString() + getAddress();
    }
}
