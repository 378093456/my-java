package com.test;

//死锁实现
public class Deadlock {

    public static void main(String[] args) {
        Object object1 = new Object();
        Object object2 = new Object();

        new Thread(() -> {
            synchronized (object1) {
                System.out.println(Thread.currentThread().getName() + "获取object1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                    System.out.println(Thread.currentThread().getName() + "获取object2");
                }
            }
        }, "thread-A").start();

        new Thread(() -> {
            synchronized (object2) {
                System.out.println(Thread.currentThread().getName() + "获取object2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object1) {
                    System.out.println(Thread.currentThread().getName() + "获取object1");
                }
            }
        }, "thread-B").start();
    }
}
