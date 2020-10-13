package com.example.algorithm.array;

import java.util.Arrays;

public class Test1 {

    public static void main(String[] args) {

        char[] hello_str = {'h', 'e', 'l', 'l', '0'};
        char[] world_str = {'w', 'o', 'r', 'l', 'd'};

        char[] all_str = new char[50];

        /**
         * 1、 遍历
         */
//        int i = 0;
//        for (char a : hello_str) {
//            all_str[i] = a;
//            i++;
//        }
//        int j = hello_str.length;
//        for (char a : world_str) {
//            all_str[j] = a;
//            j++;
//        }


        /**
         * 2、 System.arraycopy
         */
        System.arraycopy(hello_str, 0, all_str, 0, hello_str.length);
        System.arraycopy(world_str, 0, all_str, hello_str.length, world_str.length);

        /**
         * 3、复制
         */
        //Arrays.copyOf(hello_str,hello_str.length);


        System.out.println(Arrays.toString(all_str));

        //ArrayList的remove()和add(int ?,object ？)都是是根据此方法进行的操作

        //ArrayList、Vector默认初始容量为10
        // HashSet、HashMap：默认初始容量为16
        //static int indexFor(int h, int length) {  //散列核心
        //           return h & (length-1);
        //   }
        // length  ： 16 = 10000
        // length -1  ：15 = 01111


        /**
         * 冒泡排序  冒泡排序是是一种简单的排序算法。它重复地遍历要排序的数列
         * 选择排序 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置（末尾位置）
         * 插入排序
         * 希尔排序
         * 堆排序
         * 归并排序
         * 快速排序  找一个点 2边遍历
         */
    }
}
