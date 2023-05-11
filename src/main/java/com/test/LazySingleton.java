package com.test;

import java.util.concurrent.*;

//懒加载单例+线程安全
public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        //synchronized同步代码块+双重检查锁实现线程安全
        //避免第一次重复new对象
        if(instance==null){
            try {
                Thread.sleep(100);
                synchronized (LazySingleton.class){
                    //避免线程抢锁重复new对象
                    if(instance==null){
                        instance=new LazySingleton();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return instance;

        //使用静态内部类实现线程安全
//        return Inner.instance;
    }

    private static class Inner{
        private static LazySingleton instance = new LazySingleton();
    }
}
