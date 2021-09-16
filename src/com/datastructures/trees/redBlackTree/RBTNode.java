package com.datastructures.trees.redBlackTree;

import com.datastructures.trees.Node;

public class RBTNode extends Node {

    private Color color;

    public static final RBTNode sentinel = new RBTNode(null, Color.BLACK);

    public RBTNode(Integer value, Color color) {
        super(value);
        this.color = color;
        this.left = sentinel;
        this.right = sentinel;
        this.parent = sentinel;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public RBTNode getParent() {
        return (RBTNode) parent;
    }

    @Override
    public RBTNode getLeft() {
        return (RBTNode) left;
    }

    @Override
    public RBTNode getRight() {
        return (RBTNode) right;
    }

    @Override
    public String toString() {
        return "value: " + (this.getKey() == null ? null : this.getKey().toString()) + " \nparent: " + (this.getParent() == null || this.getParent().getKey() == null ? null : this.getParent().getKey().toString()) + " \nleft: " + (this.getLeft() == null || this.getLeft().getKey() == null ? null : this.getLeft().getKey().toString()) + " \nright: " + (this.getRight() == null || this.getRight().getKey() == null ? null : this.getRight().getKey().toString()) + "\ncolor: " + this.getColor().toString();
    }

}
