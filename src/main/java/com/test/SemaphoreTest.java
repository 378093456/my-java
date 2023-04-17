package com.test;

import java.util.concurrent.Semaphore;

//1、用于共享资源的互斥
//2、用于并发量的控制
//模拟排队停车（复用）
public class SemaphoreTest {

    public static void main(String[] args) {
        //假设有5个停车位
        Semaphore semaphore = new Semaphore(5);

        //假设10辆车要停车
        for(int i=1;i<=10;i++){
            new Thread(()->{
                try {
                    //占用一个线程
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"已停车！");
                    System.out.println(Thread.currentThread().getName()+"已离开！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //释放一个线程
                    semaphore.release();
                }
            },"thread-"+i).start();
        }
    }
}
