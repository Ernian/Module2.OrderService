package org.example.factory;

import org.example.enums.CustomerType;
import org.example.strategy.AnotherDiscountStrategy;
import org.example.strategy.DiscountStrategy;
import org.example.strategy.PremiumDiscountStrategy;
import org.example.strategy.RegularDiscountStrategy;
import org.example.strategy.VipDiscountStrategy;

import java.util.HashMap;
import java.util.Map;

public class DiscountStrategyFactory {
    private final Map<CustomerType, DiscountStrategy> strategies;

    public DiscountStrategyFactory() {
        strategies = new HashMap<>();
        strategies.put(CustomerType.REGULAR, new RegularDiscountStrategy());
        strategies.put(CustomerType.VIP, new VipDiscountStrategy());
        strategies.put(CustomerType.PREMIUM, new PremiumDiscountStrategy());
        strategies.put(CustomerType.ANOTHER, new AnotherDiscountStrategy());
    }

    public DiscountStrategy getStrategy(CustomerType customerType) {
        if (!strategies.containsKey(customerType)) {
            throw new RuntimeException("Unknown discount strategy for customer type: " + customerType);
        }

        return strategies.get(customerType);
    }
}
