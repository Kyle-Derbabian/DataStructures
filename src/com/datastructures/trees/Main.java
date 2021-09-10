package com.datastructures.trees;

public class Main {

    public static void print(Object object) {
        System.out.println(object);
        System.out.println();
    }

    public static void main(String[] args) {

    }

    public static void printTree(BSTNode node, String prefix) {

        if (node == null) {
            System.out.println(prefix + " +");
            return;
        }

        System.out.println(prefix + " + " + node.getKey());
        printTree(node.getLeft(), prefix + " ");
        printTree(node.getRight(), prefix + " ");

    }

}
