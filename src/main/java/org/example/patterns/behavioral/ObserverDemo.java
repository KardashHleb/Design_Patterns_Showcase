package org.example.patterns.behavioral;

import org.example.PatternDemo;

import java.util.ArrayList;
import java.util.List;

public class ObserverDemo implements PatternDemo {

    interface Observer {
        void update(String event);
    }

    static class NewsAgency {
        private final List<Observer> subscribers = new ArrayList<>();
        private String latestNews;

        void subscribe(Observer observer) {
            subscribers.add(observer);
        }

        void publish(String news) {
            latestNews = news;
            for (Observer observer : subscribers) {
                observer.update(latestNews);
            }
        }
    }

    static class NewsChannel implements Observer {
        private final String name;

        NewsChannel(String name) {
            this.name = name;
        }

        @Override
        public void update(String event) {
            System.out.println("  → " + name + " получил новость: " + event);
        }
    }

    @Override
    public void run() {
        System.out.println("Observer уведомляет подписчиков об изменениях:");
        NewsAgency agency = new NewsAgency();
        agency.subscribe(new NewsChannel("CNN"));
        agency.subscribe(new NewsChannel("BBC"));
        agency.publish("Java 21 released!");
    }
}
