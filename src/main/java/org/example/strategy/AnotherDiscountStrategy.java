package org.example.strategy;

public class AnotherDiscountStrategy implements DiscountStrategy {
    private final static double ANOTHER_RATE = 0.04;

    @Override
    public double calculate(double amount) {
        return amount * ANOTHER_RATE;
    }
}
