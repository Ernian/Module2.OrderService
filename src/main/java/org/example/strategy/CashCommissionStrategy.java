package org.example.strategy;

public class CashCommissionStrategy implements CommissionStrategy {
    private final static double CASH_COMMISSION = 0;

    @Override
    public double calculate(double amount) {
        return amount * CASH_COMMISSION;
    }
}
