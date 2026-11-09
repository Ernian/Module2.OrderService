package org.example.strategy;

public class VipDiscountStrategy implements DiscountStrategy {
    private final static double VIP_RATE = 0.05;

    @Override
    public double calculate(double amount) {
        return amount * VIP_RATE;
    }
}
