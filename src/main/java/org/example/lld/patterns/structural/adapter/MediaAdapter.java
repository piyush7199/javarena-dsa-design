package org.example.lld.patterns.structural.adapter;

public class MediaAdapter implements MediaPlayer {
    private LegacyMediaPlayer legacyPlayer;

    public MediaAdapter(LegacyMediaPlayer legacyPlayer) {
        this.legacyPlayer = legacyPlayer;
    }

    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("mp3")) {
            legacyPlayer.playMp3(fileName);
        } else {
            System.out.println("Unsupported format: " + audioType);
        }
    }
}
