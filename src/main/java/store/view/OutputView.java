package store.view;

import store.checkout.Receipt;
import store.product.Product;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class OutputView {
    public static String formatCurrency(int amount) {
        NumberFormat formatter = NumberFormat.getInstance(Locale.KOREA);
        return formatter.format(amount);
    }

    public static void printWelcomeMessage(List<Product> products) {
        System.out.println("안녕하세요. W편의점입니다.");
        System.out.println("현재 보유하고 있는 상품입니다:");
        for (Product product : products) {
            if (product.getQuantity() > 0) {
                System.out.println("- " + product);
            } else {
                System.out.println("- " + product.getName() + " " + formatCurrency(product.getPrice()) + "원 재고 없음");
            }
        }
    }

    public static void printReceipt(Receipt receipt) {
        receipt.printReceipt();
    }

    public static void printGoodbyeMessage() {
        System.out.println("감사합니다. 이용해 주셔서 감사합니다.");
    }
}