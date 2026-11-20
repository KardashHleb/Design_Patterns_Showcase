package org.example.patterns.structural;

import org.example.PatternDemo;

public class FacadeDemo implements PatternDemo {

    static class Amplifier {
        void on() {
            System.out.println("  → Amplifier on");
        }
    }

    static class DvdPlayer {
        void on() {
            System.out.println("  → DVD player on");
        }

        void play(String movie) {
            System.out.println("  → Playing: " + movie);
        }
    }

    static class Projector {
        void on() {
            System.out.println("  → Projector on");
        }
    }

    static class HomeTheaterFacade {
        private final Amplifier amplifier = new Amplifier();
        private final DvdPlayer dvdPlayer = new DvdPlayer();
        private final Projector projector = new Projector();

        void watchMovie(String movie) {
            System.out.println("  Starting home theater...");
            amplifier.on();
            dvdPlayer.on();
            projector.on();
            dvdPlayer.play(movie);
        }
    }

    @Override
    public void run() {
        System.out.println("Facade hides a complex subsystem behind one method:");
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie("Inception");
    }
}
