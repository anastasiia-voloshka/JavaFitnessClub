
# Лабораторна робота №4
## Реалізація програмних інтерфейсів мовою Java

### Предметна область
Система обліку клієнтів фітнес-клубу та розрахунку вартості абонементів.

### Мета роботи
Навчитися використовувати інтерфейси, абстрактні класи, наслідування, поліморфізм і патерн Strategy у Java.

### Реалізовані вимоги

1. Інтерфейс `DiscountStrategy` визначає поведінку розрахунку знижки.
2. `RegularDiscount` і `LongTermDiscount` реалізують різні стратегії.
3. Масив `DiscountStrategy[]` демонструє поліморфізм.
4. Інтерфейс містить `default`-метод `getStrategyName()`.
5. `AbstractDiscountStrategy` містить спільну перевірку вхідних даних.
6. `LongTermDiscount` реалізує два інтерфейси: `DiscountStrategy` через абстрактний клас та `SubscriptionBenefit` безпосередньо.
7. `SubscriptionCalculator` реалізує контекст патерна Strategy та дозволяє змінювати стратегію під час виконання програми.

### Використані технології
- Java
- Інтерфейси та абстрактні класи
- Наслідування та поліморфізм
- Патерн Strategy
- Git

### Запуск програми

1. Відкрити проєкт у середовищі розробки Java.
2. Переконатися, що всі `.java`-файли знаходяться в одній папці та не мають оголошень `package`.
3. Запустити клас `Main`.

Альтернативний запуск через термінал:

```bash
javac Main.java DomainException.java InvalidAgeException.java InvalidSubscriptionException.java DiscountStrategy.java AbstractDiscountStrategy.java RegularDiscount.java LongTermDiscount.java SubscriptionBenefit.java SubscriptionCalculator.java
java Main
```
