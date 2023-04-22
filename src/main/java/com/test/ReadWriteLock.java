package com.test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

//读写锁
public class ReadWriteLock {

    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

        new Thread(()->{
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+"获取了读锁");
            rwLock.readLock().unlock();
            System.out.println(Thread.currentThread().getName()+"释放了读锁");
        },"t1").start();

        new Thread(()->{
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName()+"获取了写锁");
            rwLock.writeLock().unlock();
            System.out.println(Thread.currentThread().getName()+"释放了写锁");
        },"t2").start();
    }
}
