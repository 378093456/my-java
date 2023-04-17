package com.test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//用于在每个线程执行完之后阻塞，当最后一个线程执行完之后再一起执行，递增统计
public class CyclicBarrierTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5
                ,()->{ System.out.println(Thread.currentThread().getName()+"全部执行完成！");});

        for(int i=0;i<5;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"执行完成！");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"thread-"+i).start();
        }
    }
}
