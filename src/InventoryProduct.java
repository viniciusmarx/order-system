public class InventoryProduct {
    private int productId;
    private int quantity;

    public InventoryProduct(int productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId(){
        return productId;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int newQuantity){
        quantity = newQuantity;
    }
}
