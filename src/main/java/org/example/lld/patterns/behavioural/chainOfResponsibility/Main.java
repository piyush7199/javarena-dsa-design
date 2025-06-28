package org.example.lld.patterns.behavioural.chainOfResponsibility;

import org.example.lld.patterns.behavioural.chainOfResponsibility.handler.SupportHandler;
import org.example.lld.patterns.behavioural.chainOfResponsibility.handler.impl.JuniorSupport;
import org.example.lld.patterns.behavioural.chainOfResponsibility.handler.impl.Manager;
import org.example.lld.patterns.behavioural.chainOfResponsibility.handler.impl.SeniorSupport;
import org.example.lld.patterns.behavioural.chainOfResponsibility.request.Ticket;

public class Main {
    public static void main(String[] args) {
        SupportHandler junior = new JuniorSupport();
        SupportHandler senior = new SeniorSupport();
        SupportHandler manager = new Manager();

        junior.setNextHandler(senior);
        senior.setNextHandler(manager);

        Ticket ticket1 = new Ticket("Login issue", 1);
        Ticket ticket2 = new Ticket("Database error", 3);
        Ticket ticket3 = new Ticket("System crash", 5);
        Ticket ticket4 = new Ticket("Critical failure", 7);

        junior.handleRequest(ticket1);
        junior.handleRequest(ticket2);
        junior.handleRequest(ticket3);
        junior.handleRequest(ticket4);
    }
}
