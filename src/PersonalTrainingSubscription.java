package src;

public final class PersonalTrainingSubscription
        extends LongTermSubscription {

    private final int personalTrainingSessionsPerMonth;

    public PersonalTrainingSubscription(
            int months,
            double monthlyPrice,
            int personalTrainingSessionsPerMonth
    ) throws InvalidSubscriptionException {
        this(
                months,
                monthlyPrice,
                personalTrainingSessionsPerMonth,
                false
        );
    }

    public PersonalTrainingSubscription(
            int months,
            double monthlyPrice,
            int personalTrainingSessionsPerMonth,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        this(
                EntityIdGenerator.nextId(),
                "Персональне тренування",
                months,
                monthlyPrice,
                personalTrainingSessionsPerMonth,
                showConstructorTrace
        );
    }

    public PersonalTrainingSubscription(
            long id,
            String name,
            int months,
            double monthlyPrice,
            int personalTrainingSessionsPerMonth
    ) throws InvalidSubscriptionException {
        this(
                id,
                name,
                months,
                monthlyPrice,
                personalTrainingSessionsPerMonth,
                false
        );
    }

    public PersonalTrainingSubscription(
            long id,
            String name,
            int months,
            double monthlyPrice,
            int personalTrainingSessionsPerMonth,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        super(
                id,
                name,
                months,
                monthlyPrice,
                0.10,
                showConstructorTrace
        );

        if (personalTrainingSessionsPerMonth <= 0) {
            throw new InvalidSubscriptionException(
                    "Кількість персональних тренувань має бути більшою за 0.",
                    personalTrainingSessionsPerMonth
            );
        }

        this.personalTrainingSessionsPerMonth =
                personalTrainingSessionsPerMonth;

        if (showConstructorTrace) {
            System.out.println(
                    "PersonalTrainingSubscription: "
                            + "конструктор персонального плану"
            );
        }
    }

    public int getPersonalTrainingSessionsPerMonth() {
        return personalTrainingSessionsPerMonth;
    }

    @Override
    public double calculateDiscount() {
        return super.calculateDiscount();
    }

    @Override
    public String getPlanName() {
        return "Персональне тренування";
    }

    @Override
    public String getBenefitsDescription() {
        return super.getBenefitsDescription()
                + String.format(
                        " Додатково: %d персональних тренувань щомісяця.",
                        personalTrainingSessionsPerMonth
                );
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(
                        "%nПерсональні тренування: %d на місяць",
                        personalTrainingSessionsPerMonth
                );
    }
}