package org.example.lld.patterns.structural.flyweight;

public class TreeType implements Tree {
    private String texture; // Intrinsic state

    public TreeType(String texture) {
        this.texture = texture;
    }

    public void render(int x, int y) {
        System.out.println("Rendering tree with texture: " + texture + " at position (" + x + ", " + y + ")");
    }
}