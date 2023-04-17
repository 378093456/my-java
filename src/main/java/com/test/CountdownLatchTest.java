package com.test;

import java.util.concurrent.CountDownLatch;

//用于主线程等待所有子线程执行完成再执行，递减统计
public class CountdownLatchTest {

    public static void main(String[] args) {
        //设置CountdownLatch数量
        CountDownLatch countDownLatch = new CountDownLatch(5);

        for(int i=0;i<5;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"执行完成！");
                //计数器减1
                countDownLatch.countDown();
            },"thread-"+i).start();
        }
        try {
            //阻塞main线程，直到CountdownLatch为0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"执行完成！");
    }
}
