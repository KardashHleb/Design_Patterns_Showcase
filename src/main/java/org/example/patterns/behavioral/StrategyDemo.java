package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class StrategyDemo implements PatternDemo {

    interface PaymentStrategy {
        void pay(int amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override
        public void pay(int amount) {
            System.out.println("  → Оплата $" + amount + " кредитной картой");
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        @Override
        public void pay(int amount) {
            System.out.println("  → Оплата $" + amount + " через PayPal");
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
        System.out.println("Strategy позволяет менять алгоритм оплаты на лету:");
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100);
        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout(50);
    }
}
