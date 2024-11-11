package store.product;

public class ProductOrder {
    private final String productName;
    private final int quantity;

    public ProductOrder(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }
}
