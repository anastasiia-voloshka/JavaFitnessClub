
package src;

public class PaymentAdapter implements PaymentProcessor {

    private final LegacyPaymentService legacyService;

    public PaymentAdapter(LegacyPaymentService legacyService) {
        if (legacyService == null) {
            throw new IllegalArgumentException(
                    "Сервіс оплати не може бути null."
            );
        }

        this.legacyService = legacyService;
    }

    @Override
    public void pay(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Сума оплати повинна бути більшою за 0."
            );
        }

        double kopecks = amount * 100;

        if (kopecks > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "Сума оплати перевищує допустиме значення."
            );
        }

        int amountInKopecks = (int) Math.round(kopecks);

        legacyService.makePayment(amountInKopecks);
    }
}
