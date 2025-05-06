public class User {
    private String username;
    private String password;
    private UserRole role;

    public enum UserRole{
        ADMIN, USER
    }
    public User(String username, String password, UserRole role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean authenticate(String password){
        return this.password.equals(password);
    }

    public boolean isAdmin(){
        return this.role == UserRole.ADMIN;
    }

    public String getUsername(){
        return username;
    }

    public UserRole getRole(){
        return this.role;
    }
}
