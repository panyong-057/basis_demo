
## 简单实现HashMap

理论
========
- JDK 1.6, JDK 1.7 HashMap 采用位桶(数组) + 链表实现。
- JDK 1.8 HashMap 采用位桶(数组) + 链表 + 红黑树实现。（当链表长度超过阈值 “8” 时，将链表转换为红黑树）

哈希冲突
=============
- 利用哈希冲突特性 存取数据
- 定义一个链表数组 Node[] data = new Node[16];
- put 的时候，通过hashCode算出在data数组中索引,2种情况
  1. hash值算出的索引一样，key不一样,存入链表 (hash冲突)
  2. hash值算出的索引一样，key一样 ,放弃存入
- get 的时候，通过hashCode算出在data数组中索引,2种情况
  1. hash值算出的索引一样，key不一样 ，遍历链表，比对key
  2. hash值算出的索引一样，key一样 ，直接返回

实践代码(简单实现)
===============
```java
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

```
使用
===========

```java

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
      
```
demo
==============
[algorithm下的hash中](https://github.com/panyong-057/basis_demo).
