package org.example.lld.practice.messageBroadcasting.models;

public class Message {
    private final String content;
    private final MessageType type;

    public Message(String content, MessageType type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public MessageType getType() {
        return type;
    }
}
