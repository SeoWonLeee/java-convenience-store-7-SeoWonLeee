package store.promotion;

import store.product.Product;

import java.time.LocalDate;
import java.util.List;

public class PromotionService {
    private final List<Promotion> promotions;

    public PromotionService(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Promotion getActivePromotion(String name, LocalDate currentDate) {
        for (Promotion promotion : promotions) {
            if (promotion.getName().equalsIgnoreCase(name) && promotion.isActive(currentDate)) {
                return promotion;
            }
        }
        return null;
    }

    public boolean isPromotionApplicable(Product product, LocalDate currentDate) {
        return product.getPromotionType() != null &&
                getActivePromotion(product.getPromotionType().toString(), currentDate) != null;
    }
}