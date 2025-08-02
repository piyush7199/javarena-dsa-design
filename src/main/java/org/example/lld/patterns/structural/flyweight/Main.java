package org.example.lld.patterns.structural.flyweight;

public class Main {
    public static void main(String[] args) {
        Forest forest = new Forest();

        // Plant trees with shared textures
        forest.plantTree(1, 2, "Pine");
        forest.plantTree(3, 4, "Oak");
        forest.plantTree(5, 6, "Pine");

        // Render all trees
        forest.renderForest();
    }
}
