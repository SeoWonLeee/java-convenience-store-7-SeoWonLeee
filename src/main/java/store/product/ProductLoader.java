package store.product;

import store.promotion.Promotion;
import store.promotion.PromotionType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductLoader {
    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";
    private static final String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";

    public List<Product> loadProducts() {
        List<Product> products = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PRODUCT_FILE_PATH))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int price = Integer.parseInt(parts[1].trim());
                int quantity = Integer.parseInt(parts[2].trim());

                PromotionType promotionType;
                if (parts[3].trim().equals("null")) {
                    promotionType = null;
                } else {
                    promotionType = PromotionType.fromDescription(parts[3].trim());
                }

                products.add(new Product(name, price, quantity, promotionType));
            }
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 상품 파일을 로드하는 중 오류가 발생했습니다.", e);
        }
        return products;
    }

    public List<Promotion> loadPromotions() {
        List<Promotion> promotions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PROMOTION_FILE_PATH))) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                int buyQuantity = Integer.parseInt(parts[1].trim());
                int getQuantity = Integer.parseInt(parts[2].trim());
                LocalDate startDate = LocalDate.parse(parts[3].trim());
                LocalDate endDate = LocalDate.parse(parts[4].trim());
                promotions.add(new Promotion(name, buyQuantity, getQuantity, startDate, endDate));
            }
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 프로모션 파일을 로드하는 중 오류가 발생했습니다.", e);
        }
        return promotions;
    }
}
