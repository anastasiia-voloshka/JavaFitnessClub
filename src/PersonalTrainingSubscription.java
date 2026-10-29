package src;

import java.util.Objects;

public final class PersonalTrainingSubscription extends LongTermSubscription {

    private final int personalTrainingSessionsPerMonth;

    public PersonalTrainingSubscription(
            int months,
            double monthlyPrice,
            int personalTrainingSessionsPerMonth
    ) throws InvalidSubscriptionException {
        this(months, monthlyPrice, personalTrainingSessionsPerMonth, false);
    }

    public PersonalTrainingSubscription(
            int months,
            double monthlyPrice,
            int personalTrainingSessionsPerMonth,
            boolean showConstructorTrace
    ) throws InvalidSubscriptionException {
        super(months, monthlyPrice, 0.10, showConstructorTrace);

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
                    "PersonalTrainingSubscription: конструктор персонального плану"
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

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        PersonalTrainingSubscription other =
                (PersonalTrainingSubscription) object;
        return super.equals(object)
                && personalTrainingSessionsPerMonth
                        == other.personalTrainingSessionsPerMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                personalTrainingSessionsPerMonth
        );
    }
}
