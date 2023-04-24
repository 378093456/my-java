package com.test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

//强、软、弱、虚四大引用类型
public class ReferenceType {

    private static Object obj = new Object();

    //强引用，即使发生OOM了也不会被GC回收
    static void strongReference(){
        Object newObj = new Object();
        newObj=obj;
        obj=null;

        //故意配置10m堆内存的情况下，生成超过10m的大对象模拟OOM
        try {
            byte[] bytes = new byte[15 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("强引用："+newObj);
        }
    }

    //软引用，发生OOM了才会被GC回收
    static void softReference(){
        SoftReference<Object> softReference = new SoftReference<Object>(obj);
        obj=null;

        //故意配置10m堆内存的情况下，生成超过10m的大对象模拟OOM
        try {
            byte[] bytes = new byte[15 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("软引用："+softReference.get());
        }
    }

    //弱引用，发生GC就会被回收
    static void weakReference(){
        WeakReference<Object> weakReference = new WeakReference<Object>(obj);
        obj=null;

        //直接手动GC
        System.gc();
        System.out.println("弱引用："+weakReference.get());

        //WeakHashMap同理，发生GC就会被回收
        WeakHashMap<Integer, Object> weakHashMap = new WeakHashMap<>();
        Integer integer = new Integer(1);
        weakHashMap.put(integer,"java");
        integer=null;

        //直接手动GC
        System.gc();
        System.out.println("WeakHashMap："+weakHashMap);
    }

    //虚引用，和引用队列联合使用
    static void phantomReference(){
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<Object>(obj,referenceQueue);
        obj=null;

        System.out.println("虚引用GC前：");
        System.out.println("PhantomReference："+phantomReference.get());
        System.out.println("ReferenceQueue："+referenceQueue.poll());

        //直接手动GC
        System.gc();

        try {
            //设置睡眠时间，GC后会放入引用队列
            Thread.sleep(500);
            System.out.println("虚引用GC后：");
            System.out.println("PhantomReference："+phantomReference.get());
            System.out.println("ReferenceQueue："+referenceQueue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //强引用，即使发生OOM了也不会被GC
//        strongReference();

        //软引用，发生OOM了会被GC
//        softReference();

        //弱引用，发生GC就会被回收
//        weakReference();

        //虚引用，
        phantomReference();
    }
}
