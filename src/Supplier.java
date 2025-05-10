public class Supplier extends User {
    private String cnpj;
    private String description;

    public Supplier(String name, String email, String password, 
                    String phoneNumber, Address address, String cnpj, String description){
        super(name, email, password, phoneNumber, address);
        this.cnpj = cnpj;
        this.description = description;
    }

    public Supplier(String name, String email, String password, 
                    String phoneNumber, Address address, String cnpj){
        super(name, email, password, phoneNumber, address);
        this.cnpj = cnpj;
    }

    @Override
    public String toString(){
        String string = super.toString();
        return string + String.format("CNPJ: %s \n Descrição: %s \n", cnpj, description) + getAddress();
    }
}
