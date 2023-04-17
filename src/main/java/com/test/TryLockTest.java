package com.test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

//用tryLock避免获取锁失败阻塞
public class TryLockTest {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();

        new Thread(()->{
            reentrantLock.lock();
            System.out.println(Thread.currentThread().getName()+"获取了锁！");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
                System.out.println(Thread.currentThread().getName()+"释放了锁！");
            }
        },"thread-A").start();

        new Thread(()->{
            try {
                Thread.sleep(1000);
                //用tryLock尝试获取锁，避免阻塞
                if(reentrantLock.tryLock(2000, TimeUnit.MILLISECONDS)){
                    System.out.println(Thread.currentThread().getName()+"获取了锁！");
                    reentrantLock.unlock();
                    System.out.println(Thread.currentThread().getName()+"释放了锁！");
                }else {
                    System.out.println(Thread.currentThread().getName()+"获取锁超时！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"thread-B").start();
    }
}
