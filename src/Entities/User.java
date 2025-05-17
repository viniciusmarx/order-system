package Entities;

public class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email,
                String password){

        if(name == null || name.isEmpty()){System.out.println("Nome preenchido incorretamente");}
        else if (email == null || email.isEmpty() || !(email.endsWith(".com") || email.contains("@"))){System.out.println("Email preenchido incorretamente");}
        else if (password == null || password.isEmpty() || password.length() < 6){System.out.println("Senha preenchida incorretamente");}
        else{
            this.name = name;
            this.email = email;
            this.password = password;
            System.out.println("Conta criada com sucesso!");
        }
    }
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getPassword(){ return password; }

    public String toString(){
        return String.format("Nome: %s \nEmail: %s \n", name, email);
    }
}
