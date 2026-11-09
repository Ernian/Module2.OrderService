package org.example.strategy;

public class PremiumDiscountStrategy implements DiscountStrategy {
    private final static double PREMIUM_RATE = 0.1;

    @Override
    public double calculate(double amount) {
        return amount * PREMIUM_RATE;
    }
}
