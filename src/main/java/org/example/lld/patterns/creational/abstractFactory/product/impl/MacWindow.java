package org.example.lld.patterns.creational.abstractFactory.product.impl;

import org.example.lld.patterns.creational.abstractFactory.product.Window;

public class MacWindow implements Window {
    public void display() {
        System.out.println("Displaying a Mac-style window");
    }
}