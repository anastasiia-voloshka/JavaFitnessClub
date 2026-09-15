import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ім'я клієнта: ");
        String name = scanner.nextLine();

        System.out.print("Введіть прізвище клієнта: ");
        String surname = scanner.nextLine();

        System.out.print("Введіть вік клієнта: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введіть тип абонемента: ");
        String subscriptionType = scanner.nextLine();

        System.out.print("Введіть кількість місяців: ");
        int months = scanner.nextInt();

        System.out.print("Введіть вартість одного місяця: ");
        double monthlyPrice = scanner.nextDouble();

        double totalPrice = monthlyPrice * months;
        double discount;

        if (months >= 6) {
            discount = totalPrice * 10 / 100;
        } else {
            discount = 0;
        }

        double finalPrice = totalPrice - discount;

        System.out.println();
        System.out.println("========== ФІТНЕС-КЛУБ ==========");

        System.out.printf("Клієнт: %s %s%n", name, surname);
        System.out.printf("Вік: %d%n", age);
        System.out.printf("Тип абонемента: %s%n", subscriptionType);
        System.out.printf("Кількість місяців: %d%n", months);
        System.out.printf("Вартість одного місяця: %.2f грн%n", monthlyPrice);
        System.out.printf("Вартість без знижки: %.2f грн%n", totalPrice);
        System.out.printf("Знижка: %.2f грн%n", discount);
        System.out.printf("До сплати: %.2f грн%n", finalPrice);

        System.out.println("=================================");

        scanner.close();
    }
}