package com.example.algorithm.tree;

public class RBTreeNode {

    private int key;
    private NodeColor color;
    private RBTreeNode parent;
    private RBTreeNode left;
    private RBTreeNode right;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public NodeColor getColor() {
        return color;
    }

    public void setColor(NodeColor color) {
        this.color = color;
    }

    public RBTreeNode getParent() {
        return parent;
    }

    public void setParent(RBTreeNode parent) {
        this.parent = parent;
    }

    public RBTreeNode getLeft() {
        return left;
    }

    public void setLeft(RBTreeNode left) {
        this.left = left;
    }

    public RBTreeNode getRight() {
        return right;
    }

    public void setRight(RBTreeNode right) {
        this.right = right;
    }



    public RBTreeNode(int key, NodeColor nodeColor) {
        this.key = key;
        this.color = nodeColor;
    }

    public String toString() {
        return "" + key + ":" + color + "  ";
    }

}
