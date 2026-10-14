
public class SubscriptionCalculator {

    private DiscountStrategy strategy;

    public SubscriptionCalculator(DiscountStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(DiscountStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "Стратегія знижки не може бути null."
            );
        }

        this.strategy = strategy;
    }

    public double calculateDiscount(double totalPrice, int months) {
        return strategy.calculateDiscount(totalPrice, months);
    }

    public double calculateFinalPrice(double totalPrice, int months) {
        double discount = calculateDiscount(totalPrice, months);
        return totalPrice - discount;
    }

    public String getStrategyName() {
        return strategy.getStrategyName();
    }
}
