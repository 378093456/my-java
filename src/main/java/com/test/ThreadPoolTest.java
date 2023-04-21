package com.test;

import java.util.concurrent.*;

//线程池
public class ThreadPoolTest {

    public static void main(String[] args) {
//        //创建5线程的线程池，任务队列是LinkedBlockingQueue，大小默认是Integer类的最大值
//        ExecutorService fixedThread = Executors.newFixedThreadPool(5);
//
//        //创建单线程的线程池，任务队列是LinkedBlockingQueue，大小默认是Integer类的最大值
//        ExecutorService singleThread = Executors.newSingleThreadExecutor();
//
//        //创建自适应扩容的线程池,最大同时执行线程数是Integer类的最大值
//        ExecutorService cachedThread = Executors.newCachedThreadPool();
//
//        try {
//            for (int i = 0; i < 10; i++) {
//                fixedThread.submit(()->{
//                    System.out.println(Thread.currentThread().getName()+" is run");
//                });
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            //关闭线程池
//            fixedThread.shutdown();
//        }

        //手写线程池，可避免OOM（内存不足）
        handwrittenThreadPool();
    }

    //手写线程池，设置7大参数
    static void handwrittenThreadPool(){
        //获取CPU线程数
        System.out.println("CPU核数"+"\t"+Runtime.getRuntime().availableProcessors());
        //合理设置线程数
        //CPU密集型系统：CPU核数*2；
        //IO密集型系统（阻塞系数在0.8~0.9之间）：CPU核数/(1-阻塞系数)

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,//核心线程数
                5,//最大同时执行线程数
                1,//非核心线程存活时间
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<>(3),//任务队列
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.DiscardPolicy());//拒绝策略
                                                        //AbortPolicy（默认）：抛出拒绝执行异常，且停止执行；
                                                        //CallerRunsPolicy：调用者执行机制，把任务交给父线程执行；
                                                        //DiscardOldestPolicy：抛弃队列中等待最久的任务并将当前任务加入队列尝试再次提交；
                                                        //DiscardPolicy：丢弃任务，不处理也不报异常

        try {
            for (int i = 1; i <= 12; i++) {
                int j=i;
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t"+j);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭线程池
            threadPoolExecutor.shutdown();
        }
    }
}
