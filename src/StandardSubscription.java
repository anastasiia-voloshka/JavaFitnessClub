package src;

import java.util.Objects;

public sealed class StandardSubscription extends Subscription
        permits LongTermSubscription {

    private final int groupClassesPerMonth;

    public StandardSubscription(int months, double monthlyPrice)
            throws InvalidSubscriptionException {
        this(months, monthlyPrice, 4, false);
    }

    public StandardSubscription(
            int months,
            double monthlyPrice,
            int groupClassesPerMonth
    ) throws InvalidSubscriptionException {
        this(months, monthlyPrice, groupClassesPerMonth, false);
    }

    protected StandardSubscription(
            int months,
            double monthlyPrice,
            int groupClassesPerMonth,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        super(months, monthlyPrice, showConstructorTrace);

        if (groupClassesPerMonth < 0) {
            throw new InvalidSubscriptionException(
                    "Кількість групових занять не може бути від'ємною.",
                    groupClassesPerMonth
            );
        }

        this.groupClassesPerMonth = groupClassesPerMonth;

        if (showConstructorTrace) {
            System.out.println("StandardSubscription: конструктор стандартного плану");
        }
    }

    public final int getGroupClassesPerMonth() {
        return groupClassesPerMonth;
    }

    @Override
    public double calculateDiscount() {
        return 0;
    }

    @Override
    public String getPlanName() {
        return "Стандартний";
    }

    @Override
    public String getBenefitsDescription() {
        return String.format(
                "Доступ до тренажерної зали та %d групових занять щомісяця.",
                groupClassesPerMonth
        );
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(
                        "%nГрупові заняття: %d на місяць",
                        groupClassesPerMonth
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

        StandardSubscription other = (StandardSubscription) object;
        return super.equals(object)
                && groupClassesPerMonth == other.groupClassesPerMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), groupClassesPerMonth);
    }
}
