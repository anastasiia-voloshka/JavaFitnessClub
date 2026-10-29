
import java.util.InputMismatchException;
import java.util.Scanner;

import src.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть кількість клієнтів: ");
            int n = readInt(scanner);

            if (n < 0) {
                throw new IllegalArgumentException(
                        "Кількість клієнтів не може бути від'ємною."
                );
            }

            Client[] clients = new Client[n];

            src.Subject subject = new src.Subject();

             subject.addObserver(
               new src.ConsoleNotificationObserver()
            );

            subject.addObserver(
        new src.MembershipLogObserver()
);



            for (int i = 0; i < n; i++) {
                System.out.println();
                System.out.println("Клієнт №" + (i + 1));

                System.out.print("Введіть ім'я: ");
                String name = scanner.nextLine();

                System.out.print("Введіть прізвище: ");
                String surname = scanner.nextLine();

                System.out.print("Введіть вік: ");
                int age = readInt(scanner);

                System.out.print(
                        "Введіть тип абонемента "
                                + "(standard / long-term / personal-training): "
                );
                String subscriptionType = scanner.nextLine();

                System.out.print("Введіть кількість місяців: ");
                int months = readInt(scanner);

                System.out.print("Введіть вартість одного місяця: ");
                double monthlyPrice = readDouble(scanner);

                Subscription subscription = SubscriptionFactory.create(
                        subscriptionType,
                        months,
                        monthlyPrice
                );

                clients[i] = new Client(
                        name,
                        surname,
                        age,
                        subscription
                );

                subject.notifyObservers(
        "Зареєстровано клієнта: " + name + " " + surname
                        + ". Абонемент: " + subscription.getPlanName()
                + ", термін: " + months + " міс."
);
            }

            System.out.println("\n========== УСІ КЛІЄНТИ ==========");

            for (Client client : clients) {
                System.out.println(client);
                System.out.println("---------------------------------");
            }

            int count = 0;

            for (Client client : clients) {
                if (client.getSubscription().getMonths() >= 6) {
                    count++;
                }
            }

            System.out.println(
                    "Кількість клієнтів з абонементом від 6 місяців: "
                            + count
            );

            for (int i = 0; i < clients.length - 1; i++) {
                for (int j = 0; j < clients.length - 1 - i; j++) {
                    if (clients[j].getSubscription().getMonthlyPrice()
                            > clients[j + 1].getSubscription().getMonthlyPrice()) {
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
                if (client.getName().equalsIgnoreCase(searchName)
                        && client.getSurname().equalsIgnoreCase(searchSurname)) {
                    System.out.println("\nКлієнта знайдено:");
                    System.out.println(client);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Клієнта не знайдено.");
            }

            System.out.println("\n========== ПОЛІМОРФІЗМ ==========");

            DiscountStrategy[] strategies = {
                    DiscountStrategyFactory.create("regular"),
                    DiscountStrategyFactory.create("long-term")
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

            DiscountStrategy regular =
                    DiscountStrategyFactory.create("regular");

            System.out.println(
                    "Назва стратегії: " + regular.getStrategyName()
            );

            DiscountStrategy custom = new DiscountStrategy() {
                @Override
                public double calculateDiscount(double price, int months) {
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
                    new SubscriptionCalculator(
                            DiscountStrategyFactory.create("long-term")
                    );

            System.out.println(
                    "Поточна стратегія: " + calculator.getStrategyName()
            );

            System.out.printf(
                    "До сплати: %.2f грн%n",
                    calculator.calculateFinalPrice(totalPrice, months)
            );

            SubscriptionDemo.run();

            calculator.setStrategy(
                    DiscountStrategyFactory.create("regular")
            );

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
