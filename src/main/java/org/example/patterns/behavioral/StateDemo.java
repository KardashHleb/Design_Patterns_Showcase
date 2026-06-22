package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class StateDemo implements PatternDemo {

    interface State {
        void insertCoin(VendingMachine machine);
        void dispense(VendingMachine machine);
    }

    static class VendingMachine {
        private State state = new IdleState();

        void setState(State state) {
            this.state = state;
        }

        void insertCoin() {
            state.insertCoin(this);
        }

        void dispense() {
            state.dispense(this);
        }
    }

    static class IdleState implements State {
        @Override
        public void insertCoin(VendingMachine machine) {
            System.out.println("  → Монета принята");
            machine.setState(new HasCoinState());
        }

        @Override
        public void dispense(VendingMachine machine) {
            System.out.println("  → Сначала вставьте монету");
        }
    }

    static class HasCoinState implements State {
        @Override
        public void insertCoin(VendingMachine machine) {
            System.out.println("  → Монета уже вставлена");
        }

        @Override
        public void dispense(VendingMachine machine) {
            System.out.println("  → Напиток выдан");
            machine.setState(new IdleState());
        }
    }

    @Override
    public void run() {
        System.out.println("State меняет поведение объекта при смене состояния:");
        VendingMachine machine = new VendingMachine();
        machine.dispense();
        machine.insertCoin();
        machine.dispense();
    }
}
