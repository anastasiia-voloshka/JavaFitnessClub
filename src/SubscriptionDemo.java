package src;

import java.util.List;

public final class SubscriptionDemo {

    private SubscriptionDemo() {
    }

    public static void main(String[] args)
            throws InvalidSubscriptionException {
        run();
    }

    public static void run() throws InvalidSubscriptionException {
        System.out.println("\n========== НАСЛІДУВАННЯ ТА ПОЛІМОРФІЗМ ==========");

        List<Subscription> subscriptions = List.of(
                new StandardSubscription(3, 1200),
                new LongTermSubscription(12, 1200),
                new PersonalTrainingSubscription(12, 1800, 4)
        );

        for (Subscription subscription : subscriptions) {
            System.out.println("План: " + subscription.getPlanName());
            System.out.println("Переваги: " + subscription.getBenefitsDescription());
            System.out.printf(
                    "Знижка: %.2f грн; до сплати: %.2f грн%n",
                    subscription.calculateDiscount(),
                    subscription.getFinalPrice()
            );
            System.out.println(subscription);
            System.out.println("---------------------------------");
        }

        Subscription personalPlan = subscriptions.get(2);
        if (personalPlan instanceof PersonalTrainingSubscription personal) {
            System.out.println(
                    "Специфічний метод підкласу: "
                            + personal.getPersonalTrainingSessionsPerMonth()
                            + " персональних тренувань на місяць."
            );
        }

        System.out.println("\n========== SEALED SWITCH ==========");
        for (Subscription subscription : subscriptions) {
            System.out.println(describeByType(subscription));
        }

        System.out.println("\n========== EQUALS ТА HASHCODE ==========");
        Subscription baseReference = new StandardSubscription(12, 1200);
        Subscription subclassReference = new LongTermSubscription(12, 1200);
        System.out.println(
                "StandardSubscription.equals(LongTermSubscription): "
                        + baseReference.equals(subclassReference)
        );
        System.out.println(
                "LongTermSubscription.equals(StandardSubscription): "
                        + subclassReference.equals(baseReference)
        );

        LongTermSubscription first = new LongTermSubscription(12, 1200);
        LongTermSubscription second = new LongTermSubscription(12, 1200);
        System.out.println("Однакові довгострокові плани рівні: " + first.equals(second));
        System.out.println("Їхні hashCode однакові: " + (first.hashCode() == second.hashCode()));

        System.out.println("\n========== ЛАНЦЮЖОК КОНСТРУКТОРІВ ==========");
        System.out.println("Створюється PersonalTrainingSubscription:");
        new PersonalTrainingSubscription(12, 1800, 4, true);
        System.out.println(
                "Порядок: Subscription → StandardSubscription → "
                        + "LongTermSubscription → PersonalTrainingSubscription."
        );
    }

    private static String describeByType(Subscription subscription) {
        return switch (subscription) {
            case PersonalTrainingSubscription personal ->
                    "Персональний план: "
                            + personal.getPersonalTrainingSessionsPerMonth()
                            + " тренувань на місяць.";
            case LongTermSubscription longTerm ->
                    "Довгостроковий план: знижка "
                            + (longTerm.getDiscountRate() * 100)
                            + "% від 6 місяців.";
            case StandardSubscription standard ->
                    "Стандартний план: "
                            + standard.getGroupClassesPerMonth()
                            + " групових занять на місяць.";
        };
    }
}
