package org.example.validator;

import org.example.entity.Order;

public class OrderAmountValidator implements Validator<Order> {
    @Override
    public void validate(Order order) {
        if (order.amount() <= 0) {
            throw new IllegalStateException("Order amount must be positive");
        }
    }
}
