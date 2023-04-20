package com.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//线程池
public class ThreadPoolTest {

    public static void main(String[] args) {
        //创建5线程的线程池
        ExecutorService fixedThread = Executors.newFixedThreadPool(5);

        //创建单线程的线程池
        ExecutorService singleThread = Executors.newSingleThreadExecutor();

        //创建多线程的线程池
        ExecutorService cachedThread = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            fixedThread.submit(()->{
                System.out.println(Thread.currentThread().getName()+" is run");
            });
        }
        //关闭线程池
        fixedThread.shutdown();
    }
}
