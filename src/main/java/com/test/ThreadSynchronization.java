package com.test;

public class ThreadSynchronization {

    private ThreadSynchronization(){};

    //加入volatile禁止指令重排导致数据异常
    private static volatile ThreadSynchronization instance=null;

    static Runnable r1=new Runnable() {
        @Override
        public void run() {
            getInstance();
        }
    };

    static Runnable r2=new Runnable() {
        @Override
        public void run() {
            getInstance();
        }
    };

    public static void main(String[] args) {
        r1.run();
        r2.run();
    }

    public static ThreadSynchronization getInstance(){
        //有可能在多线程访问的时候重复new对象
        if(instance==null) {
            synchronized (ThreadSynchronization.class){
                //可能有多个线程进入抢锁等待状态
                if(instance==null){
                    instance = new ThreadSynchronization();
                }
            }
        }
        return instance;
    }
}
