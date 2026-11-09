package org.example.strategy;

public class CardCommissionStrategy implements CommissionStrategy {
    private final static double CARD_COMMISSION = 0.02;

    @Override
    public double calculate(double amount) {
        return amount * CARD_COMMISSION;
    }
}
