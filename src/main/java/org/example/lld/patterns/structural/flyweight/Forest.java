package org.example.lld.patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.List;

public class Forest {
    private List<TreeInstance> trees = new ArrayList<>();

    // Class to store extrinsic state
    private static class TreeInstance {
        Tree tree;
        int x, y;

        TreeInstance(Tree tree, int x, int y) {
            this.tree = tree;
            this.x = x;
            this.y = y;
        }

        void render() {
            tree.render(x, y);
        }
    }

    public void plantTree(int x, int y, String texture) {
        Tree tree = TreeFactory.getTree(texture);
        trees.add(new TreeInstance(tree, x, y));
    }

    public void renderForest() {
        for (TreeInstance tree : trees) {
            tree.render();
        }
    }
}
