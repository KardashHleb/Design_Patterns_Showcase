package org.example.patterns.behavioral;

import org.example.PatternDemo;

import java.util.ArrayList;
import java.util.List;

public class MediatorDemo implements PatternDemo {

    interface ChatMediator {
        void join(User user);

        void broadcast(User sender, String message);

        void sendPrivate(User sender, String recipientName, String message);
    }

    static class ChatRoom implements ChatMediator {
        private final List<User> users = new ArrayList<>();

        @Override
        public void join(User user) {
            users.add(user);
            System.out.println("  * " + user.getName() + " вошёл в чат");
        }

        @Override
        public void broadcast(User sender, String message) {
            for (User user : users) {
                if (user != sender) {
                    user.receive(sender.getName(), message);
                }
            }
        }

        @Override
        public void sendPrivate(User sender, String recipientName, String message) {
            users.stream()
                    .filter(user -> user.getName().equals(recipientName))
                    .findFirst()
                    .ifPresentOrElse(
                            user -> user.receive(sender.getName() + " (лично)", message),
                            () -> System.out.println("  * Пользователь " + recipientName + " не найден"));
        }
    }

    static class User {
        private final String name;
        private final ChatMediator mediator;
        private final List<String> inbox = new ArrayList<>();

        User(String name, ChatMediator mediator) {
            this.name = name;
            this.mediator = mediator;
            mediator.join(this);
        }

        String getName() {
            return name;
        }

        List<String> getInbox() {
            return List.copyOf(inbox);
        }

        void send(String message) {
            mediator.broadcast(this, message);
        }

        void sendTo(String recipientName, String message) {
            mediator.sendPrivate(this, recipientName, message);
        }

        void receive(String from, String message) {
            inbox.add(from + ": " + message);
            System.out.println("  → " + name + " получил от " + from + ": " + message);
        }
    }

    @Override
    public void run() {
        System.out.println("Mediator: пользователи не знают друг о друге и общаются только через чат-комнату:");
        ChatMediator room = new ChatRoom();
        User alice = new User("Alice", room);
        User bob = new User("Bob", room);
        new User("Carol", room);

        alice.send("Всем привет!");
        bob.sendTo("Alice", "Привет, Alice!");
        bob.sendTo("Dave", "Ты здесь?");
    }
}
