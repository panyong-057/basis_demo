package com.example.algorithm.link;

public class Test1 {
    public static void main(String[] args) {

        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        nodeA.setNext(nodeB);
        nodeB.setNext(nodeC);
        nodeC.setNext(nodeD);
        nodeD.setNext(nodeE);


        showNode(nodeA);
       // Node nodeTemp = reverseListNode(nodeA);
        Node nodeTemp = reverseList(nodeA);
        showNode(nodeTemp);
    }

    public static void showNode(Node head) {
        if (head == null) {
            System.out.println("");
            return;
        }
        System.out.print(" " + head.getData());
        showNode(head.getNext());
    }
    //递归

    public static Node reverseList(Node head) {

        if (head == null || head.getNext() == null) {
            return head;
        }
        // 遍历到链表尾部
        Node newHead = reverseList(head.getNext());
        // 反转
        head.getNext().setNext(head);
        head.setNext(null);

        return newHead;
    }


    //迭代
    public static Node reverseListNode(Node head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.getNext() == null) {
            return head;
        }
        //前一个节点指针
        Node preNode = null;
        //当前节点指针
        Node curNode = head;
        //下一个节点指针
        Node nextNode = null;

        while (curNode != null) {
            nextNode = curNode.getNext();//nextNode 指向下一个节点
            curNode.setNext(preNode);//将当前节点next域指向前一个节点
            preNode = curNode;//preNode 指针向后移动
            curNode = nextNode;//curNode指针向后移动
        }

        return preNode;
    }

}
