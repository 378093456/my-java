package com.test;

import java.util.concurrent.atomic.AtomicInteger;

//带可见性和原子性的整型包装类
public class AtomicIntegerTest {

    static class MyData{
        AtomicInteger atomicInteger=new AtomicInteger();

        //原子性整型包装类的++运算
        private void atomicAdd(){
            atomicInteger.getAndIncrement();
        }
    }

    public static void main(String[] args) {
        MyData myData=new MyData();
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<10;j++){
                    myData.atomicAdd();
                }
            },"thread-"+i).start();
        }
        //实时判断线程是否全部结束,main线程和GC线程除外，也可以用CountdownLatch方式
        while(Thread.activeCount()>2){
            //线程没有全部结束，main线程让步
            Thread.yield();
        }
        System.out.println(myData.atomicInteger);
    }
}
