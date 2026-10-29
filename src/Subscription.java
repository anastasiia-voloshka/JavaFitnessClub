package src;

public sealed abstract class Subscription extends BaseEntity
        permits StandardSubscription {

    private final int months;
    private final double monthlyPrice;

    protected Subscription(int months, double monthlyPrice)
            throws InvalidSubscriptionException {
        this(
                EntityIdGenerator.nextId(),
                "Абонемент",
                months,
                monthlyPrice,
                false
        );
    }

    protected Subscription(
            int months,
            double monthlyPrice,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        this(
                EntityIdGenerator.nextId(),
                "Абонемент",
                months,
                monthlyPrice,
                showConstructorTrace
        );
    }

    protected Subscription(
            long id,
            String name,
            int months,
            double monthlyPrice,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        super(id, name);

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
            System.out.println(
                    "Subscription: конструктор базового класу"
            );
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
                "ID: %d%n"
                        + "Тип абонемента: %s%n"
                        + "Термін абонемента: %d міс.%n"
                        + "Ціна за місяць: %.2f грн%n"
                        + "Вартість без знижки: %.2f грн",
                getId(),
                getName(),
                months,
                monthlyPrice,
                getTotalPrice()
        );
    }
}