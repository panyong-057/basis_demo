package com.example.algorithm.hash;

import java.util.HashMap;
import java.util.HashSet;

public class Test1 {
    public static void main(String[] args) {

        /**
         * HashMap 数组加单向链表
         * LinkHashMap 数组加双向循环链表
         * ThreeMap 有序
         */


        //自定义hashMap  未加入扩容机制
        HashNode hashNode = new HashNode();


        hashNode.put(1, "A");
        hashNode.put(2, "B");
        hashNode.put(12, "C");
        hashNode.put(13, "D");
        hashNode.put(14, "E");
        hashNode.put(20, "F");
        hashNode.put(15, "G");
        hashNode.put(18, "H");
        hashNode.put(19, "Z");
        hashNode.put(10, "Y");
        System.out.print("" + hashNode.get(1));
        System.out.print("" + hashNode.get(2));
        System.out.print("" + hashNode.get(12));
        System.out.print("" + hashNode.get(13));
        System.out.print("" + hashNode.get(14));
        System.out.print("" + hashNode.get(20));
        System.out.print("" + hashNode.get(15));
        System.out.print("" + hashNode.get(18));
        System.out.print("" + hashNode.get(19));
        System.out.println("");
        //其中 2 和19 hash冲突


        /**
         * LRU 算法    最近最少使用
         */

        LRU<Integer, String> lru = new LRU<>(3);

        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");

        lru.print();

        lru.put(6, "D");//往后边存

        lru.print();

        lru.get(2);
        lru.print();
    }
}
