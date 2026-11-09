package org.example.entity;

import org.example.enums.CustomerType;
import org.example.enums.PaymentType;

import java.util.List;

public record Order(
        String id,
        CustomerType customerType,
        PaymentType paymentType,
        List<String> items,
        double amount
) {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;
        private CustomerType customerType;
        private PaymentType paymentType;
        private List<String> items;
        private double amount;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder customerType(CustomerType customerType) {
            this.customerType = customerType;
            return this;
        }

        public Builder paymentType(PaymentType paymentType) {
            this.paymentType = paymentType;
            return this;
        }

        public Builder items(List<String> items) {
            this.items = items;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Order build() {
            return new Order(
                    this.id,
                    this.customerType,
                    this.paymentType,
                    this.items,
                    this.amount
            );
        }
    }
}
