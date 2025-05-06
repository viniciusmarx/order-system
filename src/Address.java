public class Address {
    private String street;
    private String neighborhood;
    private String city;
    private String state;

    public Address(String street, String neighborhood, String city, String state) {
        this.street = street;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public String toString(){
        return String.format("Street: %s \nNeighborhood: %s \nCity: %s \nState: %s", street, neighborhood, city, state);
    }
}
