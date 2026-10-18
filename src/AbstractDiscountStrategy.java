package src;
public abstract class AbstractDiscountStrategy
        implements DiscountStrategy {

    protected void validateInput(double totalPrice, int months) {
        if (!Double.isFinite(totalPrice) || totalPrice < 0) {
            throw new IllegalArgumentException(
                    "Загальна вартість повинна бути невід'ємним числом."
            );
        }

        if (months <= 0) {
            throw new IllegalArgumentException(
                    "Кількість місяців повинна бути більшою за 0."
            );
        }
    }
}
