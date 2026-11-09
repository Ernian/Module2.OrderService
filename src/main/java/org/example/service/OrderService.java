package org.example.service;

import org.example.entity.Order;
import org.example.enums.CustomerType;
import org.example.enums.PaymentType;
import org.example.factory.CommissionStrategyFactory;
import org.example.factory.DiscountStrategyFactory;
import org.example.validator.OrderAmountValidator;
import org.example.validator.OrderItemsValidator;
import org.example.validator.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Контекст: Тебе достался в наследство код от предыдущего разработчика — сервис обработки заказов интернет-магазина OrderService.
 * Он работает, тесты (если бы они были) прошли бы, заказчик доволен... пока не попросил добавить новый тип клиента или способ оплаты.
 * Тут выясняется, что каждое такое "простое" изменение требует лезть в один и тот же метод на 80 строк, где всё завязано друг на друга,
 * и есть риск что-то сломать в соседней логике.
 * Твоя задача: не переписать сервис с нуля, а отрефакторить — сохранив то же поведение (те же скидки, та же комиссия, тот же чек)
 * Опирайся на SOLID и паттерны проектирования
 * Lombok использовать нельзя, все писать руками.
 */
public class OrderService {
    private final List<Validator<Order>> validators;
    private final DiscountStrategyFactory discountStrategyFactory;
    private final CommissionStrategyFactory commissionStrategyFactory;
    private final List<Order> orders = new ArrayList<>();

    public OrderService(
            List<Validator<Order>> validators,
            DiscountStrategyFactory discountStrategyFactory,
            CommissionStrategyFactory commissionStrategyFactory) {
        this.validators = validators;
        this.discountStrategyFactory = discountStrategyFactory;
        this.commissionStrategyFactory = commissionStrategyFactory;
    }

    public double processOrder(Order order) {

        // --- валидация ---
        validators.forEach(validator -> validator.validate(order));

        // --- расчёт скидки по типу клиента ---
        var discountStrategy = discountStrategyFactory.getStrategy(order.customerType());
        double discount = discountStrategy.calculate(order.amount());

        // --- расчёт комиссии по способу оплаты ---
        var commissionStrategy = commissionStrategyFactory.getStrategy(order.paymentType());
        double commission = commissionStrategy.calculate(order.amount());

        // --- итоговая сумма ---
        double finalAmount = order.amount() - discount + commission;

        // --- формирование чека прямо тут ---
        String receipt = generateReceipt(order, discount, commission);
        printReceipt(receipt);

        // --- "сохранение" заказа  ---
        saveOrder(order);

        return finalAmount;
    }

    public String generateReceipt(Order order, double discount, double commission) {
        return new StringBuilder()
                .append("=== RECEIPT ===\n")
                .append("Order ID: " + order.id() + '\n')
                .append("Items: " + order.items() + '\n')
                .append("Base amount: " + order.amount() + '\n')
                .append("Discount: -" + discount + '\n')
                .append("Commission: +" + commission + '\n')
                .append("Final amount: " + (order.amount() - discount + commission) + '\n')
                .append("================")
                .toString();
    }

    public void printReceipt(String receipt) {
        System.out.println(receipt);
    }

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public static void main(String[] args) {
        OrderService service = new OrderService(
                List.of(
                        new OrderAmountValidator(),
                        new OrderItemsValidator()
                ),
                new DiscountStrategyFactory(),
                new CommissionStrategyFactory()
        );

        Order order1 = Order.builder()
                .id(UUID.randomUUID().toString())
                .customerType(CustomerType.VIP)
                .paymentType(PaymentType.CARD)
                .items(List.of("Laptop", "Mouse"))
                .amount(1000.0)
                .build();

        Order order2 = Order.builder()
                .id(UUID.randomUUID().toString())
                .customerType(CustomerType.REGULAR)
                .paymentType(PaymentType.CASH)
                .items(List.of("Book"))
                .amount(20.0)
                .build();

        Order order3 = Order.builder()
                .id(UUID.randomUUID().toString())
                .customerType(CustomerType.ANOTHER)
                .paymentType(PaymentType.ANOTHER)
                .items(List.of("Phone"))
                .amount(200.0)
                .build();

        System.out.println("Final amount 1: " + service.processOrder(order1));
        System.out.println("Final amount 2: " + service.processOrder(order2));
        System.out.println("Final amount 3: " + service.processOrder(order3));
    }
}
