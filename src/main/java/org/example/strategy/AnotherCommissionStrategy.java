package org.example.strategy;

public class AnotherCommissionStrategy implements CommissionStrategy {
    private final static double ANOTHER_COMMISSION = 0.03;

    @Override
    public double calculate(double amount) {
        return amount * ANOTHER_COMMISSION;
    }
}
