package src;
public interface DiscountStrategy {

    double calculateDiscount(double totalPrice, int months);

    default String getStrategyName() {
        return "Стандартна стратегія знижки";
    }
}
