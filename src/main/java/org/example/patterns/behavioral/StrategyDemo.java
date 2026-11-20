package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class StrategyDemo implements PatternDemo {

    interface PaymentStrategy {
        void pay(int amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override
        public void pay(int amount) {
            System.out.println("  → Paid $" + amount + " by credit card");
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        @Override
        public void pay(int amount) {
            System.out.println("  → Paid $" + amount + " via PayPal");
        }
    }

    static class ShoppingCart {
        private PaymentStrategy paymentStrategy;

        void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        void checkout(int amount) {
            paymentStrategy.pay(amount);
        }
    }

    @Override
    public void run() {
        System.out.println("Strategy lets you switch the payment algorithm at runtime:");
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100);
        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout(50);
    }
}
