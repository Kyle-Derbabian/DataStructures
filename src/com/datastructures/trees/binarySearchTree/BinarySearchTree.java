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
     * Inserts a node with the given key into this <code>BinarySearchTree</code> instance.
     * @param key an <code>Integer</code> object representing the key to insert into this <code>BinarySearchTree</code> instance
     */
    public void insert(Integer key) {
        BSTNode node = new BSTNode(key);
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
     * Removes the node with the given key from this <code>BinarySearchTree</code> instance.
     * @param key an <code>Integer</code> object representing the key to remove from this <code>BinarySearchTree</code> instance
     */
    public void delete(Integer key) {
        BSTNode node = new BSTNode(key);
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

    /**
     * Left-rotates this <code>BinarySearchTree</code> object at the node <code>node</code>.
     * @param node a <code>Node</code> object representing the node about which to left-rotate this <code>BinarySearchTree</code> instance.
     */
    protected void rotateLeft(@NotNull Node node) {
        if (node.getRight() == null) {
            throw new NullPointerException("Input node has a null right neighbor.");
        }
        Node node1 = node.getRight();
        node.setRight(node1.getLeft());
        if (node1.getLeft() != null) {
            node1.getLeft().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == null) {
            this.root = node1;
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
     * @param node a <code>Node</code> object representing the node about which to right-rotate this <code>BinarySearchTree</code> instance.
     */
    protected void rotateRight(@NotNull Node node) {
        if (node.getLeft() == null) {
            throw new NullPointerException("Input node has a null left neighbor.");
        }
        Node node1 = node.getLeft();
        node.setLeft(node1.getRight());
        if (node1.getRight() != null) {
            node1.getRight().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == null) {
            this.root = node1;
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(node1);
        } else {
            node.getParent().setLeft(node1);
        }
        node1.setRight(node);
        node.setParent(node1);
    }



// old insert and delete code:
//    /**
//     * Inserts the given key into this <code>BinarySearchTree</code> instance.
//     * @param key an <code>int</code> value representing the key to insert into this <code>BinarySearchTree</code> instance.
//     */
//    public void insert(int key) {
//        this.insert(new BSTNode(key));
//    }
//
//    /**
//     * Removes the given key from this <code>BinarySearchTree</code> instance.
//     * @param key an <code>int</code> value representing the key to remove from this <code>BinarySearchTree</code> instance
//     */
//    public void delete(int key) {
//        this.delete(this.treeSearch(key));
//    }
//
//    /**
//     * Helper method for <code>treeInsert(int x)</code>.
//     * @param node a <code>BSTNode</code> object representing the node to insert into this <code>BinarySearchTree</code> instance
//     */
//    private void insert(BSTNode node) {
//        if (this.getRoot() == null) {
//            this.root = node;
//            return;
//        }
//        Node parent = null;
//        Node child = this.getRoot();
//        while (child != null) {
//            parent = child;
//            if (node.getKey() < child.getKey()) {
//                child = child.getLeft();
//            } else {
//                child = child.getRight();
//            }
//        }
//        node.setParent(parent);
//        if (parent == null) {
//            this.setRoot(node);
//        } else if (node.getKey() < parent.getKey()) {
//            parent.setLeft(node);
//        } else {
//            parent.setRight(node);
//        }
//    }
//
//    /**
//     * Helper method for <code>treeDelete(int key)</code>.
//     * @param node a <code>BSTNode</code> object representing the node to remove from this <code>BinarySearchTree</code> instance
//     */
//    private void delete(@NotNull Node node) {
//        if (node.getLeft() == null) {
//            this.transplant(node, node.getRight());
//        } else if (node.getRight() == null) {
//            this.transplant(node, node.getLeft());
//        } else {
//            Node successor = treeMinimum(node.getRight());
//            if (successor.getParent() != node) {
//                this.transplant(successor, successor.getRight());
//                successor.setRight(node.getRight());
//                successor.getRight().setParent(successor);
//            }
//            this.transplant(node, successor);
//            successor.setLeft(node.getLeft());
//            successor.getLeft().setParent(successor);
//        }
//    }

}
