package com.datastructures.trees.redBlackTree;

import com.datastructures.trees.BinaryTree;
import com.datastructures.trees.Node;

import org.jetbrains.annotations.NotNull;

public class RedBlackTree extends BinaryTree {

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
        this.root = new RBTNode(root);
    }

    /**
     * Constructs a <code>RedBlackTree</code> object with a given root.
     * @param root an <code>RBTNode</code> object representing the root of this <code>RedBlackTree</code> instance
     */
    public RedBlackTree(RBTNode root) {
        this.setRoot(root);
    }

    /**
     * Inserts the given key into this <code>BinarySearchTree</code> instance.
     * @param key an <code>int</code> value representing the key to insert into this <code>BinarySearchTree</code> instance.
     */
    public void treeInsert(int key) {
        this.treeInsert(new RBTNode(key));
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
    private void treeInsert(RBTNode node) {

    }

    /**
     * Helper method for <code>treeDelete(int key)</code>.
     * @param node a <code>BSTNode</code> object representing the node to remove from this <code>BinarySearchTree</code> instance
     */
    private void treeDelete(@NotNull Node node) {

    }

}
