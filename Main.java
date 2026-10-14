
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть кількість клієнтів: ");
            int n = readInt(scanner);

            Client[] clients = new Client[n];

            for (int i = 0; i < n; i++) {
                System.out.println();
                System.out.println("Клієнт №" + (i + 1));

                System.out.print("Введіть ім'я: ");
                String name = scanner.nextLine();

                System.out.print("Введіть прізвище: ");
                String surname = scanner.nextLine();

                System.out.print("Введіть вік: ");
                int age = readInt(scanner);

                System.out.print("Введіть тип абонемента: ");
                String subscriptionType = scanner.nextLine();

                System.out.print("Введіть кількість місяців: ");
                int months = readInt(scanner);

                System.out.print("Введіть вартість одного місяця: ");
                double monthlyPrice = readDouble(scanner);

                clients[i] = new Client(
                        name,
                        surname,
                        age,
                        subscriptionType,
                        months,
                        monthlyPrice
                );
            }

            System.out.println("\n========== УСІ КЛІЄНТИ ==========");

            for (Client client : clients) {
                System.out.println(client);
                System.out.println("---------------------------------");
            }

            int count = 0;

            for (Client client : clients) {
                if (client.months >= 6) {
                    count++;
                }
            }

            System.out.println(
                    "Кількість клієнтів з абонементом від 6 місяців: "
                            + count
            );

            // Сортування за вартістю одного місяця
            for (int i = 0; i < clients.length - 1; i++) {
                for (int j = 0; j < clients.length - 1 - i; j++) {
                    if (clients[j].monthlyPrice
                            > clients[j + 1].monthlyPrice) {
                        Client temp = clients[j];
                        clients[j] = clients[j + 1];
                        clients[j + 1] = temp;
                    }
                }
            }

            System.out.println("\n========== ПІСЛЯ СОРТУВАННЯ ==========");

            for (Client client : clients) {
                System.out.println(client);
                System.out.println("---------------------------------");
            }





            System.out.println("\n========== ПОШУК КЛІЄНТА ==========");

            System.out.print("Введіть ім'я для пошуку: ");
            String searchName = scanner.nextLine();

            System.out.print("Введіть прізвище для пошуку: ");
            String searchSurname = scanner.nextLine();

            boolean found = false;

            for (Client client : clients) {
                if (client.name.equalsIgnoreCase(searchName)
                        && client.surname.equalsIgnoreCase(searchSurname)) {
                    System.out.println("\nКлієнта знайдено:");
                    System.out.println(client);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Клієнта не знайдено.");
            }

            // Рівень 1: поліморфізм через масив інтерфейсів
            System.out.println("\n========== ПОЛІМОРФІЗМ ==========");

            DiscountStrategy[] strategies = {
                    new RegularDiscount(),
                    new LongTermDiscount()
            };

            double totalPrice = 6000;
            int months = 6;

            for (DiscountStrategy strategy : strategies) {
                double discount =
                        strategy.calculateDiscount(totalPrice, months);

                System.out.println(
                        "Стратегія: " + strategy.getStrategyName()
                );
                System.out.printf("Знижка: %.2f грн%n", discount);
                System.out.printf(
                        "До сплати: %.2f грн%n",
                        totalPrice - discount
                );
                System.out.println("---------------------------------");
            }






            System.out.println("\n========== DEFAULT-МЕТОД ==========");

            DiscountStrategy regular = new RegularDiscount();

            System.out.println(
                    "Назва стратегії: " + regular.getStrategyName()
            );

            DiscountStrategy custom = new DiscountStrategy() {
                @Override
                public double calculateDiscount(
                        double price, int months) {
                    return 0;
                }
            };

            System.out.println(
                    "Назва за замовчуванням: "
                            + custom.getStrategyName()
            );







            System.out.println("\n========== ПЕРЕВАГИ АБОНЕМЕНТА ==========");

            SubscriptionBenefit benefit = new LongTermDiscount();

            System.out.println(benefit.getBenefitDescription());







            System.out.println("\n========== ПАТЕРН STRATEGY ==========");

            SubscriptionCalculator calculator =
                    new SubscriptionCalculator(new LongTermDiscount());

            System.out.println(
                    "Поточна стратегія: " + calculator.getStrategyName()
            );
            System.out.printf(
                    "До сплати: %.2f грн%n",
                    calculator.calculateFinalPrice(totalPrice, months)
            );

            calculator.setStrategy(new RegularDiscount());

            System.out.println(
                    "Нова стратегія: " + calculator.getStrategyName()
            );
            System.out.printf(
                    "До сплати: %.2f грн%n",
                    calculator.calculateFinalPrice(totalPrice, months)
            );

        } catch (InputMismatchException e) {
            System.out.println(
                    "Помилка: введено некоректний тип даних."
            );

        } catch (NegativeArraySizeException e) {
            System.out.println(
                    "Помилка: кількість клієнтів не може бути від'ємною."
            );

        } catch (DomainException e) {
            System.out.println(
                    "Помилка даних клієнта: " + e.getMessage()
            );
            System.out.println(
                    "Некоректне значення: " + e.getInvalidValue()
            );

        } catch (IllegalArgumentException e) {
            System.out.println("Помилка: " + e.getMessage());

        } finally {
            scanner.close();
            System.out.println("\nРоботу програми завершено.");
        }
    }

    private static int readInt(Scanner scanner) {
        try {
            int value = scanner.nextInt();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw e;
        }
    }

    private static double readDouble(Scanner scanner) {
        try {
            double value = scanner.nextDouble();
            scanner.nextLine();
            return value;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            throw e;
        }
    }
}

class Client {

    String name;
    String surname;
    int age;
    String subscriptionType;
    int months;
    double monthlyPrice;

    Client(String name, String surname, int age,
           String subscriptionType, int months, double monthlyPrice)
            throws InvalidAgeException, InvalidSubscriptionException {

        if (name == null || name.trim().isEmpty()
                || surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Ім'я та прізвище не можуть бути порожніми."
            );
        }

        if (age < 1 || age > 120) {
            throw new InvalidAgeException(
                    "Вік клієнта повинен бути від 1 до 120 років.",
                    age
            );
        }

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

        if (subscriptionType == null
                || subscriptionType.trim().isEmpty()) {
            throw new InvalidSubscriptionException(
                    "Тип абонемента не може бути порожнім.",
                    subscriptionType
            );
        }

        this.name = name;
        this.surname = surname;
        this.age = age;
        this.subscriptionType = subscriptionType;
        this.months = months;
        this.monthlyPrice = monthlyPrice;
    }

    @Override
    public String toString() {
        double totalPrice = monthlyPrice * months;

        SubscriptionCalculator calculator =
                new SubscriptionCalculator(new LongTermDiscount());

        double discount = calculator.calculateDiscount(totalPrice, months);
        double finalPrice = totalPrice - discount;

        return String.format(
                "Клієнт: %s %s%n"
                        + "Вік: %d%n"
                        + "Тип абонемента: %s%n"
                        + "Кількість місяців: %d%n"
                        + "Вартість одного місяця: %.2f грн%n"
                        + "Вартість без знижки: %.2f грн%n"
                        + "Знижка: %.2f грн%n"
                        + "До сплати: %.2f грн",
                name,
                surname,
                age,
                subscriptionType,
                months,
                monthlyPrice,
                totalPrice,
                discount,
                finalPrice
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Client)) {
            return false;
        }

        Client other = (Client) obj;

        return name.equals(other.name)
                && surname.equals(other.surname)
                && age == other.age
                && subscriptionType.equals(other.subscriptionType)
                && months == other.months
                && Double.compare(monthlyPrice, other.monthlyPrice) == 0;
    }
}
