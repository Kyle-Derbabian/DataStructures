package com.datastructures.trees;

import org.jetbrains.annotations.NotNull;

public abstract class BinaryTree {

    protected Node root;

    /**
     * Constructs a <code>BinaryTree</code> object without a root.
     */
    public BinaryTree() {
        this.root = null;
    }

    /**
     * Returns the root of this <code>BinaryTree</code> object.
     * @return a <code>Node</code> object representing the root of this <code>BinaryTree</code> instance
     */
    public Node getRoot() {
        return this.root;
    }

    /**
     * Returns the height of this <code>BinaryTree</code> object.
     * @return an <code>int</code> representing the height of this <code>BinaryTree</code> object.
     */
    public int getHeight() {
        return this.getHeight(this.getRoot());
    }

    /**
     * Prints the nodes of this <code>BinaryTree</code> instance using inorder traversal.
     */
    public void inorderTraversal() {
        this.inorderTraversal(this.getRoot());
    }

    /**
     * Searches for and returns the node with the give key in this <code>BinaryTree</code> instance.
     * @param key the key of the <code>Node</code> object to search for
     * @return a <code>Node</code> object representing the node in this <code>BinaryTree</code> instance with the given key
     */
    public Node treeSearch(int key) {
        return this.treeSearch(this.getRoot(), key);
    }

    /**
     * Searches for and returns the node with the minimum key in this <code>BinaryTree</code> instance.
     * @return a <code>Node</code> object representing the node with the minimum key in this <code>BinaryTree</code> instance
     */
    public Node treeMinimum() {
        return this.treeMinimum(this.getRoot());
    }

    /**
     * Searches for and returns the node with the maximum key in this <code>BinaryTree</code> instance.
     * @return a <code>Node</code> object representing the node with the maximum key in this <code>BinaryTree</code> instance
     */
    public Node treeMaximum() {
        return this.treeMaximum(this.getRoot());
    }

    /**
     * Searches for and returns the successor of the node with the given key in this <code>BinaryTree</code> instance.
     * @param key the key of the <code>Node</code> object whose successor to search for
     * @return a <code>Node</code> object representing the successor of the node with the given key in this <code>BinaryTree</code> instance
     */
    public Node treeSuccessor(int key) {
        Node node = treeSearch(this.getRoot(), key);
        if (node.getRight() != null) {
            return treeMinimum(node.getRight());
        }
        Node y = node.getParent();
        while (y != null && node == y.getRight()) {
            node = y;
            y = y.getParent();
        }
        return y;
    }

    public String toString() {
        return this.getClass().getSimpleName() + ", root = " + this.getRoot().getKey();
    }

    /**
     * Sets the root of this <code>BinaryTree</code> object. Cannot be called from outside this class in order to preserve the BST invariant.
     * @param root a <code>Node</code> object representing the root of this <code>BinaryTree</code> instance
     */
    protected void setRoot(Node root) {
        this.root = root;
    }

    /**
     * Helper method for <code>getHeight()</code>.
     * @param root a <code>Node</code> object representing the root of this <code>BinaryTree</code> instance
     */
    protected int getHeight(Node root) {
        if (root == null) {
            return -1;
        }
        return Math.max(this.getHeight(root.getLeft()), this.getHeight(root.getRight())) + 1;
    }

    /**
     * Helper method for <code>inorderTraversal()</code>.
     * @param root a <code>Node</code> object representing the root of this <code>BinaryTree</code> instance
     */
    protected void inorderTraversal(Node root) {
        if (root != null) {
            this.inorderTraversal(root.getLeft());
            System.out.println(root.getKey());
            this.inorderTraversal(root.getRight());
        }
    }

    /**
     * Helper method for <code>treeSearch(int key)</code>.
     * @param root a <code>Node</code> object representing the root of this <code>BinaryTree</code> instance
     * @param key the key of the <code>Node</code> object to search for
     * @return a <code>Node</code> object representing the node in this <code>BinaryTree</code> instance with the given key
     */
    protected Node treeSearch(Node root, int key) {
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
     * @param root a <code>Node</code> object representing the root of this <code>BinaryTree</code> instance
     * @return a <code>Node</code> object representing the node with the minimum key in this <code>BinaryTree</code> instance
     */
    protected Node treeMinimum(@NotNull Node root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    /**
     * Helper method for <code>treeMaximum()</code>.
     * @param root a <code>Node</code> object representing the root of this <code>BinaryTree</code> instance
     * @return a <code>Node</code> object representing the node with the maximum key in this <code>BinaryTree</code> instance
     */
    protected Node treeMaximum(@NotNull Node root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root;
    }

    /**
     * Replaces the subtree rooted at node <code>replacee</code> with the subtree rooted at node <code>replacement</code>.
     * @param replacee the <code>Node</code> object representing the node to replace in this <code>BinaryTree</code> instance.
     * @param replacement the <code>Node</code> object representing the root of the subtree replacing <code>replacee</code> in this <code>BinaryTree</code> instance.
     */
    protected void transplant(@NotNull Node replacee, Node replacement) {
        if (replacee.getParent() == null) {
            this.root = replacement;
        } else if (replacee == replacee.getParent().getLeft()) {
            replacee.getParent().setLeft(replacement);
        } else {
            replacee.getParent().setRight(replacement);
        }
        if (replacement != null) {
            replacement.setParent(replacee.getParent());
        }
    }

    public abstract void insert(Integer key);

    public abstract void delete(Integer key);

    /**
     * Left-rotates this <code>BinaryTree</code> object at the node <code>node</code>.
     * @param node a <code>Node</code> object representing the node about which to left-rotate this <code>BinaryTree</code> instance.
     */
    protected abstract void rotateLeft(@NotNull Node node);

    /**
     * Right-rotates this <code>BinaryTree</code> object at the node <code>node</code>.
     * @param node a <code>Node</code> object representing the node about which to right-rotate this <code>BinaryTree</code> instance.
     */
    protected abstract void rotateRight(@NotNull Node node);


}
