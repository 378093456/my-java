package com.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//Condition类实现精确唤醒线程
public class ConditionTest {

    //线程对应标志
    static int i=1;//A:1   B:2     C:3

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition A=reentrantLock.newCondition();
        Condition B=reentrantLock.newCondition();
        Condition C=reentrantLock.newCondition();

        //A线程
        new Thread(()->{
            reentrantLock.lock();
                try {
                    //判断是否轮到A线程执行
                    while (i!=1) {
                        //否则阻塞
                        A.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                    //修改线程标志并通知B线程
                    i=2;
                    B.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
        },"thread-A").start();

        //B线程
        new Thread(()->{
            reentrantLock.lock();

                try {
                    //判断是否轮到B线程执行
                    while (i!=2) {
                        //否则阻塞
                        B.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                    //修改线程标志并通知C线程
                    i=3;
                    C.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
        },"thread-B").start();

        //C线程
        new Thread(()->{
            reentrantLock.lock();
                try {
                    //判断是否轮到C线程执行
                    while (i!=3) {
                        //否则阻塞
                        C.await();
                    }
                    System.out.println(Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    reentrantLock.unlock();
                }
        },"thread-C").start();
    }
}
