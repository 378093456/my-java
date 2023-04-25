package com.test;

//JVM调优、GC参数设置

//        JVM常用XX参数
//        -XX:+PrintGCDetails：打印GC收集细节；
//        -XX:MatespaceSize：设置元空间大小；
//        -XX:MaxTenuringThreshold：设置新生代到老年代的年龄条件（默认15）；
//        -XX:InitialHeapSize（-Xms）：设置初始堆内存，默认物理内存1/64；
//        -XX:MaxHeapSize（-Xmx）：设置最大堆内存，默认物理内存1/4；
//        -XX:ThreadStackSize（-Xss）：设置线程栈大小，默认值是0，0表示JDK为系统（Linux、macOS或Windows）设置的默认大小是1024kb
//        -XX:MaxDirectMemorySize：设置最大直接内存大小；
//        -XX:+UseG1GC：设置为G1垃圾回收器；
//        -Xmn：设置设置年轻代大小，默认堆内存的1/3；
//        -XX:SurvivorRatio：设置Eden区在年轻代的比例，默认8:1:1；
//        -XX:NewRatatio：设置老年代在堆内存的比例，默认2:1
//        通过Idea终端jps命令和jinfo命令查看参数值

//        查看进程参数：jinfo -flags 进程id
//        查看初始参数：java -XX:+PrintFlagsInitial
//        查看最终参数：java -XX:+PrintFlagsFinal
//        =表示没改过
//        :=表示人为改过或JVM改过
public class GC {

    public static void main(String[] args) {
        //查看系统CPU核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        //查看虚拟机堆内存总量
        System.out.println(Runtime.getRuntime().totalMemory());
        //查看虚拟机试图使用的最大堆内存
        System.out.println(Runtime.getRuntime().maxMemory());
    }
}
