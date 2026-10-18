
package src;

public class LegacyPaymentService {

    public void makePayment(int amountInKopecks) {
        System.out.printf(
                "Оплату оброблено: %.2f грн%n",
                amountInKopecks / 100.0
        );
    }
}
