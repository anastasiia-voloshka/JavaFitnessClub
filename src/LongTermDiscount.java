package src;
public class LongTermDiscount extends AbstractDiscountStrategy
        implements SubscriptionBenefit {

    @Override
    public double calculateDiscount(double totalPrice, int months) {
        validateInput(totalPrice, months);

        if (months >= 6) {
            return totalPrice * 0.10;
        }

        return 0;
    }

    @Override
    public String getStrategyName() {
        return "Знижка 10% за абонемент від 6 місяців";
    }

    @Override
    public String getBenefitDescription() {
        return "Знижка 10% на абонемент від 6 місяців";
    }
}
