package org.example.strategy;

public class RegularDiscountStrategy implements DiscountStrategy {
    private final static double REGULAR_RATE = 0;

    @Override
    public double calculate(double amount) {
        return amount * REGULAR_RATE;
    }
}
