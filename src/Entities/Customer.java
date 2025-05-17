package Entities;

public class Customer extends User {
    private String phoneNumber;
    private String creditCard;
    private Address address;

    public Customer(String name, String email, String password,
                    String phoneNumber, String creditCard, Address address){
        super(name, email, password);
        if (phoneNumber == null || phoneNumber.isEmpty() || 
            phoneNumber.length() > 10){System.out.println("Número de telefone inválido");}
        else if (creditCard == null || creditCard.isEmpty() || 
                 creditCard.length() < 16){System.out.println("Número de cartão de crédito inválido");}
        else if (address == null){System.out.println("Endereço não preenchido");}
        else {
            this.phoneNumber = phoneNumber;
            this.creditCard = creditCard;
            this.address = address;
        }
    }

    public String getPhoneNumber(){ return phoneNumber; }
    public String getCreditCard() { return creditCard; }
    public Address getAddress(){ return address; }

    public void setCreditCard(String creditCard) {
        if (creditCard == null || creditCard.isEmpty() || 
            creditCard.length() < 16){System.out.println("Número de cartão de crédito inválido");}
        else{this.creditCard = creditCard;}
    }

    @Override
    public String toString(){
        String info = super.toString();
        info += String.format("Telefone: %s \nCartão de crédito: %s \n", phoneNumber, creditCard);
        return info + "Endereço: \n" + address;
    }
}
