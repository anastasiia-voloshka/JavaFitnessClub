
public class RegularDiscount extends AbstractDiscountStrategy {

    @Override
    public double calculateDiscount(double totalPrice, int months) {
        validateInput(totalPrice, months);
        return 0;
    }

    @Override
    public String getStrategyName() {
        return "Без знижки";
    }
}
