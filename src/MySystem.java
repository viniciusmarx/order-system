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

    public void login(String username, String password){

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
