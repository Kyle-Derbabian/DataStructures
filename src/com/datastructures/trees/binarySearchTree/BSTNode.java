package com.datastructures.trees.binarySearchTree;

import com.datastructures.trees.Node;

public class BSTNode extends Node {

    public BSTNode(int key) {
        super(key);
    }

    @Override
    public String toString() {
        return "value: " + this.getKey() + " \nparent: " + (this.getParent() == null ? null : this.getParent().getKey()) + " \nleft: " + (this.getLeft() == null ? null : this.getLeft().getKey()) + " \nright: " + (this.getRight() == null ? null : this.getRight().getKey());
    }

}
