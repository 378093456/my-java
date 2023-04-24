package com.test;

//GC调优，JVM参数设置
public class GC {

    public static void main(String[] args) {
        //查看系统CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        //查看虚拟机堆内存总量
        System.out.println(Runtime.getRuntime().totalMemory());
        //查看虚拟机试图使用的最大堆内存
        System.out.println(Runtime.getRuntime().maxMemory());

//        JVM常用XX参数
//        -XX:+PrintGCDetails：打印GC收集细节；
//        -XX:MatespaceSize：设置元空间大小；
//        -XX:MaxTenuringThreshold：设置新生代到老年代的年龄条件（默认15）；
//        -XX:InitialHeapSize（-Xms）：设置初始堆内存，默认物理内存1/64；
//        -XX:MaxHeapSize（-Xmx）：设置最大堆内存，默认物理内存1/4；
//        -XX:ThreadStackSize（-Xss）：设置线程栈大小，默认值是0，0表示JDK为系统（Linux、macOS或Windows）设置的默认大小是1024kb
//        -XX:MaxDirectMemorySize：设置最大直接内存大小；
//        -Xmn：设置设置年轻代大小，默认堆内存的1/3；
//        -XX:SurvivorRatio：设置Eden区在年轻代的比例，默认8:1:1；
//        -XX:NewRatatio：设置老年代在堆内存的比例，默认2:1
//        通过Idea终端jps命令和jinfo命令查看参数值

        try {
            Thread.sleep(Integer.MAX_VALUE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
