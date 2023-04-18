package com.test;

import java.util.concurrent.Semaphore;

//1、用于共享资源的互斥
//2、用于并发量的控制
//模拟排队洗车（复用）
public class SemaphoreTest {

    public static void main(String[] args) {
        //假设有5个洗车位
        Semaphore semaphore = new Semaphore(5);

        //假设10辆车要洗车
        for(int i=1;i<=10;i++){
            new Thread(()->{
                try {
                    //占用一个线程
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"正在洗车！");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"洗完已离开！");
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
