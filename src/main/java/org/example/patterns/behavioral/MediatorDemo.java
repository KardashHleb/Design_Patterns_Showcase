package org.example.patterns.behavioral;

import org.example.PatternDemo;

public class MediatorDemo implements PatternDemo {

    static class ChatRoom {
        void showMessage(String user, String message) {
            System.out.println("  [" + user + "]: " + message);
        }
    }

    static class User {
        private final String name;
        private final ChatRoom chatRoom;

        User(String name, ChatRoom chatRoom) {
            this.name = name;
            this.chatRoom = chatRoom;
        }

        void send(String message) {
            chatRoom.showMessage(name, message);
        }
    }

    @Override
    public void run() {
        System.out.println("Mediator централизует общение между пользователями:");
        ChatRoom room = new ChatRoom();
        User alice = new User("Alice", room);
        User bob = new User("Bob", room);
        alice.send("Привет, Bob!");
        bob.send("Привет, Alice!");
    }
}
