import java.util.ArrayList;
import java.util.List;

public class Stock {
    private List<InventoryProduct> products;

    public Stock(){
        products = new ArrayList<>();
    }

    public void addProduct(Product newProduct, int quantity){
        for(InventoryProduct product : products){
            if(product.getProductId() == newProduct.getId()){
                product.setQuantity(product.getQuantity() + quantity);
                return;
            }
        }
        products.add(new InventoryProduct(newProduct, quantity));
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
