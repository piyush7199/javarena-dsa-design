package org.example.lld.patterns.creational.abstractFactory;

public class Main {
    public static void main(String[] args) {
        // Create Windows UI
        UIFactory windowsFactory = new WindowsUIFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.renderUI();

        // Create Mac UI
        UIFactory macFactory = new MacUIFactory();
        Application macApp = new Application(macFactory);
        macApp.renderUI();
    }
}
