package org.example.lld.patterns.creational.abstractFactory;

import org.example.lld.patterns.creational.abstractFactory.product.Button;
import org.example.lld.patterns.creational.abstractFactory.product.Window;
import org.example.lld.patterns.creational.abstractFactory.product.impl.MacButton;
import org.example.lld.patterns.creational.abstractFactory.product.impl.MacWindow;

public class MacUIFactory implements UIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public Window createWindow() {
        return new MacWindow();
    }
}
