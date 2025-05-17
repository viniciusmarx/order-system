package Entities;

public class Address {
    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;

    public Address(String street, int number, String complement, String neighborhood, 
                   String zipCode, String city, String state) {
        if (street == null || street.isEmpty()) {System.out.println("Rua preenchida incorretamente");} 
        else if (number <= 0) {System.out.println("Número preenchido incorretamente");} 
        else if (neighborhood == null || neighborhood.isEmpty()) {System.out.println("Bairro preenchido incorretamente");} 
        else if (zipCode == null || zipCode.isEmpty() || zipCode.length() != 8) {System.out.println("CEP preenchido incorretamente");} 
        else if (city == null || city.isEmpty()) {System.out.println("Cidade preenchida incorretamente");} 
        else if (state == null || state.isEmpty()) {System.out.println("Estado preenchido incorretamente");}
        else{
            this.street = street;
            this.number = number;
            this.complement = complement;
            this.neighborhood = neighborhood;
            this.zipCode = zipCode;
            this.city = city;
            this.state = state;
        }
    }

    public String getStreet() {return street;}
    public int getNumber() {return number;}
    public String getComplement() {return complement;}
    public String getNeighborhood() {return neighborhood;}
    public String getZipCode() {return zipCode;}
    public String getCity() {return city;}
    public String getState() {return state;}

    @Override
    public String toString(){
        return String.format("Rua: %s \nNúmero: %d \nComplemento: %s \nBairro: %s \nCEP: %s " +
                             "\nCidade: %s \nEstado: %s", street, number, complement, neighborhood, zipCode, city, state);
    }
}
