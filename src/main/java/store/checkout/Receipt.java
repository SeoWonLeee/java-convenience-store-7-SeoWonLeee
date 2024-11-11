package store.checkout;

import store.view.OutputView;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<String> purchasedItems;
    private final List<String> freeItems;
    private int totalAmount;
    private int promotionDiscount;
    private int membershipDiscount;
    private int finalAmount;

    public Receipt() {
        this.purchasedItems = new ArrayList<>();
        this.freeItems = new ArrayList<>();
        this.totalAmount = 0;
        this.promotionDiscount = 0;
        this.membershipDiscount = 0;
        this.finalAmount = 0;
    }

    public void addPurchasedItem(String name, int quantity, int amount) {
        purchasedItems.add(name + "\t" + quantity + "\t" + OutputView.formatCurrency(amount));
        totalAmount += amount;
    }

    public void addFreeItem(String name, int quantity) {
        freeItems.add(name + "\t" + quantity);
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void applyPromotionDiscount(int discount) {
        this.promotionDiscount = discount;
    }

    public void applyMembershipDiscount(int discount) {
        this.membershipDiscount = discount;
    }

    public void calculateFinalAmount() {
        finalAmount = totalAmount - promotionDiscount - membershipDiscount;
    }

    public void printReceipt() {
        System.out.println("==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");
        for (String item : purchasedItems) {
            System.out.println(item);
        }
        if (!freeItems.isEmpty()) {
            System.out.println("=============증\t정===============");
            for (String item : freeItems) {
                System.out.println(item);
            }
        }
        System.out.println("====================================");
        System.out.println("총구매액\t\t\t" + OutputView.formatCurrency(totalAmount));
        System.out.println("행사할인\t\t\t-" + OutputView.formatCurrency(promotionDiscount));
        System.out.println("멤버십할인\t\t-" + OutputView.formatCurrency(membershipDiscount));
        System.out.println("내실돈\t\t\t" + OutputView.formatCurrency(finalAmount));
    }
}