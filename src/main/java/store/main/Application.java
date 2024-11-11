package store.main;

import store.checkout.CheckoutService;
import store.checkout.Receipt;
import store.customer.Customer;
import store.product.InventoryService;
import store.product.Product;
import store.product.ProductLoader;
import store.product.ProductOrder;
import store.promotion.Promotion;
import store.promotion.PromotionService;
import store.view.InputView;
import store.view.OutputView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ProductLoader productLoader = new ProductLoader();
        List<Product> products = productLoader.loadProducts();
        List<Promotion> promotions = productLoader.loadPromotions();

        InventoryService inventoryService = new InventoryService(products);
        PromotionService promotionService = new PromotionService(promotions);

        OutputView.printWelcomeMessage(products);

        while (true) {
            try {
                processOrder(inventoryService, promotionService, products);
                if (!isContinueShopping()) {
                    OutputView.printGoodbyeMessage();
                    break;
                }
                OutputView.printWelcomeMessage(products);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void processOrder(InventoryService inventoryService, PromotionService promotionService, List<Product> products) {
        String productOrderInput = InputView.readProductOrder();
        List<ProductOrder> orders = parseProductOrderInput(productOrderInput);

        Customer customer = createCustomer();
        CheckoutService checkoutService = new CheckoutService(inventoryService, promotionService);
        Receipt receipt = checkoutService.processPurchase(customer, orders, LocalDate.now());
        OutputView.printReceipt(receipt);
    }

    private static Customer createCustomer() {
        String membershipInput = InputView.readYesOrNo("멤버십 할인을 받으시겠습니까?");
        return new Customer(membershipInput.equalsIgnoreCase("Y"));
    }

    private static boolean isContinueShopping() {
        String continueInput = InputView.readYesOrNo("구매하고 싶은 다른 상품이 있나요?");
        return continueInput.equalsIgnoreCase("Y");
    }

    private static List<ProductOrder> parseProductOrderInput(String input) {
        List<ProductOrder> orders = new ArrayList<>();
        String[] orderParts = splitOrderInput(input);

        for (String orderPart : orderParts) {
            orders.add(createProductOrder(orderPart));
        }
        return orders;
    }

    private static String[] splitOrderInput(String input) {
        return input.substring(1, input.length() - 1).split("\\],\\[");
    }

    private static ProductOrder createProductOrder(String orderPart) {
        String[] details = orderPart.split("-");
        String productName = details[0].trim();
        int quantity = Integer.parseInt(details[1].trim());
        return new ProductOrder(productName, quantity);
    }
}
