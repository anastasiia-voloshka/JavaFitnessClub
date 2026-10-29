package src;

import java.util.Objects;

public sealed abstract class Subscription permits StandardSubscription {

    private final int months;
    private final double monthlyPrice;

    protected Subscription(int months, double monthlyPrice)
            throws InvalidSubscriptionException {
        this(months, monthlyPrice, false);
    }

    protected Subscription(
            int months,
            double monthlyPrice,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        if (months <= 0) {
            throw new InvalidSubscriptionException(
                    "Кількість місяців повинна бути більшою за 0.",
                    months
            );
        }

        if (!Double.isFinite(monthlyPrice) || monthlyPrice <= 0) {
            throw new InvalidSubscriptionException(
                    "Вартість одного місяця повинна бути більшою за 0.",
                    monthlyPrice
            );
        }

        this.months = months;
        this.monthlyPrice = monthlyPrice;

        if (showConstructorTrace) {
            System.out.println("Subscription: конструктор базового класу");
        }
    }

    public final int getMonths() {
        return months;
    }

    public final double getMonthlyPrice() {
        return monthlyPrice;
    }

    public final double getTotalPrice() {
        return monthlyPrice * months;
    }

    public final double getFinalPrice() {
        return getTotalPrice() - calculateDiscount();
    }

    public abstract double calculateDiscount();

    public abstract String getPlanName();

    public abstract String getBenefitsDescription();

    @Override
    public String toString() {
        return String.format(
                "Термін абонемента: %d міс.%n"
                        + "Ціна за місяць: %.2f грн%n"
                        + "Вартість без знижки: %.2f грн",
                months,
                monthlyPrice,
                getTotalPrice()
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

        Subscription other = (Subscription) object;
        return months == other.months
                && Double.compare(monthlyPrice, other.monthlyPrice) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), months, monthlyPrice);
    }
}
