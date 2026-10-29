package src;

import java.util.Locale;

public final class SubscriptionFactory {

    private SubscriptionFactory() {
    }

    public static Subscription create(
            String type,
            int months,
            double monthlyPrice
    ) throws InvalidSubscriptionException {
        String normalizedType = type == null
                ? ""
                : type.trim().toLowerCase(Locale.ROOT);

        return switch (normalizedType) {
            case "standard", "regular", "звичайний", "стандартний" ->
                    new StandardSubscription(months, monthlyPrice);
            case "long-term", "довгостроковий" ->
                    new LongTermSubscription(months, monthlyPrice);
            case "personal-training", "персональний", "персональне тренування" ->
                    new PersonalTrainingSubscription(months, monthlyPrice, 4);
            default -> throw new IllegalArgumentException(
                    "Невідомий тип абонемента: " + type
                            + ". Доступні: standard, long-term, personal-training."
            );
        };
    }
}
