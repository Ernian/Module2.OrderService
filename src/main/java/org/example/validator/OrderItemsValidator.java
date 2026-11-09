package org.example.validator;

import org.example.entity.Order;

public class OrderItemsValidator implements Validator<Order> {
    @Override
    public void validate(Order order) {
        if (order.items() == null || order.items().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }
    }
}
