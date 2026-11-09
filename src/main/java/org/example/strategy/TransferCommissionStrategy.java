package org.example.strategy;

public class TransferCommissionStrategy implements CommissionStrategy {
    private final static double TRANSFER_COMMISSION = 0.02;

    @Override
    public double calculate(double amount) {
        return amount * TRANSFER_COMMISSION;
    }
}
