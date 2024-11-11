package store.checkout;

import store.customer.Customer;
import store.product.InventoryService;
import store.product.Product;
import store.product.ProductOrder;
import store.promotion.Promotion;
import store.promotion.PromotionService;

import java.time.LocalDate;
import java.util.List;

public class CheckoutService {
    private final InventoryService inventoryService;
    private final PromotionService promotionService;

    public CheckoutService(InventoryService inventoryService, PromotionService promotionService) {
        this.inventoryService = inventoryService;
        this.promotionService = promotionService;
    }

    public Receipt processPurchase(Customer customer, List<ProductOrder> orders, LocalDate currentDate) {
        Receipt receipt = new Receipt();
        int totalPromotionDiscount = calculateTotalPromotionDiscount(orders, currentDate, receipt);
        int totalMembershipDiscount = calculateMembershipDiscount(customer, receipt, totalPromotionDiscount);

        receipt.applyPromotionDiscount(totalPromotionDiscount);
        receipt.applyMembershipDiscount(totalMembershipDiscount);
        receipt.calculateFinalAmount();
        return receipt;
    }

    private int calculateTotalPromotionDiscount(List<ProductOrder> orders, LocalDate currentDate, Receipt receipt) {
        int totalPromotionDiscount = 0;

        for (ProductOrder order : orders) {
            Product product = inventoryService.getProduct(order.getProductName());
            int quantity = order.getQuantity();

            if (product.getQuantity() < quantity) {
                throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }

            inventoryService.reduceStock(product.getName(), quantity);

            Promotion activePromotion = getActivePromotion(product, currentDate);
            if (activePromotion != null && quantity >= activePromotion.getBuyQuantity()) {
                int freeQuantity = calculateFreeQuantity(quantity, activePromotion);
                int discountAmount = freeQuantity * product.getPrice();
                receipt.addFreeItem(product.getName(), freeQuantity);
                totalPromotionDiscount += discountAmount;
            }

            int amount = calculateAmount(product, quantity);
            receipt.addPurchasedItem(product.getName(), quantity, amount);
        }

        return totalPromotionDiscount;
    }

    private Promotion getActivePromotion(Product product, LocalDate currentDate) {
        if (product.getPromotionType() == null) {
            return null;
        }
        return promotionService.getActivePromotion(product.getPromotionType().toString(), currentDate);
    }

    private int calculateFreeQuantity(int quantity, Promotion activePromotion) {
        return (quantity / activePromotion.getBuyQuantity()) * activePromotion.getGetQuantity();
    }

    private int calculateAmount(Product product, int quantity) {
        return quantity * product.getPrice();
    }

    private int calculateMembershipDiscount(Customer customer, Receipt receipt, int totalPromotionDiscount) {
        if (!customer.isMember()) {
            return 0;
        }
        int discountableAmount = receipt.getTotalAmount() - totalPromotionDiscount;
        return Math.min(8000, (int) (discountableAmount * 0.3));
    }
}