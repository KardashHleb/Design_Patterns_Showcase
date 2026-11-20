package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class ChainOfResponsibilityDemo implements PatternDemo {

    abstract static class Handler {
        private Handler next;

        Handler linkWith(Handler next) {
            this.next = next;
            return next;
        }

        void handle(int amount) {
            if (next != null) {
                next.handle(amount);
            } else {
                System.out.println("  → Request not handled: $" + amount);
            }
        }
    }

    static class BankHandler extends Handler {
        @Override
        void handle(int amount) {
            if (amount <= 100) {
                System.out.println("  → Bank: approved $" + amount);
            } else {
                super.handle(amount);
            }
        }
    }

    static class PayPalHandler extends Handler {
        @Override
        void handle(int amount) {
            if (amount <= 500) {
                System.out.println("  → PayPal: approved $" + amount);
            } else {
                super.handle(amount);
            }
        }
    }

    static class BitcoinHandler extends Handler {
        @Override
        void handle(int amount) {
            System.out.println("  → Bitcoin: approved $" + amount);
        }
    }

    @Override
    public void run() {
        System.out.println("Chain of Responsibility passes a request along a chain:");
        Handler chain = new BankHandler();
        chain.linkWith(new PayPalHandler()).linkWith(new BitcoinHandler());
        chain.handle(50);
        chain.handle(300);
        chain.handle(2000);
    }
}
