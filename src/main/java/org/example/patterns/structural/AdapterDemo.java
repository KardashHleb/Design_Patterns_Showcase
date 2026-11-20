package org.example.patterns.structural;

import org.example.PatternDemo;

public class AdapterDemo implements PatternDemo {

    interface MediaPlayer {
        void play(String filename);
    }

    static class Mp3Player implements MediaPlayer {
        @Override
        public void play(String filename) {
            System.out.println("  → Playing MP3: " + filename);
        }
    }

    static class AdvancedMediaPlayer {
        void playVlc(String filename) {
            System.out.println("  → Playing VLC: " + filename);
        }
    }

    static class MediaAdapter implements MediaPlayer {
        private final AdvancedMediaPlayer advancedPlayer = new AdvancedMediaPlayer();

        @Override
        public void play(String filename) {
            if (filename.endsWith(".vlc")) {
                advancedPlayer.playVlc(filename);
            } else {
                System.out.println("  → Unsupported format: " + filename);
            }
        }
    }

    static class AudioPlayer implements MediaPlayer {
        private final Mp3Player mp3Player = new Mp3Player();
        private final MediaAdapter adapter = new MediaAdapter();

        @Override
        public void play(String filename) {
            if (filename.endsWith(".mp3")) {
                mp3Player.play(filename);
            } else {
                adapter.play(filename);
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Adapter lets the player play VLC files through a common interface:");
        MediaPlayer player = new AudioPlayer();
        player.play("song.mp3");
        player.play("movie.vlc");
    }
}
