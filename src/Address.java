public class Address {
    private String street;
    private int number;
    private String complement;
    private String neighborhood;
    private String zipCode;
    private String city;
    private String state;

    public Address(String street, int number, String neighborhood, String zipCode,
                   String city, String state) {
        this.street = street;
        this.number = number;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public Address(String street, int number, String complement, String neighborhood, String zipCode,
                   String city, String state) {
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
    }

    public String toString(){
        return String.format("Street: %s \nNumber: %d \nComplement: %s \nNeighborhood: %s \nZipCode: %s " +
                             "\nCity: %s \nState: %s", street, neighborhood, city, state);
    }
}
