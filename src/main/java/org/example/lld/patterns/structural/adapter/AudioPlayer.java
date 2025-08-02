package org.example.lld.patterns.structural.adapter;

public class AudioPlayer {
    private MediaPlayer mediaPlayer;

    public AudioPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public void play(String audioType, String fileName) {
        mediaPlayer.play(audioType, fileName);
    }
}
