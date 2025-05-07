public class InventoryProduct {
    private Product product;
    private int quantity;

    public InventoryProduct(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public int getProductId(){
        return product.getId();
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int newQuantity){
        quantity = newQuantity;
    }
}
