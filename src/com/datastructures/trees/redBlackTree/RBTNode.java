package com.datastructures.trees.redBlackTree;

import com.datastructures.trees.Node;

public class RBTNode extends Node {

    private Color color;

    public RBTNode(int value, Color color) {
        super(value);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "value: " + this.getKey() + " \nparent: " + (this.getParent() == null ? null : this.getParent().getKey()) + " \nleft: " + (this.getLeft() == null ? null : this.getLeft().getKey()) + " \nright: " + (this.getRight() == null ? null : this.getRight().getKey()) + "\ncolor: " + this.getColor().toString();
    }

}
