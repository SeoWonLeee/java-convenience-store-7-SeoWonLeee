package store.validation;

import java.util.regex.Pattern;

public class InputValidation {
    private static final Pattern PRODUCT_ORDER_PATTERN = Pattern.compile("\\[\\p{L}+\\-\\d+\\](,\\[\\p{L}+\\-\\d+\\])*");

    public static void validateProductOrderFormat(String input) {
        if (!PRODUCT_ORDER_PATTERN.matcher(input).matches()) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    public static void validateYesOrNo(String input) {
        if (!input.equalsIgnoreCase("Y") && !input.equalsIgnoreCase("N")) {
            throw new IllegalArgumentException("[ERROR] Y 또는 N으로 입력해 주세요.");
        }
    }
}
