package testing;

import com.datastructures.trees.*;
import com.datastructures.trees.binarySearchTree.BinarySearchTree;
import com.datastructures.trees.redBlackTree.*;

public class Test {

    public static void main(String[] args) {

        RedBlackTree t = new RedBlackTree(7);
        t.treeInsert(3);
        print(t);
        t.treeInsert(6);
        print(t);
        t.treeInsert(9);
        print(t);
        t.treeDelete(9);
        print(t);
        t.treeDelete(3);
        print(t);

    }

    private static void print(BinaryTree t) {
        printTree(t.getRoot(), "");
        System.out.println();
    }

    private static void printTree(Node node, String prefix) {

        if (node == null) {
            System.out.println(prefix + " +");
            return;
        }

        System.out.println(prefix + " + " + node.getKey() + "    " + (node instanceof RBTNode ? ((RBTNode)node).getColor() : ""));
        printTree(node.getLeft(), prefix + " ");
        printTree(node.getRight(), prefix + " ");

    }

}
