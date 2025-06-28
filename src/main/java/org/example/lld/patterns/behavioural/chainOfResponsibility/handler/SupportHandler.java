package org.example.lld.patterns.behavioural.chainOfResponsibility.handler;

import org.example.lld.patterns.behavioural.chainOfResponsibility.request.Ticket;

public interface SupportHandler {
    void handleRequest(Ticket ticket);
    void setNextHandler(SupportHandler nextHandler);
}
