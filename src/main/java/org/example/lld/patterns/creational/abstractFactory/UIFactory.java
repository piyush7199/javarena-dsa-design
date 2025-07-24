package org.example.lld.patterns.creational.abstractFactory;

import org.example.lld.patterns.creational.abstractFactory.product.Button;
import org.example.lld.patterns.creational.abstractFactory.product.Window;

public interface UIFactory {
    Button createButton();

    Window createWindow();
}
