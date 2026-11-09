package org.example.factory;

import org.example.enums.PaymentType;
import org.example.strategy.AnotherCommissionStrategy;
import org.example.strategy.CardCommissionStrategy;
import org.example.strategy.CashCommissionStrategy;
import org.example.strategy.CommissionStrategy;
import org.example.strategy.TransferCommissionStrategy;

import java.util.HashMap;
import java.util.Map;

public class CommissionStrategyFactory {
    private final Map<PaymentType, CommissionStrategy> strategies;

    public CommissionStrategyFactory() {
        strategies = new HashMap<>();
        strategies.put(PaymentType.CARD, new CardCommissionStrategy());
        strategies.put(PaymentType.CASH, new CashCommissionStrategy());
        strategies.put(PaymentType.TRANSFER, new TransferCommissionStrategy());
        strategies.put(PaymentType.ANOTHER, new AnotherCommissionStrategy());
    }

    public CommissionStrategy getStrategy(PaymentType paymentType) {
        if (!strategies.containsKey(paymentType)) {
            throw new RuntimeException("Unknown commission strategy for payment type: " + paymentType);
        }

        return strategies.get(paymentType);
    }
}
