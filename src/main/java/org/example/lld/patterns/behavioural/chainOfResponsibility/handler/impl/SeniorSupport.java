package org.example.lld.patterns.behavioural.chainOfResponsibility.handler.impl;

import org.example.lld.patterns.behavioural.chainOfResponsibility.handler.SupportHandler;
import org.example.lld.patterns.behavioural.chainOfResponsibility.request.Ticket;

public class SeniorSupport implements SupportHandler {
    private SupportHandler nextHandler;

    @Override
    public void handleRequest(Ticket ticket) {
        if (ticket.getSeverity() <= 3) {
            System.out.println("Junior Support handled: " + ticket.getDescription());
        } else if (nextHandler != null) {
            nextHandler.handleRequest(ticket);
        } else {
            System.out.println("No handler available for: " + ticket.getDescription());
        }
    }

    @Override
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
