package org.example.lld.patterns.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

public class TreeFactory {
    private static Map<String, Tree> trees = new HashMap<>();

    public static Tree getTree(String texture) {
        Tree tree = trees.get(texture);
        if (tree == null) {
            tree = new TreeType(texture);
            trees.put(texture, tree);
        }
        return tree;
    }
}
