package com.datastructures.trees;

public class BSTNode {

    private int key;
    private BSTNode parent;
    private BSTNode left;
    private BSTNode right;

    public BSTNode(int value) {
        this.key = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public BSTNode getParent() {
        return parent;
    }

    public void setParent(BSTNode parent) {
        this.parent = parent;
    }

    public BSTNode getLeft() {
        return left;
    }

    public void setLeft(BSTNode left) {
        this.left = left;
    }

    public BSTNode getRight() {
        return right;
    }

    public void setRight(BSTNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "value: " + this.key + " \nparent: " + (this.parent == null ? null : this.parent.key) + " \nleft: " + (this.left == null ? null : this.left.key) + " \nright: " + (this.right == null ? null : this.right.key);
    }

}
