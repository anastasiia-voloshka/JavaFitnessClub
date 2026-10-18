
package src;

public final class DiscountStrategyFactory {

    private DiscountStrategyFactory() {
    }

    public static DiscountStrategy create(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Тип стратегії знижки не може бути порожнім."
            );
        }

        switch (type.trim().toLowerCase()) {
            case "regular":
                return new RegularDiscount();

            case "long-term":
                return new LongTermDiscount();

            default:
                throw new IllegalArgumentException(
                        "Невідомий тип стратегії знижки: " + type
                );
        }
    }
}
