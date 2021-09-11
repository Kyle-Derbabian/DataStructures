package com.datastructures.trees.redBlackTree;

import com.datastructures.trees.BinaryTree;
import com.datastructures.trees.Node;

import org.jetbrains.annotations.NotNull;

public class RedBlackTree extends BinaryTree {

//    RBTNode root; maybe put this.root = super.root in the constructor??

    /**
     * Constructs a <code>RedBlackTree</code> object without a root.
     */
    public RedBlackTree() {
        this.root = null;
    }

    /**
     * Constructs a <code>RedBlackTree</code> object with a given root.
     * @param root an <code>int</code> value representing the root of this <code>RedBlackTree</code> instance
     */
    public RedBlackTree(int root) {
        this.root = new RBTNode(root, Color.BLACK);
    }

    /**
     * Constructs a <code>RedBlackTree</code> object with a given root.
     * @param root an <code>RBTNode</code> object representing the root of this <code>RedBlackTree</code> instance
     */
    public RedBlackTree(RBTNode root) {
        this.root = root;
        ((RBTNode) this.root).setColor(Color.BLACK);
    }

    /**
     * Returns the root of this <code>RedBlackTree</code> object.
     * @return a <code>RBTNode</code> object representing the root of this <code>RedBlackTree</code> instance
     */
    public RBTNode getRoot() {
        return (RBTNode) this.root;
    }

    /**
     * Inserts the given key into this <code>RedBlackTree</code> instance.
     * @param key an <code>int</code> value representing the key to insert into this <code>RedBlackTree</code> instance.
     */
    public void treeInsert(int key) {
        this.treeInsert(new RBTNode(key, Color.RED));
    }

    /**
     * Removes the given key from this <code>RedBlackTree</code> instance.
     * @param key an <code>int</code> value representing the key to remove from this <code>RedBlackTree</code> instance
     */
    public void treeDelete(int key) {
        this.treeDelete((RBTNode) this.treeSearch(key));
    }

    /**
     * Helper method for <code>RedBlackTree(int x)</code>.
     * @param node a <code>BSTNode</code> object representing the node to insert into this <code>RedBlackTree</code> instance
     */
    private void treeInsert(RBTNode node) {
        Node parent = null;
        Node child = this.getRoot();
        while (child != null) {
            parent = child;
            if (node.getKey() < child.getKey()) {
                child = child.getLeft();
            } else {
                child = child.getRight();
            }
        }
        node.setParent(parent);
        if (parent == null) {
            this.setRoot(node);
        } else if (node.getKey() < parent.getKey()) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        node.setColor(Color.RED);
        this.treeInsertFixup(node);
    }

    private void treeInsertFixup(RBTNode node) {
        while (((RBTNode) node.getParent()).getColor() == Color.RED) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                Node uncle = node.getParent().getParent().getRight();
                if (uncle != null && ((RBTNode) uncle).getColor() == Color.RED) {
                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
                    ((RBTNode) uncle).setColor(Color.BLACK);
                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
                    node = (RBTNode) node.getParent().getParent();
                } else {
                    if (node == node.getParent().getRight()) {
                        node = (RBTNode) node.getParent();
                        this.rotateLeft(node);
                    }
                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
                    this.rotateRight(node.getParent().getParent());
                }
            } else {
                Node uncle = node.getParent().getParent().getLeft();
                if (((RBTNode) uncle).getColor() == Color.RED) {
                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
                    ((RBTNode) uncle).setColor(Color.BLACK);
                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
                } else {
                    if (node == node.getParent().getLeft()) {
                        node = (RBTNode) node.getParent();
                        this.rotateRight(node);
                    }
                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
                    this.rotateLeft(node.getParent().getParent());
                }
            }
        }
        this.getRoot().setColor(Color.BLACK);
    }

    /**
     * Helper method for <code>treeDelete(int key)</code>.
     * @param node an <code>RBTNode</code> object representing the node to remove from this <code>RedBlackTree</code> instance
     */
    private void treeDelete(@NotNull RBTNode node) {
        RBTNode y = node;
        Color yOriginalColor = y.getColor();
        RBTNode x;
        if (node.getLeft() == null) {
            x = (RBTNode) node.getRight();
            this.transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            x = (RBTNode) node.getLeft();
            this.transplant(node, node.getLeft());
        } else {
            y = (RBTNode) this.treeMinimum(node.getRight());
            yOriginalColor = y.getColor();
            x = (RBTNode) y.getRight();
            if (y.getParent() == node) {
                if (x != null) {
                    x.setParent(y);
                }
            } else {
                this.transplant(y, y.getRight());
                y.setRight(node.getRight());
                y.getRight().setParent(y);
            }
            this.transplant(node, y);
            y.setLeft(node.getLeft());
            y.getLeft().setParent(y);
            y.setColor(node.getColor());
        }
        if (yOriginalColor == Color.BLACK) {
            this.treeDeleteFixup(x);
        }
    }

    private void treeDeleteFixup(RBTNode node) {
        while (node != this.root && node.getColor() == Color.BLACK) {
            if (node == node.getParent().getLeft()) {
                RBTNode w = (RBTNode) node.getParent().getLeft();
                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    ((RBTNode) node.getParent()).setColor(Color.RED);
                    this.rotateLeft(node.getParent());
                    w = (RBTNode) node.getParent().getRight();
                }
                if (((RBTNode) w.getLeft()).getColor() == Color.BLACK && ((RBTNode) w.getRight()).getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    node = (RBTNode) node.getParent();
                } else {
                    if (((RBTNode) w.getRight()).getColor() == Color.BLACK) {
                        ((RBTNode) w.getLeft()).setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        this.rotateRight(w);
                        w = (RBTNode) node.getParent().getRight();
                    }
                    w.setColor(((RBTNode) node.getParent()).getColor());
                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
                    ((RBTNode) w.getRight()).setColor(Color.BLACK);
                    this.rotateLeft(node.getParent());
                    node = (RBTNode) this.root;
                }
            } else {
                RBTNode w = (RBTNode) node.getParent().getRight();
                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    ((RBTNode) node.getParent()).setColor(Color.RED);
                    this.rotateRight(node.getParent());
                    w = (RBTNode) node.getParent().getLeft();
                }
                if (((RBTNode) w.getRight()).getColor() == Color.BLACK && ((RBTNode) w.getLeft()).getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    node = (RBTNode) node.getParent();
                } else {
                    if (((RBTNode) w.getLeft()).getColor() == Color.BLACK) {
                        ((RBTNode) w.getRight()).setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        this.rotateLeft(w);
                        w = (RBTNode) node.getParent().getLeft();
                    }
                    w.setColor(((RBTNode) node.getParent()).getColor());
                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
                    ((RBTNode) w.getLeft()).setColor(Color.BLACK);
                    this.rotateRight(node.getParent());
                    node = (RBTNode) this.root;
                }
            }
        }
        node.setColor(Color.BLACK);
    }

}
