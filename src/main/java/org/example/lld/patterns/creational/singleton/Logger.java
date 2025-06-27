package org.example.lld.patterns.creational.singleton;

public class Logger {
    // Private static instance (lazy initialization)
    private static volatile Logger instance = null;

    // Private constructor to prevent instantiation
    private Logger() {
        // Prevent instantiation via reflection
        if (instance != null) {
            throw new RuntimeException("Use getInstance() to get the single instance.");
        }
    }

    // Thread-safe getInstance method (double-checked locking)
    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Example method
    public void log(String message) {
        System.out.println("Log: " + message);
    }
}
