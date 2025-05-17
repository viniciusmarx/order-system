package Entities;

public class Supplier{
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private Address address;

    public Supplier(String name, String description, String phoneNumber, String email, Address address){

        if( name == null || name.isEmpty() && description == null || description.isEmpty() &&
            phoneNumber == null || phoneNumber.isEmpty() && email == null || email.isEmpty() || 
            address == null ){
            System.out.println("Um ou mais campos estão vazios");
           }
        else{
            this.name = name;
            this.description = description;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.address = address;
        }
    }

    public String getName(){ return name; }

    public String getDescription(){ return description; }

    public String getPhoneNumber(){ return phoneNumber; }

    public String getEmail(){ return email; }

    public void setName(String name){ 
        if (name == null || (name.isEmpty())){
            System.out.println("O nome deve ser preenchido");
        }
        else
        {
            this.name = name;
        }
    }

    public void setDescription(String description){ 
        if (description == null || description.isEmpty()){
            System.out.println("Descrição não pode ser vazia");
        }
        else
        {
            this.description = description;
        }
    }

    public void setPhoneNumber(String phoneNumber){ 
        if (phoneNumber == null || phoneNumber.isEmpty() &&
            phoneNumber.length() < 10){
            System.out.println("Numero de telefone inválido");
        }
        else
        {
            this.phoneNumber = phoneNumber;
        }
    }

    public void setEmail(String email){ this.email = email; }

    @Override
    public String toString(){
        String info = String.format("Nome: %s \nDescrição: %s \nTelefone: %s \nEmail: %s \n", name, description, phoneNumber, email);
        return info + "Endereço: " + address;
    }
}
