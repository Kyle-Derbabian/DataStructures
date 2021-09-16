package com.datastructures.trees.redBlackTree;

import com.datastructures.trees.BinaryTree;
import com.datastructures.trees.Node;

import org.jetbrains.annotations.NotNull;

public class RedBlackTree extends BinaryTree {

    /**
     * Constructs a <code>RedBlackTree</code> object without a root.
     */
    public RedBlackTree() {
        this.root = RBTNode.sentinel;
    }

    /**
     * Constructs a <code>RedBlackTree</code> object with a given root.
     *
     * @param root an <code>int</code> value representing the root of this <code>RedBlackTree</code> instance
     */
    public RedBlackTree(int root) {
        this.root = new RBTNode(root, Color.BLACK);
    }

    /**
     * Constructs a <code>RedBlackTree</code> object with a given root.
     *
     * @param root an <code>RBTNode</code> object representing the root of this <code>RedBlackTree</code> instance
     */
    public RedBlackTree(RBTNode root) {
        this.root = root;
        ((RBTNode) this.root).setColor(Color.BLACK);
    }

    /**
     * Returns the root of this <code>RedBlackTree</code> object.
     *
     * @return a <code>RBTNode</code> object representing the root of this <code>RedBlackTree</code> instance
     */
    public RBTNode getRoot() {
        return (RBTNode) this.root;
    }

    /**
     * Inserts a node with the given key into this <code>RedBlackTree</code> instance.
     * @param key an <code>Integer</code> object representing the key to insert into this <code>RedBlackTree</code> instance
     */
    public void insert(Integer key) {
        RBTNode z = new RBTNode(key, Color.RED);

        RBTNode y = RBTNode.sentinel;
        RBTNode x = this.getRoot();
        while (x != RBTNode.sentinel) {
            y = x;
            if (z.getKey() < x.getKey()) {
                x = (RBTNode) x.getLeft();
            } else {
                x = (RBTNode) x.getRight();
            }
        }
        z.setParent(y);

        if (y == RBTNode.sentinel) {
            this.setRoot(z);
        } else if (z.getKey() < y.getKey()) {
            y.setLeft(z);
        } else {
            y.setRight(z);
        }

        z.setLeft(RBTNode.sentinel);
        z.setRight(RBTNode.sentinel);
        z.setColor(Color.RED);

        this.treeInsertFixup(z);

    }

    /**
     * Helper function for <code>insert(Integer key)</code>.
     * @param z the <code>RBTNode</code> object from which to start the insertion fixup
     */
    public void treeInsertFixup(RBTNode z) {

        while (z.getParent().getColor() == Color.RED) {
            RBTNode y;
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                y = z.getParent().getParent().getRight();
                if (y.getColor() == Color.RED) {
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        this.rotateLeft(z);
                    }
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    this.rotateRight(z.getParent().getParent());
                }
            } else {
                y = z.getParent().getParent().getLeft();
                if (y.getColor() == Color.RED) {
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        this.rotateRight(z);
                    }
                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    this.rotateLeft(z.getParent().getParent());
                }
            }
        }

        this.getRoot().setColor(Color.BLACK);

    }

    public void delete(Integer key) {
        RBTNode z = (RBTNode) treeSearch(key);
        RBTNode y = z;
        Color yOriginalColor = y.getColor();
        RBTNode x = z.getRight();
        if (z.getLeft() == RBTNode.sentinel) {
            x = z.getRight();
            this.transplant(z, z.getRight());
        } else if (z.getRight() == RBTNode.sentinel) {
            x = z.getLeft();
            this.transplant(z, z.getLeft());
        } else {
            y = (RBTNode) this.treeMinimum(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                this.transplant(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            this.transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(y);
            y.setColor(z.getColor());
        }
        if (yOriginalColor == Color.BLACK) {
            this.treeDeleteFixup(x);
        }
    }

    public void treeDeleteFixup(RBTNode x) {
        while (x != RBTNode.sentinel && x.getColor() == Color.BLACK) {
            if (x == x.getParent().getLeft()) {
                RBTNode w = x.getParent().getRight();
                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    this.rotateLeft(x.getParent());
                    w = x.getParent().getRight();
                }
                if (w.getLeft().getColor() == Color.BLACK && w.getRight().getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getRight().getColor() == Color.BLACK) {
                        w.getLeft().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        this.rotateRight(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getRight().setColor(Color.BLACK);
                    this.rotateLeft(x.getParent());
                    x = this.getRoot();
                }
            } else {
                RBTNode w = x.getParent().getLeft();
                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    this.rotateRight(x.getParent());
                    w = x.getParent().getLeft();
                }
                if (w.getRight().getColor() == Color.BLACK && w.getLeft().getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeft().getColor() == Color.BLACK) {
                        w.getRight().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        this.rotateLeft(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getLeft().setColor(Color.BLACK);
                    this.rotateRight(x.getParent());
                    x = this.getRoot();
                }
            }
        }
    }

    protected void transplant(@NotNull Node u, Node v) {
        if (u.getParent() == RBTNode.sentinel) {
            this.root = v;
        } else if (u == u.getParent().getLeft()) {
            u.getParent().setLeft(v);
        } else {
            u.getParent().setRight(v);
        }
        v.setParent(u.getParent());
    }

    /**
     * Left-rotates this <code>BinaryTree</code> object at the node <code>node</code>.
     * @param node a <code>Node</code> object representing the node about which to left-rotate this <code>BinaryTree</code> instance.
     */
    protected void rotateLeft(@NotNull Node node) {
        if (node.getRight() == RBTNode.sentinel) {
            throw new NullPointerException("Input node has a null right neighbor.");
        }
        Node node1 = node.getRight();
        node.setRight(node1.getLeft());
        if (node1.getLeft() != RBTNode.sentinel) {
            node1.getLeft().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == RBTNode.sentinel) {
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
     * Right-rotates this <code>BinaryTree</code> object at the node <code>node</code>.
     * @param node a <code>Node</code> object representing the node about which to right-rotate this <code>BinaryTree</code> instance.
     */
    protected void rotateRight(@NotNull Node node) {
        if (node.getLeft() == RBTNode.sentinel) {
            throw new NullPointerException("Input node has a null left neighbor.");
        }
        Node node1 = node.getLeft();
        node.setLeft(node1.getRight());
        if (node1.getRight() != RBTNode.sentinel) {
            node1.getRight().setParent(node);
        }
        node1.setParent(node.getParent());
        if (node.getParent() == RBTNode.sentinel) {
            this.root = node1;
        } else if (node == node.getParent().getRight()) {
            node.getParent().setRight(node1);
        } else {
            node.getParent().setLeft(node1);
        }
        node1.setRight(node);
        node.setParent(node1);
    }





// second-oldest insert code
//    public void treeInsert(Integer key) {
//        this.treeInsert(new RBTNode(key, Color.RED));
//    }
//
//    private void treeInsert(RBTNode z) {
//
//        RBTNode y = RBTNode.sentinel;
//        RBTNode x = this.getRoot();
//        while (x != RBTNode.sentinel) {
//            y = x;
//            if (z.getKey() < x.getKey()) {
//                x = (RBTNode) x.getLeft();
//            } else {
//                x = (RBTNode) x.getRight();
//            }
//        }
//        z.setParent(y);
//
//        if (y == RBTNode.sentinel) {
//            this.setRoot(z);
//        } else if (z.getKey() < y.getKey()) {
//            y.setLeft(z);
//        } else {
//            y.setRight(z);
//        }
//
//        z.setLeft(RBTNode.sentinel);
//        z.setRight(RBTNode.sentinel);
//        z.setColor(Color.RED);
//
//        this.treeInsertFixup(z);
//
//    }



//
//    /**
//     * Inserts the given key into this <code>RedBlackTree</code> instance.
//     * @param key an <code>int</code> value representing the key to insert into this <code>RedBlackTree</code> instance.
//     */
//    public void treeInsert(int key) {
//        this.treeInsert(new RBTNode(key, Color.RED));
//    }
//
//    /**
//     * Removes the given key from this <code>RedBlackTree</code> instance.
//     * @param key an <code>int</code> value representing the key to remove from this <code>RedBlackTree</code> instance
//     */
//    public void treeDelete(int key) {
//        this.treeDelete((RBTNode) this.treeSearch(key));
//    }
//
//    /**
//     * Helper method for <code>RedBlackTree(int x)</code>.
//     * @param node a <code>BSTNode</code> object representing the node to insert into this <code>RedBlackTree</code> instance
//     */
//    private void treeInsert(RBTNode node) {
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
//        node.setColor(Color.RED);
//        this.treeInsertFixup(node);
//    }
//
//    private void treeInsertFixup(RBTNode node) {
//        while (node.getParent() != null && ((RBTNode) node.getParent()).getColor() == Color.RED) {
//            if (node.getParent() == node.getParent().getParent().getLeft()) {
//                Node uncle = node.getParent().getParent().getRight();
//                if (uncle != null && ((RBTNode) uncle).getColor() == Color.RED) {
//                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
//                    ((RBTNode) uncle).setColor(Color.BLACK);
//                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
//                    node = (RBTNode) node.getParent().getParent();
//                } else {
//                    if (node == node.getParent().getRight()) {
//                        node = (RBTNode) node.getParent();
//                        this.rotateLeft(node);
//                    }
//                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
//                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
//                    this.rotateRight(node.getParent().getParent());
//                }
//            } else {
//                Node uncle = node.getParent().getParent().getLeft();
//                if (uncle != null && ((RBTNode) uncle).getColor() == Color.RED) {
//                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
//                    ((RBTNode) uncle).setColor(Color.BLACK);
//                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
//                } else {
//                    if (node == node.getParent().getLeft()) {
//                        node = (RBTNode) node.getParent();
//                        this.rotateRight(node);
//                    }
//                    ((RBTNode) node.getParent()).setColor(Color.BLACK);
//                    ((RBTNode) node.getParent().getParent()).setColor(Color.RED);
//                    this.rotateLeft(node.getParent().getParent());
//                }
//            }
//        }
//        this.getRoot().setColor(Color.BLACK);
//    }
//
//    /**
//     * Helper method for <code>treeDelete(int key)</code>.
//     * @param z an <code>RBTNode</code> object representing the node to remove from this <code>RedBlackTree</code> instance
//     */
//    private void treeDelete(@NotNull RBTNode z) {
//        RBTNode y = z;
//        Color yOriginalColor = y.getColor();
//        RBTNode x;
//        if (z.getLeft() == null) {
//            x = (RBTNode) z.getRight();
//            this.transplant(z, z.getRight());
//        } else if (z.getRight() == null) {
//            x = (RBTNode) z.getLeft();
//            this.transplant(z, z.getLeft());
//        } else {
//            y = (RBTNode) this.treeMinimum(z.getRight());
//            yOriginalColor = y.getColor();
//            x = (RBTNode) y.getRight();
//            if (y.getParent() == z) {
//                x.setParent(y);
//            } else {
//                this.transplant(y, y.getRight());
//                y.setRight(z.getRight());
//                y.getRight().setParent(y);
//            }
//            this.transplant(z, y);
//            y.setLeft(z.getLeft());
//            y.getLeft().setParent(y);
//            y.setColor(z.getColor());
//        }
//        if (yOriginalColor == Color.BLACK) {
//            this.treeDeleteFixup(x);
//        }
//    }
//
//    private void treeDeleteFixup(RBTNode x) {
//        while (x != this.root && x.getColor() == Color.BLACK) {
//            if (x == x.getParent().getLeft()) {
//                RBTNode w = (RBTNode) x.getParent().getLeft();
//                if (w.getColor() == Color.RED) {
//                    w.setColor(Color.BLACK);
//                    ((RBTNode) x.getParent()).setColor(Color.RED);
//                    this.rotateLeft(x.getParent());
//                    w = (RBTNode) x.getParent().getRight();
//                }
//                if (((RBTNode) w.getLeft()).getColor() == Color.BLACK && ((RBTNode) w.getRight()).getColor() == Color.BLACK) {
//                    w.setColor(Color.RED);
//                    x = (RBTNode) x.getParent();
//                } else {
//                    if (((RBTNode) w.getRight()).getColor() == Color.BLACK) {
//                        ((RBTNode) w.getLeft()).setColor(Color.BLACK);
//                        w.setColor(Color.RED);
//                        this.rotateRight(w);
//                        w = (RBTNode) x.getParent().getRight();
//                    }
//                    w.setColor(((RBTNode) x.getParent()).getColor());
//                    ((RBTNode) x.getParent()).setColor(Color.BLACK);
//                    ((RBTNode) w.getRight()).setColor(Color.BLACK);
//                    this.rotateLeft(x.getParent());
//                    x = (RBTNode) this.root;
//                }
//            } else {
//                RBTNode w = (RBTNode) x.getParent().getRight();
//                if (w.getColor() == Color.RED) {
//                    w.setColor(Color.BLACK);
//                    ((RBTNode) x.getParent()).setColor(Color.RED);
//                    this.rotateRight(x.getParent());
//                    w = (RBTNode) x.getParent().getLeft();
//                }
//                if (((RBTNode) w.getRight()).getColor() == Color.BLACK && ((RBTNode) w.getLeft()).getColor() == Color.BLACK) {
//                    w.setColor(Color.RED);
//                    x = (RBTNode) x.getParent();
//                } else {
//                    if (((RBTNode) w.getLeft()).getColor() == Color.BLACK) {
//                        ((RBTNode) w.getRight()).setColor(Color.BLACK);
//                        w.setColor(Color.RED);
//                        this.rotateLeft(w);
//                        w = (RBTNode) x.getParent().getLeft();
//                    }
//                    w.setColor(((RBTNode) x.getParent()).getColor());
//                    ((RBTNode) x.getParent()).setColor(Color.BLACK);
//                    ((RBTNode) w.getLeft()).setColor(Color.BLACK);
//                    this.rotateRight(x.getParent());
//                    x = (RBTNode) this.root;
//                }
//            }
//        }
//        x.setColor(Color.BLACK);
//    }
//


}


