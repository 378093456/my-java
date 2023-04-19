package com.test;

import com.mysql.cj.util.StringUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

//阻塞队列+生产者消费者
public class BlockingQueueAndProducerConsumer {

    static volatile boolean flag=true;//默认开启生产者消费者

    static BlockingQueue blockingQueue=null;

    public BlockingQueueAndProducerConsumer(BlockingQueue blockingQueue) {
        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public static void main(String[] args) {
        BlockingQueueAndProducerConsumer blockingQueueAndProducerConsumer =
                new BlockingQueueAndProducerConsumer(new ArrayBlockingQueue<String>(10));

        //生产线程
        new Thread(()->{
            boolean isOffer;
            while(flag){
                try {
                    Thread.sleep(1000);
                    //获取插入结果
                    isOffer=blockingQueue.offer("java",3000, TimeUnit.MILLISECONDS);
                    if(isOffer){
                        System.out.println(Thread.currentThread().getName()+"\t插入队列成功");
                    }else{
                        System.out.println(Thread.currentThread().getName()+"\t插入队列失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("flag=false,停止生产");
        },"thread-生产者").start();

        //消费线程
        new Thread(()->{
            String result=null;
            while(flag){
                try {
                    Thread.sleep(1000);
                    //获取值
                    result= String.valueOf(blockingQueue.poll(3000, TimeUnit.MILLISECONDS));
                    //判断是否为null或空字符串
                    if(StringUtils.isNullOrEmpty(result)){
                        System.out.println(Thread.currentThread().getName()+"\t获取队列失败");
                        //退出循环
                        return;
                    }else{
                        System.out.println(Thread.currentThread().getName()+"\t获取队列成功");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("flag=false,停止消费");
        },"thread-消费者").start();

        //10秒后关闭生产者消费者
        try {
            Thread.sleep(10000);
            flag=false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
