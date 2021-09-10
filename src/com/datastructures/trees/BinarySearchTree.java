package com.datastructures.trees;

import org.jetbrains.annotations.NotNull;

public class BinarySearchTree {

    private BSTNode root;

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
        return root;
    }

    /**
     * Returns the height of this <code>BinarySearchTree</code> object.
     * @return an <code>int</code> representing the height of this <code>BinarySearchTree</code> object.
     */
    public int getHeight() {
        return this.getHeight(this.getRoot());
    }

    /**
     * Prints the nodes of this <code>BinarySearchTree</code> instance using inorder traversal.
     */
    public void inorderTraversal() {
        this.inorderTraversal(this.getRoot());
    }

    /**
     * Searches for and returns the node with the give key in this <code>BinarySearchTree</code> instance.
     * @param key the key of the <code>BSTNode</code> object to search for
     * @return a <code>BSTNode</code> object representing the node in this <code>BinarySearchTree</code> instance with the given key
     */
    public BSTNode treeSearch(int key) {
        return this.treeSearch(this.getRoot(), key);
    }

    /**
     * Searches for and returns the node with the minimum key in this <code>BinarySearchTree</code> instance.
     * @return a <code>BSTNode</code> object representing the node with the minimum key in this <code>BinarySearchTree</code> instance
     */
    public BSTNode treeMinimum() {
        return this.treeMinimum(this.getRoot());
    }

    /**
     * Searches for and returns the node with the maximum key in this <code>BinarySearchTree</code> instance.
     * @return a <code>BSTNode</code> object representing the node with the maximum key in this <code>BinarySearchTree</code> instance
     */
    public BSTNode treeMaximum() {
        return this.treeMaximum(this.getRoot());
    }

    /**
     * Searches for and returns the successor of the node with the given key in this <code>BinarySearchTree</code> instance.
     * @param key the key of the <code>BSTNode</code> object whose successor to search for
     * @return a <code>BSTNode</code> object representing the successor of the node with the given key in this <code>BinarySearchTree</code> instance
     */
    public BSTNode treeSuccessor(int key) {
        BSTNode node = treeSearch(this.getRoot(), key);
        if (node.getRight() != null) {
            return treeMinimum(node.getRight());
        }
        BSTNode y = node.getParent();
        while (y != null && node == y.getRight()) {
            node = y;
            y = y.getParent();
        }
        return y;
    }

    /**
     * Removes the given key from this <code>BinarySearchTree</code> instance.
     * @param key an <code>int</code> value representing the key to remove from this <code>BinarySearchTree</code> instance
     */
    public void treeDelete(int key) {
        this.treeDelete(this.treeSearch(key));
    }

    /**
     * Inserts the given key into this <code>BinarySearchTree</code> instance.
     * @param key an <code>int</code> value representing the key to insert into this <code>BinarySearchTree</code> instance.
     */
    public void treeInsert(int key) {
        this.treeInsert(new BSTNode(key));
    }

    /**
     * Sets the root of this <code>BinarySearchTree</code> object. Cannot be called from outside this class in order to preserve the BST invariant.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    private void setRoot(BSTNode root) {
        this.root = root;
    }

    /**
     * Helper method for <code>getHeight()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    private int getHeight(BSTNode root) {
        if (root == null) {
            return -1;
        }
        return Math.max(this.getHeight(root.getLeft()), this.getHeight(root.getRight())) + 1;
    }

    /**
     * Helper method for <code>inorderTraversal()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     */
    private void inorderTraversal(BSTNode root) {
        if (root != null) {
            this.inorderTraversal(root.getLeft());
            System.out.println(root.getKey());
            this.inorderTraversal(root.getRight());
        }
    }

    /**
     * Helper method for <code>treeSearch(int key)</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     * @param key the key of the <code>BSTNode</code> object to search for
     * @return a <code>BSTNode</code> object representing the node in this <code>BinarySearchTree</code> instance with the given key
     */
    private BSTNode treeSearch(BSTNode root, int key) {
        if (root == null || key == root.getKey()) {
            return root;
        }
        if (key < root.getKey()) {
            return this.treeSearch(root.getLeft(), key);
        } else {
            return this.treeSearch(root.getRight(), key);
        }
    }

    /**
     * Helper method for <code>treeMinimum()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     * @return a <code>BSTNode</code> object representing the node with the minimum key in this <code>BinarySearchTree</code> instance
     */
    private BSTNode treeMinimum(@NotNull BSTNode root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    /**
     * Helper method for <code>treeMaximum()</code>.
     * @param root a <code>BSTNode</code> object representing the root of this <code>BinarySearchTree</code> instance
     * @return a <code>BSTNode</code> object representing the node with the maximum key in this <code>BinarySearchTree</code> instance
     */
    private BSTNode treeMaximum(@NotNull BSTNode root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    /**
     * Helper method for <code>treeInsert(int x)</code>.
     * @param node a <code>BSTNode</code> object representing the node to insert into this <code>BinarySearchTree</code> instance
     */
    private void treeInsert(BSTNode node) {
        BSTNode node1 = null;
        BSTNode node2 = this.getRoot();
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
     * Replaces the subtree rooted at node <code>replacee</code> with the subtree rooted at node <code>replacement</code>.
     * @param replacee the <code>BSTNode</code> object representing the node to replace in this <code>BinarySearchTree</code> instance.
     * @param replacement the <code>BSTNode</code> object representing the root of the subtree replacing <code>replacee</code> in this <code>BinarySearchTree</code> instance.
     */
    private void transplant(@NotNull BSTNode replacee, BSTNode replacement) {
        if (replacee.getParent() == null) {
            this.setRoot(replacement);
        } else if (replacee == replacee.getParent().getLeft()) {
            replacee.getParent().setLeft(replacement);
        } else {
            replacee.getParent().setRight(replacement);
        }
        if (replacement != null) {
            replacement.setParent(replacee.getParent());
        }
    }

    /**
     * Helper method for <code>treeDelete(int key)</code>.
     * @param node a <code>BSTNode</code> object representing the node to remove from this <code>BinarySearchTree</code> instance
     */
    private void treeDelete(@NotNull BSTNode node) {
        if (node.getLeft() == null) {
            this.transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            this.transplant(node, node.getLeft());
        } else {
            BSTNode successor = treeMinimum(node.getRight());
            if (successor.getParent() != node) {
                this.transplant(successor, successor.getRight());
                successor.setRight(node.getRight());
                successor.getRight().setParent(successor); // node.getRight().setParent(successor)
            }
            this.transplant(node, successor);
            successor.setLeft(node.getLeft());
            successor.getLeft().setParent(successor); // node.getLeft().setParent(successor)
        }
    }

    /**
     * Left-rotates this <code>BinarySearchTree</code> object at the node <code>node</code>.
     * @param node a <code>BSTNode</code> object representing the node about which to left-rotate this <code>BinarySearchTree</code> instance.
     */
    private void rotateLeft(@NotNull BSTNode node) {
        if (node.getRight() == null) {
            throw new NullPointerException("Input node has a null right neighbor.");
        }
        BSTNode node1 = node.getRight();
        node.setRight(node1.getLeft());
        if (node1.getLeft() != null) {
            node1.getLeft().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == null) {
            this.setRoot(node1);
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(node1);
        } else {
            node.getParent().setRight(node1);
        }
        node1.setLeft(node);
        node.setParent(node1);
    }

    /**
     * Right-rotates this <code>BinarySearchTree</code> object at the node <code>node</code>.
     * @param node a <code>BSTNode</code> object representing the node about which to right-rotate this <code>BinarySearchTree</code> instance.
     */
    private void rotateRight(@NotNull BSTNode node) {
        BSTNode node1 = node.getLeft();
        node.setLeft(node1.getRight());
        if (node1.getRight() != null) {
            node1.getRight().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == null) {
            this.setRoot(node1);
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(node1);
        } else {
            node.getParent().setLeft(node1);
        }
        node1.setRight(node);
        node.setParent(node1);
    }

}
