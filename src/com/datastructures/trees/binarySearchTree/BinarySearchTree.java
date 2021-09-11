package com.datastructures.trees.binarySearchTree;

import com.datastructures.trees.BinaryTree;
import com.datastructures.trees.Node;

import org.jetbrains.annotations.NotNull;

public class BinarySearchTree extends BinaryTree {

//    BSTNode root;

    /**
     * Constructs a <code>BinarySearchTree</code> object without a root.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * Constructs a <code>BinarySearchTree</code> object with a given root.
     * @param root an <code>int</code> value representing the root of this <code>BinarySearchTree</code> instance
     */
    public BinarySearchTree(int root) {
        this.root = new BSTNode(root);
    }

    /**
     * Constructs a <code>BinarySearchTree</code> object with a given root.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    public BinarySearchTree(BSTNode root) {
        this.root = root;
    }

    /**
     * Returns the root of this <code>BinarySearchTree</code> object.
     * @return a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    public BSTNode getRoot() {
        return (BSTNode) this.root;
    }

    /**
     * Inserts the given key into this <code>BinarySearchTree</code> instance.
     * @param key an <code>int</code> value representing the key to insert into this <code>BinarySearchTree</code> instance.
     */
    public void treeInsert(int key) {
        this.treeInsert(new BSTNode(key));
    }

    /**
     * Removes the given key from this <code>BinarySearchTree</code> instance.
     * @param key an <code>int</code> value representing the key to remove from this <code>BinarySearchTree</code> instance
     */
    public void treeDelete(int key) {
        this.treeDelete(this.treeSearch(key));
    }

    /**
     * Helper method for <code>treeInsert(int x)</code>.
     * @param node a <code>BSTNode</code> object representing the node to insert into this <code>BinarySearchTree</code> instance
     */
    private void treeInsert(BSTNode node) {
        if (this.getRoot() == null) {
            this.root = node;
            return;
        }
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
    }

    /**
     * Helper method for <code>treeDelete(int key)</code>.
     * @param node a <code>BSTNode</code> object representing the node to remove from this <code>BinarySearchTree</code> instance
     */
    private void treeDelete(@NotNull Node node) {
        if (node.getLeft() == null) {
            this.transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            this.transplant(node, node.getLeft());
        } else {
            Node successor = treeMinimum(node.getRight());
            if (successor.getParent() != node) {
                this.transplant(successor, successor.getRight());
                successor.setRight(node.getRight());
                successor.getRight().setParent(successor);
            }
            this.transplant(node, successor);
            successor.setLeft(node.getLeft());
            successor.getLeft().setParent(successor);
        }
    }

}
