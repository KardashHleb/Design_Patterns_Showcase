package org.example.patterns.behavioral;

import org.example.PatternDemo;

import java.util.ArrayDeque;
import java.util.Deque;

public class CommandDemo implements PatternDemo {

    interface Command {
        void execute();
        void undo();
    }

    static class Light {
        private boolean on;

        void turnOn() {
            on = true;
            System.out.println("  → Light on");
        }

        void turnOff() {
            on = false;
            System.out.println("  → Light off");
        }

        boolean isOn() {
            return on;
        }
    }

    static class LightOnCommand implements Command {
        private final Light light;

        LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }

        @Override
        public void undo() {
            light.turnOff();
        }
    }

    static class RemoteControl {
        private final Deque<Command> history = new ArrayDeque<>();

        void pressButton(Command command) {
            command.execute();
            history.push(command);
        }

        void pressUndo() {
            if (!history.isEmpty()) {
                history.pop().undo();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Command wraps an action in an object and supports undo:");
        Light light = new Light();
        RemoteControl remote = new RemoteControl();
        Command turnOn = new LightOnCommand(light);
        remote.pressButton(turnOn);
        remote.pressUndo();
    }
}
