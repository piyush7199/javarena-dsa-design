package org.example.lld.patterns.creational.abstractFactory;

import org.example.lld.patterns.creational.abstractFactory.product.Button;
import org.example.lld.patterns.creational.abstractFactory.product.Window;

public class Application {
    private Button button;
    private Window window;

    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.window = factory.createWindow();
    }

    public void renderUI() {
        button.render();
        window.display();
    }
}
