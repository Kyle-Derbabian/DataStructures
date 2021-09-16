package testing;

import com.datastructures.trees.*;
import com.datastructures.trees.binarySearchTree.BinarySearchTree;
import com.datastructures.trees.redBlackTree.*;

import static com.datastructures.trees.redBlackTree.RBTNode.sentinel;

public class Test {

    public static void main(String[] args) {

        RedBlackTree T1 = new RedBlackTree();
        T1.insert(1);
        T1.insert(2);
        T1.insert(3);
        T1.insert(4);
        T1.insert(5);
        T1.insert(6);
        T1.insert(7);
        T1.insert(8);
        T1.insert(9);

        RedBlackTree T2 = new RedBlackTree();
        T2.insert(11);
        T2.insert(12);
        T2.insert(13);


//      RB_Join:
////        print(T2);
//
//        RBTNode y = (RBTNode) T1.getRoot().getRight().getRight();
////        System.out.println(y);
//
//        RBTNode parentOfY = (RBTNode) y.getParent();
////        System.out.println(parentOfY);
//
//        // disconnect Ty from T1:
//        y.setParent(sentinel);
//        parentOfY.setRight(sentinel);
//        RedBlackTree Ty = new RedBlackTree(y);
//
//
//        RBTNode x = new RBTNode(10, Color.RED);
//        // connect x
//        parentOfY.setRight(x);
//        x.setParent(parentOfY);
//        x.setLeft(y);
//        x.setRight(T2.getRoot());
//
//        print(T1);
//
//        T1.treeInsertFixup(x);
//
//        print(T1);

    }



    private static void print(BinaryTree t) {
        printTree(t.getRoot(), "");
        System.out.println();
    }

    private static void printTree(Node node, String prefix) {

        if (node == sentinel) {
//            System.out.println(prefix + " +");
            return;
        }

        System.out.println(prefix + " + " + node.getKey() + "       " + (node instanceof RBTNode ? ((RBTNode)node).getColor() : ""));
        printTree(node.getLeft(), prefix + "  ");
        printTree(node.getRight(), prefix + "  ");

    }

}
