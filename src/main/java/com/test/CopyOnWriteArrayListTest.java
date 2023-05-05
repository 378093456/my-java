package com.test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

//使用写时复制，避免并发修改异常
//往list集合添加数据时，先把list复制到一个长度+1的list，最后把要添加的数据加到这个list最后
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {
        List list=new CopyOnWriteArrayList();
        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add("java");
                System.out.println(list);
            },"thread-"+i).start();
        }
    }
}
