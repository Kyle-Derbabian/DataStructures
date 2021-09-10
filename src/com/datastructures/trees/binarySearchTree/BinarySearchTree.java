package com.datastructures.trees.binarySearchTree;

import com.datastructures.trees.BinaryTree;
import com.datastructures.trees.Node;

import org.jetbrains.annotations.NotNull;

public class BinarySearchTree extends BinaryTree {

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
        this.setRoot(root);
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
        Node node1 = null;
        Node node2 = this.getRoot();
        while (node2 != null) {
            node1 = node2;
            if (node.getKey() < node2.getKey()) {
                node2 = node2.getLeft();
            } else {
                node2 = node2.getRight();
            }
        }
        node.setParent(node1);
        if (node1 == null) {
            this.setRoot(node);
        } else if (node.getKey() < node1.getKey()) {
            node1.setLeft(node);
        } else {
            node1.setRight(node);
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
