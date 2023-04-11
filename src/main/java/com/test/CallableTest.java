package com.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

    public static void main(String[] args) {
        FutureTask task=new FutureTask(new CallableThread());
        new Thread(task,"CallableThread").start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
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
            System.out.println(Thread.currentThread()+"CallableThread is run!");
            return 200;
        }
    }
}
