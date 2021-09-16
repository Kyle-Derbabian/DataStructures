package com.datastructures.trees.binarySearchTree;

import com.datastructures.trees.Node;

public class BSTNode extends Node {

    public static final BSTNode sentinel = new BSTNode(null);

    public BSTNode(Integer key) {
        super(key);
    }

    @Override
    public BSTNode getParent() {
        return (BSTNode) parent;
    }

    @Override
    public BSTNode getLeft() {
        return (BSTNode) left;
    }

    @Override
    public BSTNode getRight() {
        return (BSTNode) right;
    }

    @Override
    public String toString() {
        return "value: " + this.getKey() + " \nparent: " + (this.getParent() == null ? null : this.getParent().getKey()) + " \nleft: " + (this.getLeft() == null ? null : this.getLeft().getKey()) + " \nright: " + (this.getRight() == null ? null : this.getRight().getKey());
    }

}
