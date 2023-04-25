package com.test;

import java.util.concurrent.*;

//懒加载单例+线程安全
public class LazySingleton {

    private LazySingleton(){

    }

    private static LazySingleton INSTANCE = null;

    public static LazySingleton getInstance(){
        if(INSTANCE==null){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(INSTANCE==null){
                INSTANCE=new LazySingleton();
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        Callable<LazySingleton> callable = new Callable<LazySingleton>() {

            @Override
            public LazySingleton call() throws Exception {
                return LazySingleton.getInstance();
            }
        };
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<LazySingleton> submit1 = executorService.submit(callable);
        Future<LazySingleton> submit2 = executorService.submit(callable);
        Future<LazySingleton> submit3 = executorService.submit(callable);
        Future<LazySingleton> submit4 = executorService.submit(callable);
        Future<LazySingleton> submit5 = executorService.submit(callable);
        try {
            System.out.println(submit1.get());
            System.out.println(submit2.get());
            System.out.println(submit3.get());
            System.out.println(submit4.get());
            System.out.println(submit5.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }
    }
}
