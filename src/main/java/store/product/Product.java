package store.product;

import store.promotion.PromotionType;
import store.view.OutputView;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private PromotionType promotionType;

    public Product(String name, int price, int quantity, PromotionType promotionType) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionType = promotionType;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public PromotionType getPromotionType() {
        return promotionType;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void reduceQuantity(int amount) {
        if (amount > this.quantity) {
            throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 차감할 수 없습니다.");
        }
        this.quantity -= amount;
    }

    @Override
    public String toString() {
        if (quantity == 0) {
            return name + " " + OutputView.formatCurrency(price) + "원 재고 없음";
        }
        String promotionInfo = promotionType != null ? " " + promotionType : "";
        return name + " " + OutputView.formatCurrency(price) + "원 " + quantity + "개" + promotionInfo;
    }
}