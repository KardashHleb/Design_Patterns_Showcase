package org.example.patterns.structural;

import org.example.PatternDemo;

public class FacadeDemo implements PatternDemo {

    static class Amplifier {
        void on() {
            System.out.println("  → Усилитель включён");
        }
    }

    static class DvdPlayer {
        void on() {
            System.out.println("  → DVD-плеер включён");
        }

        void play(String movie) {
            System.out.println("  → Воспроизведение: " + movie);
        }
    }

    static class Projector {
        void on() {
            System.out.println("  → Проектор включён");
        }
    }

    static class HomeTheaterFacade {
        private final Amplifier amplifier = new Amplifier();
        private final DvdPlayer dvdPlayer = new DvdPlayer();
        private final Projector projector = new Projector();

        void watchMovie(String movie) {
            System.out.println("  Запуск домашнего кинотеатра...");
            amplifier.on();
            dvdPlayer.on();
            projector.on();
            dvdPlayer.play(movie);
        }
    }

    @Override
    public void run() {
        System.out.println("Facade скрывает сложность подсистемы за одним методом:");
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie("Inception");
    }
}
