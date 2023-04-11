package com.test;

import lombok.Synchronized;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//死锁实现
public class Deadlock {

    static Object lock1=new Object();

    static Object lock2=new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+"尝试获取lock1");
                synchronized (lock1){
                    System.out.println("获取到了lock1");
                    System.out.println("尝试获取lock2");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2){
                        System.out.println("获取到了lock2");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+"尝试获取lock2");
                synchronized (lock2){
                    System.out.println("获取到了lock2");
                    System.out.println("尝试获取lock1");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1){
                        System.out.println("获取到了lock1");
                    }
                }
            }
        }).start();
    }
}
