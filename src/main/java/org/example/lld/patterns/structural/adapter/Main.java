package org.example.lld.patterns.structural.adapter;

public class Main {
    public static void main(String[] args) {
        LegacyMediaPlayer legacyPlayer = new LegacyMediaPlayer();
        MediaPlayer adapter = new MediaAdapter(legacyPlayer);
        AudioPlayer player = new AudioPlayer(adapter);

        player.play("mp3", "song.mp3");
        player.play("wav", "sound.wav");
    }
}
