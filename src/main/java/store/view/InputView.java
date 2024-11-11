package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.validation.InputValidation;

public class InputView {

    public static String readProductOrder() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();
        InputValidation.validateProductOrderFormat(input);
        return input;
    }

    public static String readYesOrNo(String message) {
        System.out.println(message + " (Y/N)");
        String input = Console.readLine();
        InputValidation.validateYesOrNo(input);
        return input;
    }
}
