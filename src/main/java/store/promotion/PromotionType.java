package store.promotion;

public enum PromotionType {
    BUY_N_GET_1_FREE("탄산2+1"),
    MD_RECOMMENDED("MD추천상품"),
    LIMITED_TIME_SALE("반짝할인");

    private final String description;

    PromotionType(String description) {
        this.description = description;
    }

    public static PromotionType fromDescription(String description) {
        for (PromotionType type : values()) {
            if (type.description.equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("[ERROR] 존재하지 않는 프로모션 타입: " + description);
    }

    @Override
    public String toString() {
        return description;
    }
}
