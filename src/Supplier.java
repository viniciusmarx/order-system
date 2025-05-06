public class Supplier {
    private static int idCount = 1;
    private int id;
    private String name;
    private String cnpj;
    private Address address;

    public Supplier(String name, String cnpj, Address address){
        this.id = idCount++;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
    }
}
