package com.example.algorithm.hash;

import android.util.Log;

public class HashNode {

    Node[] data = new Node[16];
    int max_count = 16;
    int count = 0;

    //HashMap的容量
    int indexFor(int h, int length) {  //散列核心
        return h & (length - 1);
    }

    final int hash(Object k) {
        int h = 0;
        //得到k的hashcode值
        h ^= k.hashCode();
        //进行计算
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    public Object get(Object key) {
        if (key == null)
            return null;
        Node entry = getEntry(key);

        return null == entry ? null : entry.value;
    }

    //数据获取
    final Node getEntry(Object key) {
        int hash = (key == null) ? 0 : hash(key);
        for (Node e = data[indexFor(hash, data.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash &&
                    ((k = e.key) == key || (key != null && key.equals(k))))
                return e;
        }
        return null;
    }


    //数据存入
    private void addEntry(int hash, Object key, Object value, int i) {

        if (count > max_count * 0.75) {//12
            //扩容
        }
        //存入
        Node node = new Node();
        node.hash = hash;
        node.key = key;
        node.value = value;
        System.out.println("index i = " + i + " key = " + key);
        if (data[i] == null) {
            data[i] = node;
        } else {
            System.out.println("hash冲突");
            Node end_node = reverseList(data[i]);
            end_node.next = node;
        }
    }

    public static Node reverseList(Node head) {

        Node tempNode;
        Node curNode = head;
        while (curNode.next != null) {
            tempNode = curNode.next;
            curNode = tempNode;
        }

        return curNode;
    }

    public Object put(Object key, Object value) {

        //通过调用hash方法对key进行哈希，得到哈希之后的数值。
        // 该方法实现可以通过看源码，其目的是为了尽可能的让键值对可以分不到不同的桶中
        int hash = hash(key);

        int i = indexFor(hash, data.length);

        //已经存在数据
        for (Node e = data[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
                Object oldValue = e.value;
                e.value = value;
                return oldValue;
            }
        }

        count++;
        addEntry(hash, key, value, i);
        return null;
    }

}
