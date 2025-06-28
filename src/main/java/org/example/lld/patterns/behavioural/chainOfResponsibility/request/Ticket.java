package org.example.lld.patterns.behavioural.chainOfResponsibility.request;

public class Ticket {
    private final String description;
    private final int severity;

    public Ticket(String description, int severity) {
        this.description = description;
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public int getSeverity() {
        return severity;
    }
}
