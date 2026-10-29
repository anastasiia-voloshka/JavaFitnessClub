package src;

import java.util.Objects;

public sealed class LongTermSubscription extends StandardSubscription
        permits PersonalTrainingSubscription {

    private final double discountRate;

    public LongTermSubscription(int months, double monthlyPrice)
            throws InvalidSubscriptionException {
        this(months, monthlyPrice, 0.10, false);
    }

    public LongTermSubscription(
            int months,
            double monthlyPrice,
            double discountRate
    ) throws InvalidSubscriptionException {
        this(months, monthlyPrice, discountRate, false);
    }

    protected LongTermSubscription(
            int months,
            double monthlyPrice,
            double discountRate,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        super(months, monthlyPrice, 4, showConstructorTrace);

        if (!Double.isFinite(discountRate)
                || discountRate < 0
                || discountRate > 1) {
            throw new InvalidSubscriptionException(
                    "Розмір знижки має бути від 0 до 1.",
                    discountRate
            );
        }

        this.discountRate = discountRate;

        if (showConstructorTrace) {
            System.out.println("LongTermSubscription: конструктор довгострокового плану");
        }
    }

    public final double getDiscountRate() {
        return discountRate;
    }

    @Override
    public double calculateDiscount() {
        if (getMonths() < 6) {
            return 0;
        }

        return getTotalPrice() * discountRate;
    }

    @Override
    public String getPlanName() {
        return "Довгостроковий";
    }

    @Override
    public String getBenefitsDescription() {
        return String.format(
                "Доступ до зали, групові заняття та знижка %.0f%% для терміну від 6 місяців.",
                discountRate * 100
        );
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(
                        "%nЗнижка за термін від 6 місяців: %.0f%%",
                        discountRate * 100
                );
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        LongTermSubscription other = (LongTermSubscription) object;
        return super.equals(object)
                && Double.compare(discountRate, other.discountRate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), discountRate);
    }
}
