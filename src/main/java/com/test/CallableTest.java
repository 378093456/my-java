package com.test;

import java.util.concurrent.*;

//带返回值的Callable接口
public class CallableTest {

    public static void main(String[] args) {
        FutureTask task=new FutureTask(new CallableThread());

        new Thread(task,"CallableThread").start();
        try {
            Thread.sleep(1000);
            System.out.println(task.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName()+" is run!");
            return 200;
        }
    }
}
