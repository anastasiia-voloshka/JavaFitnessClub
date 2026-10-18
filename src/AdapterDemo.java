
package src;

public class AdapterDemo {

    public static void main(String[] args) {
        LegacyPaymentService legacyService =
                new LegacyPaymentService();

        PaymentProcessor paymentProcessor =
                new PaymentAdapter(legacyService);

        System.out.println("========== ADAPTER ==========");

        paymentProcessor.pay(5400.00);

        System.out.println("=============================");
    }
}
