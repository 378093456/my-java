package com.test;

import java.util.concurrent.*;

//带返回值的Callable接口
public class CallableTest {

    public static void main(String[] args) {
        FutureTask task=new FutureTask(new CallableThread());

        new Thread(task,"CallableThread").start();
        //判断Callable是否执行完
        while (true){
            if (task.isDone()){
                try {
                    System.out.println(task.get());
                    return;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class CallableThread implements Callable {

        @Override
        public Object call() throws Exception {
            System.out.println(Thread.currentThread().getName()+" is run!");
            Thread.sleep(5000);
            return 200;
        }
    }
}
