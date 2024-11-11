package store.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {
    private final Map<String, Product> productInventory = new HashMap<>();

    public InventoryService(List<Product> products) {
        for (Product product : products) {
            productInventory.put(product.getName(), product);
        }
    }

    public Product getProduct(String name) {
        if (!productInventory.containsKey(name)) {
            throw new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다.");
        }
        return productInventory.get(name);
    }

    public void reduceStock(String name, int quantity) {
        Product product = getProduct(name);
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
        }
        product.reduceQuantity(quantity);
    }
}
