package src;

public sealed class StandardSubscription extends Subscription
        permits LongTermSubscription {

    private final int groupClassesPerMonth;

    public StandardSubscription(int months, double monthlyPrice)
            throws InvalidSubscriptionException {
        this(
                EntityIdGenerator.nextId(),
                "Стандартний",
                months,
                monthlyPrice,
                4,
                false
        );
    }

    public StandardSubscription(
            int months,
            double monthlyPrice,
            int groupClassesPerMonth
    ) throws InvalidSubscriptionException {
        this(
                EntityIdGenerator.nextId(),
                "Стандартний",
                months,
                monthlyPrice,
                groupClassesPerMonth,
                false
        );
    }

    protected StandardSubscription(
            int months,
            double monthlyPrice,
            int groupClassesPerMonth,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        this(
                EntityIdGenerator.nextId(),
                "Стандартний",
                months,
                monthlyPrice,
                groupClassesPerMonth,
                showConstructorTrace
        );
    }

    public StandardSubscription(
            long id,
            String name,
            int months,
            double monthlyPrice,
            int groupClassesPerMonth
    ) throws InvalidSubscriptionException {
        this(
                id,
                name,
                months,
                monthlyPrice,
                groupClassesPerMonth,
                false
        );
    }

    protected StandardSubscription(
            long id,
            String name,
            int months,
            double monthlyPrice,
            int groupClassesPerMonth,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        super(id, name, months, monthlyPrice, showConstructorTrace);

        if (groupClassesPerMonth < 0) {
            throw new InvalidSubscriptionException(
                    "Кількість групових занять не може бути від'ємною.",
                    groupClassesPerMonth
            );
        }

        this.groupClassesPerMonth = groupClassesPerMonth;

        if (showConstructorTrace) {
            System.out.println(
                    "StandardSubscription: конструктор стандартного плану"
            );
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
}