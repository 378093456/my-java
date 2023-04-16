package com.test;

import java.util.*;
import java.util.concurrent.atomic.AtomicStampedReference;

//用带时间戳（版本号）的原子对象引用解决CAS中ABA问题
public class ABATest {

    public static void main(String[] args) {
        AtomicStampedReference atomicStampedReference=new AtomicStampedReference<String>("A",1);

        new Thread(()->{
            //获取初始版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t"+"值："+atomicStampedReference.getReference()+"\t"+"第一次版本号："+stamp);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //期望对象引用，需要修改的对象引用，期望版本号，需要修改的版本号
            atomicStampedReference.compareAndSet("A","B",stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t"+"值："+atomicStampedReference.getReference()+"\t"+"第二次版本号："+atomicStampedReference.getStamp());
            //期望对象引用，需要修改的对象引用，期望版本号，需要修改的版本号
            atomicStampedReference.compareAndSet("B","A",atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(Thread.currentThread().getName()+"\t"+"值："+atomicStampedReference.getReference()+"\t"+"第三次版本号："+atomicStampedReference.getStamp());
        },"thread-A").start();

        new Thread(()->{
            //获取初始版本号
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"\t"+"值："+atomicStampedReference.getReference()+"\t"+"第一次版本号："+atomicStampedReference.getStamp());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //期望对象引用，需要修改的对象引用，期望版本号，需要修改的版本号
            atomicStampedReference.compareAndSet("A","SSS",stamp,stamp+1);
            System.out.println(Thread.currentThread().getName()+"\t"+"值："+atomicStampedReference.getReference()+"\t"+"第二次版本号："+atomicStampedReference.getStamp());
        },"thread-B").start();
    }
}
