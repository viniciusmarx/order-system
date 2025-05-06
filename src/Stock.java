import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<InventoryProduct> products;

    public Stock(){
        products = new ArrayList<>();
    }

    public void addProduct(int productId, int quantity){
        for(InventoryProduct product : products){
            if(product.getProductId() == productId){
                product.setQuantity(product.getQuantity() + quantity);
                return;
            }
        }
        products.add(new InventoryProduct(productId, quantity));
    }

    public int getQuantity(int productId){
        for(InventoryProduct product : products){
            if(product.getProductId() == productId){
                return product.getQuantity();
            }
        }
        return 0;
    }
}
