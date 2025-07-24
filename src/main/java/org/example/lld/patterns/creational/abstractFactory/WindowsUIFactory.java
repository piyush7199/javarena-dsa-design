package org.example.lld.patterns.creational.abstractFactory;

import org.example.lld.patterns.creational.abstractFactory.product.Button;
import org.example.lld.patterns.creational.abstractFactory.product.Window;
import org.example.lld.patterns.creational.abstractFactory.product.impl.WindowButton;
import org.example.lld.patterns.creational.abstractFactory.product.impl.WindowWindow;

public class WindowsUIFactory implements UIFactory {
    public Button createButton() {
        return new WindowButton();
    }

    public Window createWindow() {
        return new WindowWindow();
    }
}
