package org.example.lld.patterns.creational.singleton;

public class Main {
    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        logger1.log("Message from logger1");
        logger2.log("Message from logger2");

        // Verify single instance
        System.out.println("Same instance? " + (logger1 == logger2));
    }
}
