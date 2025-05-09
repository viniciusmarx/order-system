import java.util.ArrayList;
import java.util.List;

public class MySystem {
    private List<User> users;
    private List<Supplier> suppliers;
    private List<Product> products;
    private Stock stock;

    public MySystem(){
        users = new ArrayList<>();
        suppliers = new ArrayList<>();
        products = new ArrayList<>();
        stock = new Stock();
    }

    public Boolean login(String username, String password){
        for(User user : users){ // ajustar user quando trocar de lista para array
            if(user.getEmail().equals(username) && 
               user.getPassword().equals(password))
            {
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Invalid username or password.");
        return false;
    }

    public void showMenu(User user){

    }

    public void registerSupplier(Supplier supplier){
        suppliers.add(supplier);
    }

    public void registerProduct(Product product){
        products.add(product);
    }
}
