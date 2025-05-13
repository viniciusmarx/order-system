public class User {
    private String name;
    private String email;
    private String password;
    private Role role; // 0 = user, 1 = admin

    public enum Role{
        ADMIN, CUSTOMER
    }
    public User(String name, String email,
                String password, Role role){
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public String getName(){ return name; }

    public String getEmail(){ return email; }

    public String getPassword(){ return password; }

    public Role getRole(){ return role; }

    public String toString(){
        return String.format("Nome: %s \nEmail: %s \nTipo: %s\n", name, email, role);
    }
}
